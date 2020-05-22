<template >
     <div style="  overflow:scroll;">
        <el-col :span="24">
    


        <el-col :span="24"  >
            <el-table
                :data="examInviteList"
                   highlight-current-row
               

                >
            
                  <el-table-column
                 type="index"
                 width="100">
               </el-table-column>

                  <el-table-column
                     property="tquestionName"
                     label="试题名称"
                     width="260">
                   </el-table-column>

                   <el-table-column
                     property="studentName"
                     label="学生" width="260">
                   </el-table-column>

                
             
                    <el-table-column
                     property="totalTime"
                     label="考试时长" width="260">
                    </el-table-column>


                  <el-table-column
                    label="学科"
                  width="260">
                    
                    <template slot-scope="scope">
                       <div slot="reference" class="name-wrapper">
                          <el-tag size="medium"> {{ scope.row.subjectName }} </el-tag>
                        </div>
                    
                    </template>
                  </el-table-column>
                    <el-table-column
                    label="时间"
                    width="240">
                    <template slot-scope="scope">
                      <i class="el-icon-time"></i>
                      <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
                    </template>
                  </el-table-column>

                  <el-table-column label="  操作" width="500">
                    <template slot-scope="scope">

                      <el-button 
                      v-if="scope.row.isExam == 1"
                      style="float:left;margin-left:30px"
                        size="mini"
                       type="success"
                       @click="takeReview(scope.row)">批阅 </el-button> &nbsp; &nbsp;
           
                     <el-button 
                      v-if="scope.row.isExam == 0"
                      disabled="false"
                        size="mini"
                       type="success"
                        style="float:left;margin-left:30px"
                       @click="takeReview(scope.row)">批阅 </el-button> &nbsp; &nbsp;

                             <el-button 
                      v-if="scope.row.isExam == 3"
                      disabled="false"
                        size="mini"
                       type="success"
                        style="float:left;margin-left:30px"
                       @click="takeReview(scope.row)">批阅 </el-button> &nbsp; &nbsp;

               
                     <el-button 
                      v-if="scope.row.isCorrection == 1"
                         style="float:left;margin-left:30px"
                        size="mini"
                       type="success"
                       @click="toLook(scope.row)">查看 </el-button>
           
                     <el-button 
                      v-if="scope.row.isCorrection == 0"
                      disabled="false"
                        size="mini"
                       type="success"
                        style="float:left;margin-left:30px"
                       @click="toLook(scope.row)">查看 </el-button>


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
    takeReview(row){


          this.$router.push({
                      path: '/tquestion/preview_exam',
                      query: {
                        examId: row.examId ,
                        tquestionId: row.tqustionId,
                              
                        tquestionName:row.tquestionName,                 
                        }
           })

            
    
    },
    toLook(row){
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
                                           "types":2
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











