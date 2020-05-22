<template>
    <div style="padding:20px;color:#000000	">
      <h1>{{tquestionName}}</h1>
      <br>

   
  <p></p> 

  <hr style="height:5px;border:none;border-top:5px ridge green; width:1080px;margin-left:20%;" />
      <el-table
    :data="writeData"
    border
    style="width:1080px;margin-left:20%;margin-bottom:60px;">
    <el-table-column
      fixed="right"
   
      label="书面题"
    >
        <template slot-scope="scope"    style="border:5px solid red">
           标题
           <p class="p_sytle"> {{scope.row.title}}: </p>
   
        <p></p>
            考生答案  :
               <p class="p_sytle"> &nbsp; &nbsp;{{scope.row.userAnswer}}</p>
               <p>总分值:{{scope.row.totalScore}}</p>
         
                       分    值  :  <el-input v-model="scope.row.userScore"  type="number" style="width:100px;clear:right;"  placeholder="请输入内容"></el-input><p></p>
      
      
       <p></p>
   <hr style="height:1px;border:none;border-top:1px dashed #0066CC;" />
      </template>
      
    </el-table-column>
   
  </el-table>
           <el-button
           size="medinue"
          type="primary"
          @click="toSave()">结束批阅</el-button>
    </div>
</template>

<script>
export default {
 name:"ExamResultPreview",
 data(){
     return{
         examId:'',
         writeData:'',
         tquestionName:'',
         tquestionId:'',
     }
 },
 methods:{
     toSave(){
     
var that=this;
              this.$axios.put('/examUserAnswer/save',this.writeData, {
             headers: {
                       "X-Token":sessionStorage.getItem("token")
                         },
                            params: {examId:this.examId}
                
            }).then(function (response) {
                 console.log(response);
                 if(response.data.code ==1){
                    that.$message({ 
                                    message: response.data.msg,
                                 type: 'success'})

                       that.$router.push("/admin/question/invitelist")
              
                        // lets.onsubmit("clickitem1")
               
                 }else{
                      that.$message(response.data.msg)
                 }
               }).catch(function (error) {
                 console.log(error);
               });
     },

          handleEdit(index, row) {
               var that=this
        console.log(index, row);

          
      },

      geteData(){
           var that=this
       
               that.writeData=""
      

          this.$axios.get('/examUserAnswer/user_write', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: {examId:that.examId}
                             })
                             .then(function (res) {
                                 console.log(res);
                                that.writeData=res.data
                          
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });
      }

 },
 created:function(){
     console.log(this.$route.query.tquestionId)
     this.tquestionName=this.$route.query.tquestionName
     this.tquestionId=this.$route.query.tquestionId
     this.examId=this.$route.query.examId


   this.geteData()
     console.log("as")
  console.log(this.selectData)

 }
}
</script>

<style scoped lang="scss">
    
.selectDiv{
   margin-left: 30%;
    width: 300px;
    font-size: 20px;
}

.p_sytle{
  font-size:18px;
  margin-left: 30px;
  font-weight:500;
}

</style>
