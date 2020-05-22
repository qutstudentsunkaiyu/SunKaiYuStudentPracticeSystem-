<template>
 <div style="padding:20px;color:#000000	">
      <h1>{{examTitle}}    <span style="font-size:15px ;color:gray ;margin-left:50px">   考试时长: {{totalTime}} 分钟   || 使用时间： {{dataa}}  分钟</span> </h1>
      <br>

      <el-table
    :data="selectList"
    border
    style="width:1080px;margin-left:20%;">
    <el-table-column
      fixed="right"
      label=" 选择题"
    >
        <template slot-scope="scope"    style="border:5px solid red">
            <p class="titlea">  &nbsp; &nbsp;{{scope.row.title}}  &nbsp; &nbsp; &nbsp; &nbsp; <span style="color:gray;font-size:16px;"> ( {{scope.row.score}} 分)</span> </p>
    
         <p></p>

          <el-radio v-model="scope.row.correct" label="a" class="marginl">{{scope.row.selectAItem}}</el-radio><br>
          <el-radio v-model="scope.row.correct" label="b"  class="marginl">{{scope.row.selectBItem}}</el-radio><br>
            <el-radio v-model="scope.row.correct" label="c"  class="marginl">{{scope.row.selectCItem}}</el-radio><br>
             <el-radio v-model="scope.row.correct" label="d"  class="marginl">{{scope.row.selectDItem}}</el-radio> <br>
      

               <p></p>    <p></p>
   <hr style="height:1px;border:none;border-top:1px dashed #0066CC;" />
      </template>
      
    </el-table-column>
  </el-table>
  <p></p> <p></p>  <p></p>

  <hr style="height:5px;border:none;border-top:5px ridge green; width:1080px;margin-left:20%;" />
      <el-table
    :data="writeList"
    border
    style="width:1080px;margin-left:20%;margin-bottom:60px;">
    <el-table-column
      fixed="right"
   
      label="书面题"
    >
        <template slot-scope="scope"    style="border:5px solid red">
              <p class="titlea">  &nbsp; &nbsp;{{scope.row.title}}  &nbsp; &nbsp; &nbsp; &nbsp; <span style="color:gray;font-size:16px;"> ( {{scope.row.score}} 分)</span> </p>
    
        <p></p>
          请作答 : 
              <el-input
               type="textarea"
               :autosize="{ minRows: 2, maxRows: 4}"
               placeholder="请输入内容"
               v-model="scope.row.answer">
             </el-input>
       

               <p></p>    <p></p>
   <hr style="height:1px;border:none;border-top:1px dashed #0066CC;" />
      </template>
      
    </el-table-column>
   
  </el-table>
           <el-button
           size="medinue"
          type="primary"
          @click="toList()">提交试卷</el-button>
    </div>
</template>

<script>
export default {
name:"Test",
data(){
    return{
      useTime:0,
      totalTime:'',
      selectNum:'',
      writeNum:'',
       tquestionId:'',
       examMsg:{},
       userList:'',

       selectList:'',
       writeList:'',

       selectArray:[],
       writeArray:[],
   
    }
},
computed:{
  dataa(){
    return this.useTime
  }
},
methods:{

toList(){
  var that=this
    console.log(this.selectList)
     console.log(this.writeList)

     for(var i=0;i<this.selectList.length;i++){
       var item={}
       item.examId=parseInt(this.examMsg.id)
       item.testType=0
       item.userAnswerOption=this.selectList[i].correct
       item.tquestionTitleId=this.selectList[i].id

      
       this.selectArray[i]=item
     }
     console.log(this.selectArray)
            this.$axios.post('/examUserAnswer/select',this.selectArray, {
             headers: {
                       "X-Token":sessionStorage.getItem("token")
                         },
                
            }).then(function (response) {
                 console.log(response);
              
               }).catch(function (error) {
                 console.log(error);
               });


     for(var i=0;i<this.writeList.length;i++){
       var item={}
       item.examId=parseInt(this.examMsg.id)
       item.testType=1
       item.userAnswerWrite=this.writeList[i].answer
       item.tquestionTitleId=this.writeList[i].id

      
       this.writeArray[i]=item
     }

     console.log(this.writeArray)

            this.$axios.post('/examUserAnswer/write',this.writeArray, {
             headers: {
                       "X-Token":sessionStorage.getItem("token")
                         },
                
            }).then(function (response) {
                 console.log(response);
                 if(response.data.code ==1){
                    that.$message({
                        type:"success",
                        message:"试卷提交成功"
                    })
              
                       setTimeout(function(){
                          that.$router.push('/admin/exam/history')

                                 that.$axios.get('/examUserAnswer/result', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: {"examId":   that.examMsg.id }
                             })
                             .then(function (res) {
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });
                       },1000)
                        // lets.onsubmit("clickitem1")
                   
               
                 }else{
                      that.$message(response.data.msg)
                 }
               }).catch(function (error) {
                 console.log(error);
               });

      


}
},
created:function(){

     console.log(this.$route.query.tquestionId)
    this.totalTime=this.$route.query.totalTime

     this.selectNum=this.$route.query.selectNum
      this.writeNum=this.$route.query.writeNum
   
     this.tquestionId=this.$route.query.tquestionId
     this.examMsg.id=this.$route.query.examId 
    this.examTitle =this.$route.query.examTitle
  
    var that=this
    this.userList=JSON.parse(sessionStorage.getItem("userInfo"))
           this.examMsg.userId=this.userList.userId   
     console.log(this.examMsg)



               this.$axios.get('/exam/select', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { "tquestion":  parseInt(this.tquestionId)   ,
                                           "num":parseInt( this.selectNum)
                                          }
                             })
                             .then(function (res) {
                                  that.selectList=res.data
                                  console.log(that.selectList)
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });

                  this.$axios.get('/exam/write', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { "tquestion":  parseInt(this.tquestionId)   ,
                                           "num": parseInt(this.writeNum)  ,
                                          }
                             })
                             .then(function (res) {
                                  that.writeList=res.data
                                  console.log(that.writeList)
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });

                             setInterval(function(){
                                that.useTime=that.useTime+1 
                                
                             },60000)

}
}
</script>

<style scoped lang="scss">
.titlea{
  margin-left:20px;color:#000000;
  font-size:16px;
}
.marginl{
    margin-left:20px;
    color: #000000;
    font-size: 12px;
}

</style>
