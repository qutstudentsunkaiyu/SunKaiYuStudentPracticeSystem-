<template>
    <div  >
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="试题名称" prop="name">
         <el-input v-model="ruleForm.name"></el-input>
         </el-form-item>
         <el-form-item label="试卷类型" prop="ruleForm.subjectId">
              <el-select v-model="ruleForm.subjectId" placeholder="请选择试题类型" size="medium" style="width:500px">
                                       <el-option
                                         v-for="item in options"
                                         :key="item.value"
                                         :label="item.label"
                                         :value="item.value">
                                       </el-option>
               </el-select>
         </el-form-item>
         <el-form-item label="选择题数量" prop="optionNum">
             <el-input v-model="ruleForm.optionNum"  type="number"></el-input>
         </el-form-item>
         <el-form-item label="书面题数量" prop="writeNum">
               <el-input v-model="ruleForm.writeNum" type="number"></el-input>
         </el-form-item>
             <el-form-item label="考试时长" prop="totalTime">
               <el-input v-model="ruleForm.totalTime" type="number"></el-input>
         </el-form-item>

  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
    <el-button @click="resetForm('ruleForm')">重置</el-button>
  </el-form-item>
</el-form>
<div style="border:1px solid red; padding:5px">
<h3 >上传选择题</h3>

<form action="http://localhost:8082/tquestionItemSelect/import" method="post" enctype="multipart/form-data"><!--enctype="multipart/form-data"是必须的-->
 
    <input type="file" name="file"  class="file">

  <input type="hidden" name="tId" v-model="tId"><br>
  <input type="submit" id="btnSub" value=" 提 交 "  class="button ">
   <a  style="float:left" href=" http://localhost:8082/static/选择题模板.xls"> 下载模板</a>
</form>
</div>
<br>
<div style="border:1px solid green; padding:5px">
<h3>上传书面题 </h3>

<form action="http://localhost:8082/tquestionItemWrite/import" method="post" enctype="multipart/form-data"><!--enctype="multipart/form-data"是必须的-->
 
    <input type="file" name="file"  class="file">

  <input type="hidden" name="tId" v-model="tId"><br>
  <input type="submit" id="btnSub" value=" 提 交 "  class="button ">
  <a href=" http://localhost:8082/static/书面题模板.xls"  style="float:left"> 下载模板</a>
</form>
</div>

    </div>
</template>

<script>
export default {
name:"Question",
  data() {
      return {
          userInfo:"",
    tId:"",
       options: [{
          value: 1,
          label: 'JAVA'
        }, {
          value: 2,
          label: 'Android'
        }, {
          value: 3,
          label: 'Python'
        }],
        optionValue: "",

        ruleForm: {
          userId:'',
          name: '',
          subjectId: '',
          optionNum:'',
          writeNum:'',
          totalTime:'',

        },
        rules: {
          name: [
            { required: true, message: '请输入考试名称', trigger: 'blur' },
          
          ],
          subjectId: [
            { required: true, message: '请选择考试类型', trigger: 'change' }
          ],
             optionNum: [
            { required: true, message: '请输入选择题数量', trigger: 'blur' },
          
          ],
             writeNum: [
            { required: true, message: '请输入书面题数量', trigger: 'blur' },
          
          ],
             totalTime: [
            { required: true, message: '请输入考试时长', trigger: 'blur' },
          
          ],

       
        }
      };
    },
    methods: {
        submita(){

        },
      submitForm(formName) {
          var that=this
        this.$refs[formName].validate((valid) => {
          if (valid) {
                  this.$axios.post('/tquestion',this.ruleForm, {
             headers: {
                       "X-Token":sessionStorage.getItem("token")
                         },
                
            }).then(function (response) {
                 console.log(response);
                  
                     that.$message({   message: response.data.msg,
                 type: 'success'})
                    that.tId=response.data.id
                    if(response.data.code === '1'){
                         sessionStorage.setItem("tquestion",JSON.stringify(that.ruleForm) )
                         sessionStorage.setItem("tId", that.tId)
                    }
      
               }).catch(function (error) {
                 console.log(error);
               });
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.ruleForm={}
        this.ruleForm.userId=this.userInfo.userId  
        sessionStorage.removeItem("tquestion")
        sessionStorage.removeItem("tId")
      }
    },
      created: function () {
          
     
           this.userInfo=  JSON.parse(sessionStorage.getItem("userInfo") ) 
             this.ruleForm.userId=this.userInfo.userId   
          if( sessionStorage.getItem("tquestion") !== null) {
                  this.ruleForm=  JSON.parse(sessionStorage.getItem("tquestion") ) 
          } 
          if( sessionStorage.getItem("tId") !== null){
              this.tId= JSON.parse(sessionStorage.getItem("tId") )   
          }
            this.ruleForm.userId=this.userInfo.userId   
            
    },
}
</script>

<style scoped lnag="scss">
.file {
    position: relative;
    display: inline-block;
    background: #D0EEFF;
    border: 1px solid #99D3F5;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color: #1E88C7;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
}
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background: #AADFFD;
    border-color: #78C3F3;
    color: #004974;
    text-decoration: none;
}

.button{
    width: 60px;
    display: inline-block;
    outline: none;
    cursor: pointer;
    text-align: center;
    text-decoration: none;
    font: 14px/100% Arial, Helvetica, sans-serif;
    padding: 5px 2px 4px;
    text-shadow: 0 1px 1px rgba(0,0,0,.3);
    -webkit-border-radius: 5px; 
    -moz-border-radius: 5px;
    border-radius: 5px;
    -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.2);
    -moz-box-shadow: 0 1px 2px rgba(0,0,0,.2);
    box-shadow: 0 1px 2px rgba(0,0,0,.2);
    color: #d9eef7;
    border: solid 1px #0076a3;
    background: #0095cd;
    background: -webkit-gradient(linear, left top, left bottom, from(#0095cc), to(#00678e));
    background: -moz-linear-gradient(top, #00adee, #00678e);
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#00adee', endColorstr='#00678e');
}
.button:hover{
    background: #007ead;
    background: -webkit-gradient(linear, left top, left bottom, from(#00678e), to(#0095cc));
    background: -moz-linear-gradient(top, #00678e, #0095cc);
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#00678e', endColorstr='#0095cc');
}

</style>
