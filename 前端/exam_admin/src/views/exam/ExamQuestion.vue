<template >
     <div style="  overflow:scroll;">
        <el-col :span="24">
        <el-col :span="24"  >
          <el-select v-model="optionValue" placeholder="请选择">
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
             </el-select>
                

          <el-input
             placeholder="请输入试题名/出题人"
             prefix-icon="el-icon-search"
            class="search"
             v-model="searchName">
            </el-input>

       
             <el-button type="primary"  @click="search()" round>搜索</el-button>
        </el-col>


        <el-col :span="24"  >
            <el-table
                :data="questionList"
                   highlight-current-row
               
                style="width: 1200px"
             
                max-height="700"
                >
            
                  <el-table-column
                 type="index"
                 width="50">
               </el-table-column>

                  <el-table-column
                     property="tquestionName"
                     label="试题名称"
                     width="180">
                   </el-table-column>

                   <el-table-column
                     property="userName"
                     label="出题人">
                   </el-table-column>

                    <el-table-column
                     property="optionNum"
                     label="选择题数量"  >
                   </el-table-column>

                    <el-table-column
                     property="writeNum"
                     label="书面题数量" >
                    </el-table-column>
             
                    <el-table-column
                     property="totalTime"
                     label="考试时长" >
                    </el-table-column>


                  <el-table-column
                    label="学科"
                    >
                    
                    <template slot-scope="scope">
                       <div slot="reference" class="name-wrapper">
                          <el-tag size="medium"> {{ scope.row.subjectName }} </el-tag>
                        </div>
                    
                    </template>
                  </el-table-column>
                    <el-table-column
                    label="出题时间"
                    width="180">
                    <template slot-scope="scope">
                      <i class="el-icon-time"></i>
                      <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
                    </template>
                  </el-table-column>

                  <el-table-column label="  操作" width="200">
                    <template slot-scope="scope">
                      <el-button
                        size="mini"
                       type="success"
                       @click="takeExam(scope.row)">参加考试 </el-button> &nbsp; &nbsp;

               
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
 name:"ExamQuestion",
data(){
    return{
        userList:'',
        examMsg:{},
        questionList:'',

      pages:'',
      size:'',
      current:'',
      totala:'',
     currentRow: null,
        currentPage1: 1,

      searchName:'',

   options: [{
          value: 1,
          label: '试题名称'
        }, {
          value: 2,
          label: '出题人'
        }
        ],
        optionValue: 1


    }
},
methods:{
    takeExam(row){
// 注册考试，传递tquestionId，跳转靠考试页面
  this.examMsg.subjectId=row.subjectId
  this.examMsg.examType=1
  this.examMsg.tquestionId=row.id
  this.examMsg.totalTime=row.totalTime
  var that=this
  console.log(this.examMsg)
           this.$axios.post('/exam',this.examMsg, {
             headers: {
                       "X-Token":sessionStorage.getItem("token")
                         },
                
            }).then(function (response) {
                 console.log(response);
              
                    that.$message({
                   type:"success",
                   message:"即将开始考试"
                    })
                    
                that.examMsg=response.data
                  that.$router.push({
                      path: '/test',
                      query: {
                        examId: that.examMsg.id ,
                        tquestionId: row.id,
                        selectNum:0,
                          writeNum:0,
                          totalTime:  that.examMsg.totalTime,
                        examTitle:row.tquestionName

                   }
        })
              
                        // lets.onsubmit("clickitem1")
               
           
               }).catch(function (error) {
                 console.log(error);
               });
    
    
    },
    search(){
        console.log(this.optionValue)

        if(this.searchName===''){
   var that=this
           this.$axios.get('/tquestion/all', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { "current": 1 ,
                                           "size":10,
                                          }
                             })
                             .then(function (res) {
                                 console.log(res.data);
                                   that.questionList=res.data.records
                                that.pages=res.data.pages
                                that.size=res.data.size
                                that.current=res.data.current
                                that.totala=res.data.total
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });
        }
      else if(this.optionValue === 1){
                console.log(this.optionValue)
      var that=this
           this.$axios.get('/tquestion/title', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { "current": 1 ,
                                           "size":10,
                                             "testName":that.searchName
                                          }
                             })
                             .then(function (res) {
                                 console.log(res.data);
                                   that.questionList=res.data.records
                                that.pages=res.data.pages
                                that.size=res.data.size
                                that.current=res.data.current
                                that.totala=res.data.total

                                   that.searchName=''
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });
       }else if(this.optionValue === 2){
      var that=this
           this.$axios.get('/tquestion/user', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { "current": 1 ,
                                           "size":10,
                                           "userName":that.searchName
                                          }
                             })
                             .then(function (res) {
                                 console.log(res.data);
                                   that.questionList=res.data.records
                                that.pages=res.data.pages
                                that.size=res.data.size
                                that.current=res.data.current
                                that.totala=res.data.total

                                that.searchName=''
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });
       }

    },
  
        CurrentChange(val) {
          var that=this
     

           this.$axios.get('/tquestion/all', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { "current": val ,
                                           "size":10,
                                          }
                             })
                             .then(function (res) {
                                 console.log(res.data);
                                   that.questionList=res.data.records
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
    var that=this
         this.userList=JSON.parse(sessionStorage.getItem("userInfo"))
        this.examMsg.userId=this.userList.userId

        this.$axios.get('/tquestion/all', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { "current": 1 ,
                                           "size":10,
                                            }
                             })
                             .then(function (res) {
                                 console.log(res.data);
                                that.questionList=res.data.records
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

<style scoped lang="scss">
.search{
    width:250px ;margin-right:40px
}
</style>
