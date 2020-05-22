<template>

    <div style="  overflow:scroll;">
        <el-col :span="24">
        <el-col :span="24"  >
            <el-input
               class="search"
             placeholder="请输入姓名"
             prefix-icon="el-icon-search"
             v-model="searchRealName">
           </el-input>

             <el-input
             placeholder="请输入学校"
             prefix-icon="el-icon-search"
            class="search"
             v-model="searchSchool">
            </el-input>
            <br><br>
            <el-input
               class="search"
             placeholder="请输入专业"
             prefix-icon="el-icon-search"
             v-model="searchProfession">
            </el-input>
           
             <el-button type="primary"  @click="search()" round>搜索</el-button>
        </el-col>
        <el-col :span="24"  >
            <el-table
                :data="userList"
                   highlight-current-row
               
                style="width: 1200px"
             
                max-height="700"
                >
            
                  <el-table-column
                 type="index"
                 width="50">
               </el-table-column>

                  <el-table-column
                     property="userName"
                     label="账户"
                     width="100">
                   </el-table-column>

                   <el-table-column
                     property="realName"
                     label="真实姓名">
                   </el-table-column>


                    <el-table-column
                     property="email"
                     label="邮箱"  width="200">
                    </el-table-column>
                    <el-table-column
                     property="school"
                     label="学校">
                    </el-table-column>
                      <el-table-column
                     property="profession"
                     label="专业">
                   </el-table-column>
                  <el-table-column label="  操作" width="200">
                    <template slot-scope="scope">
                      <el-button
                        size="mini"
                       
                       @click="editRole(scope.row)"> &nbsp; &nbsp;参加考试 </el-button> &nbsp; &nbsp;

                       <el-dialog title="考试试卷" :visible.sync="dialogFormVisible"  >
                           <el-select v-model="optionValue" placeholder="请选择">
                                <el-option
                                  v-for="item in tquestionList"
                                  :key="item.id"
                                  :label="item.tquestionName"
                                  :value="item.id">
                                </el-option>
                              </el-select>

                            <div slot="footer" class="dialog-footer">
                              <el-button @click="dialogFormVisible = false">取 消</el-button>
                              <el-button type="primary" @click="takeInvite()">确 定</el-button>
                            </div>
                        </el-dialog>

                      <!-- <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row)">删除</el-button> -->
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
name:"UserMsg",
data(){
    return{
      userList:'',
      pages:'',
      size:'',
      current:'',
      totala:'',
     currentRow: null,
        currentPage1: 1,

      searchName:'',
      searchRealName:'', 
      searchSchool:'',
      searchProfession:'', 
      searchRole:'',
      searchList:{},

   tquestionList:'',



    dialogFormVisible: false,
    changeRoleInfo:'',
    userRole:{},
//   options: [{
//           value: 1,
//           label: '管理员'
//         }, {
//           value: 2,
//           label: '教师'
//         }, {
//           value: 3,
//           label: '学生'
//         }],
         optionValue: "",
     }
},

 methods: {
     takeInvite(){
     
         this.dialogFormVisible = false
          this.inviteExam={}
     var that=this
        this.$set( this.inviteExam, "userIdStudent", this.changeRoleInfo.id)
        this.$set( this.inviteExam, "userIdTeacher",this.userInfo.userId)
        this.$set( this.inviteExam, "userIdStudentName", this.changeRoleInfo.realName)
        this.$set( this.inviteExam, "tqustionId", this.optionValue)
        this.$axios.post('/examInvite',this.inviteExam, {
             headers: {
                       "X-Token":sessionStorage.getItem("token")
                         },
                
            }).then(function (response) {
                 console.log(response);
                 if(response.data.code ==1){
                    that.$message(response.data.msg)
              
              
                        // lets.onsubmit("clickitem1")
               
                 }else{
                      that.$message(response.data.msg)
                 }
             
               }).catch(function (error) {
                 console.log(error);
               });
     },
     editRole(row){
         console.log(row)
         this.dialogFormVisible = true
      
              this.optionValu;
           this.changeRoleInfo=row
     },

  
        CurrentChange(val) {
          var that=this
             this.$axios.get('/user/all', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { "current": val ,
                                           "size":10,
                                            "search":'{}'}
                             })
                             .then(function (res) {
                                 console.log(res.data);
                                that.userList=res.data.records
                                that.pages=res.data.pages
                                that.size=res.data.size
                                that.current=res.data.current
                                that.totala=res.data.total
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });

      },



     search(){

         console.log( this.searchRole)
      if(this.searchName!==''){
      this.$set( this.searchList, "name", this.searchName)
      }
        if(this.searchRealName!==''){
      this.$set( this.searchList, "realName", this.searchRealName)
      }
        if(this.searchSchool!==''){
      this.$set( this.searchList, "school", this.searchSchool)
      }
        if(this.searchProfession!==''){
      this.$set( this.searchList, "profession", this.searchProfession)
      }
     
          this.$set( this.searchList, "roleName", "学生")


      console.log(this.searchList)

                var that=this
             this.$axios.get('/user/all', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { "current": 1 ,
                                           "size":10,
                                            "search":JSON.stringify(that.searchList)}
                             })
                             .then(function (res) {
                                 console.log(res.data);
                                 that.userList={}
                                that.userList=res.data.records
                                that.pages=res.data.pages
                                that.size=res.data.size
                                that.current=res.data.current
                                that.totala=res.data.total
                                console.log(  that.userList)
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });
                        this.searchName=''  
                       this.searchRealName='' 
                       this.searchSchool=''
                        this.searchProfession=''
                        this.searchRole=''
                     this.searchList={}
                            

     },

          handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
  


},
   created: function () {
     
        this.userInfo=JSON.parse(sessionStorage.getItem("userInfo"))
        var that=this
        console.log(this.userInfo)
        this.$axios.get('/user/all', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { "current": 1 ,
                                           "size":10,
                                            "search":"{'roleName':'学生'}"}
                             })
                             .then(function (res) {
                                 console.log(res.data);
                                that.userList=res.data.records
                                that.pages=res.data.pages
                                that.size=res.data.size
                                that.current=res.data.current
                                that.totala=res.data.total
                           

                             })
                             .catch(function (err) {
                                 console.log(err);
                             });

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
                                  console.log(  "sdfsdfsd");
                              console.log(  that.tquestionList);
                              
                                  console.log(  that. options);
                            
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });
    },
}
</script>

<style scoped lang="scss">
.search{
    width:250px ;margin-right:40px
}
</style>
