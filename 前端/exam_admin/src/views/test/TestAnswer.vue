<template>

    <div style="padding:20px;color:#000000	">
      <h1>{{examTitle}}</h1>
      <h2 style=" margin-left:60%"> 分值{{examMsga.totalScore}}     ||  使用时间 {{examMsga.useTime}} 分钟</h2>
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

          <el-radio v-model="scope.row.userAnswerOption"    label="a" class="marginl">{{scope.row.selectAItem}}</el-radio><br>
          <el-radio v-model="scope.row.userAnswerOption"    label="b"  class="marginl">{{scope.row.selectBItem}}</el-radio><br>
            <el-radio v-model="scope.row.userAnswerOption"    label="c"  class="marginl">{{scope.row.selectCItem}}</el-radio><br>
             <el-radio v-model="scope.row.userAnswerOption"    label="d"  class="marginl">{{scope.row.selectDItem}}</el-radio> <br>
      

               <p></p>    <p></p>
         <el-tag  v-if="scope.row.score > 0" type="success">正确</el-tag>  
      <el-tag  v-else-if="scope.row.score == 0" type="danger">错误</el-tag> 
       <span style="margin-left:10px">   正确答案 ： {{scope.row.correct }}</span>
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
    
        <p>       您的答案  : </p>
  
              <el-input
               type="textarea"
               :autosize="{ minRows: 2, maxRows: 4}"
               placeholder="请输入内容"
               v-model="scope.row.userAnswerWrite">
             </el-input>

       <p>   正确答案  : </p>
       <p style="margin-left:10px">{{scope.row.correct}}</p>
            
         
       

               <p></p>    <p></p>
   <hr style="height:1px;border:none;border-top:1px dashed #0066CC;" />
      </template>
      
    </el-table-column>
   
  </el-table>
           <el-button
           size="medinue"
          type="primary"
          @click="toList()">返回考试中心</el-button>
    </div>
</template>
</template>

<script>
export default {
name:"TestAnswer",
data(){
    return{
        examTitle:'',
        selectList:'',
        writeList:'',
        examId:'',
        examMsga:''
    }
},
methods:{
  toList(){
                 this.$router.push('/admin/exam/history')
  }
},
created:function(){
    var that=this
        this.examId=this.$route.query.examId
        this.examTitle=this.$route.query.testName
        console.log(this.examId)

             this.$axios.get('/examUserAnswer/select', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { examId:this.examId}
                             })
                             .then(function (res) {
                                 console.log(res.data);
                                that.selectList=res.data
                         
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });

  this.$axios.get('/examUserAnswer/write', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { examId:this.examId}
                             })
                             .then(function (res) {
                              
                                that.writeList=res.data
                                    console.log("sdf");
                            console.log(that.writeList);
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });

      this.$axios.get('/exam', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { examId:this.examId}
                             })
                             .then(function (res) {
                              
                                that.examMsga=res.data
                                    console.log("sdf");
                            console.log(that.examMsga);
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });


}

}
</script>

<style>

</style>
