<template>
    <div style="  overflow:scroll; width:100%">
        <el-col :span="24">
      
        <el-col :span="24"  >
            <el-table 
                :data="tquestionList"
                   highlight-current-row
                 
         
             
             
                >
            
                  <el-table-column
                 type="index"
                 width="150">
               </el-table-column>

                  <el-table-column
                     property="tquestionName"
                     label="试题名称"
                     width="280">
                   </el-table-column>

                   <el-table-column
                     property="optionNum"
                     label="选择题"
                       width="120">
                   </el-table-column>

                    <el-table-column
                     property="writeNum"
                     label="书面题" width="120">
                   </el-table-column>

                      <el-table-column
                     property="totalTime"
                     label="考试时长" width="120">
                   </el-table-column>


                  <el-table-column
                    label="学科"
                   width="150">
                    
                    <template slot-scope="scope">
                       <div slot="reference" class="name-wrapper">
                          <el-tag size="medium"> {{ scope.row.subjectName }} </el-tag>
                        </div>
                    
                    </template>
                  </el-table-column>

                    <el-table-column
                    label="日期"  width="300"
                  >
                    <template slot-scope="scope">
                      <i class="el-icon-time"></i>
                      <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
                    </template>
                  </el-table-column>

                  <el-table-column label="  操作" width="400">
                    <template slot-scope="scope">
                      <el-button
                        size="mini"
                       
                       @click="showDialogs(scope.row)"> &nbsp; &nbsp;编辑 </el-button> &nbsp; &nbsp;

                       <el-dialog title="修改试题" :visible.sync="dialogFormVisible"  >
                       试卷名称:   <el-input
                              placeholder="请输入内容"
                              v-model="changeQuestion.tquestionName"
                              clearable>
                            </el-input><p></p>
                        选择题数量： <el-input
                         
                              type="number"
                              v-model="changeQuestion.optionNum"
                              clearable>
                           </el-input><p></p>
                          填空题数量： <el-input
                 
                              type="number"
                              v-model="changeQuestion.writeNum"
                              clearable>
                            </el-input><p></p>

                               考试时长： <el-input
                 
                              type="number"
                              v-model="changeQuestion.totalTime"
                              clearable>
                            </el-input><p></p>
                       
                       

                            <div slot="footer" class="dialog-footer">
                              <el-button @click="dialogFormVisible = false">取 消</el-button>
                              <el-button type="primary" @click="changeQuestiona()">确 定</el-button>
                            </div>
                        </el-dialog>


                      <el-button
                        size="mini"
                       type="primary"
                       @click="preview(scope.row)"> &nbsp; &nbsp;预览试题 </el-button> &nbsp; &nbsp;
                  
                      <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                  </el-table-column>
            </el-table>
        </el-col>
        <br><br>
        <el-col  :span="24" >
      <el-pagination
      @current-change="CurrentChange"
      :current-page.sync="currentPage1"
      :page-size="size"
      layout="total, prev, pager, next"
      :total="totala">
    </el-pagination>
        </el-col>
        </el-col>
    </div>
</template>

<script>
export default {
 name:"QuestionList",
 data(){
     return{
      tquestionList:'',
      userInfo:'',
         pages:'',
      size:'',
      current:'',
      totala:'',
   
     currentRow: null,
     currentPage1: 1,


     changeRoleInfo:'',

  //  编辑
     dialogFormVisible: false,
     changeQuestion:'',
     }
 },
 methods:{
   changeQuestiona(){
     var that=this
     this.changeQuestion.name=   this.changeQuestion.tquestionName
              this.$axios.put('/tquestion',this.changeQuestion, {
             headers: {
                       "X-Token":sessionStorage.getItem("token")
                         },
                
            }).then(function (response) {
                 console.log(response);
                 if(response.data.code ==1){
             
                    that.$message(response.data.msg)

               that.search()
              
                        // lets.onsubmit("clickitem1")
               
                 }else{
                      that.$message(response.data.msg)
                 }
                 that.dialogFormVisible=false
               }).catch(function (error) {
                 console.log(error);
               });
   },
   showDialogs(row){
       this.changeQuestio=''
    this.dialogFormVisible=true
    this.changeQuestion=row

   },
     preview(row){
           this.$router.push({
          path: '/tquestion/preview',
          query: {
            tquestionId: row.id,
            tquestionName:row.tquestionName

          }
        })
     },

        search(){
            var that=this
             this.$axios.get('/tquestion/userId', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { "current": 1 ,
                                           "size":10,
                                               "userId": that.userInfo.userId ,}
                             })
                             .then(function (res) {
                                       console.log(res.data);
                                that.tquestionList=res.data.records

                                that.pages=res.data.pages
                                that.size=res.data.size
                                that.current=res.data.current
                                that.totala=res.data.total
                            
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });

        },
         handleDelete(index, row) {
             console.log(row.id)

          var that=this
         this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
           }).then(() => {

             var that2=that
             that.$axios.delete('/tquestion', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { "id":row.id}
                             })
                             .then(function (res) {
                                 that2.$message({
                                   type: 'success',
                                   message: '删除成功!'
                                 });
                                 that2.search()
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });
        
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
   
      },

         CurrentChange(val) {
          var that=this
             this.$axios.get('/tquestion/userId', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { "current": val ,
                                           "size":10,
                                               "userId": that.userInfo.userId ,}
                             })
                             .then(function (res) {
                                       console.log(res.data);
                                that.tquestionList=res.data.records

                                that.pages=res.data.pages
                                that.size=res.data.size
                                that.current=res.data.current
                                that.totala=res.data.total
                            
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });

      },

 },
 created:function(){
     this.userInfo=JSON.parse(sessionStorage.getItem("userInfo"))
     var that=this
         this.$axios.get('/tquestion/userId', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { "current": 1 ,
                                           "size":10,
                                     "userId": that.userInfo.userId ,
                                            }
                             })
                             .then(function (res) {
                                 console.log(res.data);
                                that.tquestionList=res.data.records

                                that.pages=res.data.pages
                                that.size=res.data.size
                                that.current=res.data.current
                                that.totala=res.data.total
                            
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });

 }

}
</script>

<style>

</style>
