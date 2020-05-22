



<template >
     <div style="  overflow:scroll;">
        <el-col :span="24">
    


        <el-col :span="24"  >
            <el-table
                :data="examInviteList"
                   highlight-current-row
               
                style="width:1800px"
             
                max-height="2500px"
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
                     property="teacherName"
                     label="老师">
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
                    label="时间"
                    width="350">
                    <template slot-scope="scope">
                      <i class="el-icon-time"></i>
                      <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
                    </template>
                  </el-table-column>

                  <el-table-column label="  操作" width="400" style="padding-lefg:50px">
                    <template slot-scope="scope">

                      <el-button 
                      v-if="scope.row.isExam == 0"
                      style="margin-let:50px; float:left"
                        size="mini"
                       type="success"
                       @click="takeExam(scope.row)">参加考试 </el-button> &nbsp; &nbsp;
           
                     <el-button 
                            style="margin-let:50px; float:left"
                      v-if="scope.row.isExam == 1"
                      disabled="false"
                        size="mini"
                       type="success"
                       >已参加 </el-button> &nbsp; &nbsp;
     <el-button 
                            style="margin-let:50px; float:left"
                      v-if="scope.row.isExam == 3"
                 
                        size="mini"
                       type="primary"
                       @click="takeLook(scope.row)">已批阅 </el-button> &nbsp; &nbsp;
               
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

        examInviteList:'',
        hidea:false,


      pages:'',
      size:'',
      current:'',
      totala:'',
     currentRow: null,
        currentPage1: 1,

      searchName:'',


    }
},
methods:{
    takeExam(row){
// 注册考试，传递tquestionId，跳转靠考试页面
  this.examMsg.subjectId=row.subjectId
  this.examMsg.examType=1
  this.examMsg.tquestionId=row.tqustionId
  this.examMsg.totalTime=row.totalTime

  this.examMsg.userId=this.userList.userId
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
                console.log("ddddd")
                console.log(that.examMsg)

                      that.$axios.get('/examInvite/update', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { "examInviteId": row.ids,
                                           "states":1,
                                           "examId":that.examMsg.id
                                          }
                             })
                             .then(function (res) {
                              
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });

                  that.$router.push({
                      path: '/test',
                      query: {
                        examId: that.examMsg.id ,
                        tquestionId: row.tqustionId,
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
    takeLook(row){
                this.$router.push({
                      path: '/testAnswer',
                      query: {
                        examId: row.examId,
                        testName:row.tquestionName,
                     
                   }
                })
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
          console.log("asdasd")
       console.log(this.userList)
   this.$axios.get('/examInvite/list', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { "current": 1 ,
                                           "size":10,
                                           "userId":this.userList.userId,
                                           "types":1
                                          }
                             })
                             .then(function (res) {
                                 console.log(res.data);
                                   that.examInviteList=res.data.records
                                that.pages=res.data.pages
                                that.size=res.data.size
                                that.current=res.data.current
                                that.totala=res.data.total
                                  
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });
                             console.log(this.examList)
}
}
</script>

<style scoped lang="scss">
.search{
    width:250px ;margin-right:40px
}
</style>











