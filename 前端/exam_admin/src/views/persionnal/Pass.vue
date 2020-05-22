<template>
    <div>
           
  <el-form   :model="ruleForm2" status-icon :rules="rules2"  cell-style="line-height"   ref="ruleForm2" label-width="100px" >
    <el-form-item label="旧密码" prop="name"  >
         <el-input type="password" v-model="ruleForm2.oldPass"  autocomplete="off" ></el-input>
    </el-form-item>
    <el-form-item label="新密码" prop="pass"  >
         <el-input type="password" v-model="ruleForm2.newPass" autocomplete="on"  placeholder="必填" ></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="checkPass">
         <el-input type="password" v-model="ruleForm2.checkPass" autocomplete="off"  placeholder="必填" ></el-input>
    </el-form-item>
    <el-form-item>
         <el-button type="primary" @click="submitForm('ruleForm2')">提交</el-button>
  
  </el-form-item>
</el-form> 
    </div>
</template>

<script>
export default {
 name:"Pass",
 data(){
           var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm2.checkPass !== '') {
            this.$refs.ruleForm2.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm2.newPass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
          return {
        ruleForm2: {
          userId:'',  
          oldPass: '',
          newPass: '',
          checkPass:''
        },
        userInfo:'',
        rules2: {
          newPass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],  
        }
      };
 },
     methods: {
      submitForm(formName) {
             var that=this
        this.$refs[formName].validate((valid) => {
     
          if (valid) {
              console.log(this.ruleForm2.name)
          this.$axios.put('/user/pass',this.ruleForm2, {
             headers: {
                         "X-Token":sessionStorage.getItem("token")
                     },
            }).then(function (response) {

                 console.log(response.data.msg);
                 if(response.data.code === '1' ){
                   that.$message({ 
                        message: response.data.msg,
                 type: 'success'})
                 }else {
                    that.$message.error(response.data.msg);
                    
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
        this.$refs[formName].resetFields();
      }
    },
       created: function () {
     
        this.userInfo=JSON.parse(sessionStorage.getItem("userInfo"))
          this.ruleForm2.userId=this.userInfo.userId
    },
 
}
</script>

<style>

</style>
