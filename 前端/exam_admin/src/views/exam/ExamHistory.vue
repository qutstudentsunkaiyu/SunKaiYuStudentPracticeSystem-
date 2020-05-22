<template>
    <div>
        <el-col :span="24">
        <el-col :span="24"  >
            <el-table
                :data="examList"
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
                     property="totalTime"
                     label="总时间">
                   </el-table-column>

                    <el-table-column
                     property="useTime"
                     label="使用时间"  >
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
                    label="考试时间"
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
                       @click="seeExam(scope.row)"> 查看考试 </el-button> &nbsp; &nbsp;

               
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
name:"ExamHistory",
data(){
    return{
        userList:'',
          examList:'',

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
    seeExam(row){
        
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
     

           this.$axios.get('/exam/history', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { "current": val ,
                                           "size":5,
                                           "userId":that.userList.userId
                                          }
                             })
                             .then(function (res) {
                                 console.log(res.data);
                                   that.examList=res.data.records
                           
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
   this.$axios.get('/exam/history', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { "current": 1 ,
                                           "size":5,
                                           "userId":this.userList.userId
                                          }
                             })
                             .then(function (res) {
                                 console.log(res.data);
                                   that.examList=res.data.records

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

<style>

</style>
