<template>
  <div style="width:100% ;vertical-align:middle; text-align:center ; ">
    <div style="width:500px;     display:inline-block; ">
       
  <el-form   :model="ruleForm2" status-icon :rules="rules2"  cell-style="line-height"   ref="ruleForm2" label-width="100px" >
    <el-form-item label="账户" prop="name"  >
         <el-input type="text" v-model="ruleForm2.name" autocomplete="off" placeholder="必填" ></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="pass"  >
         <el-input type="password" v-model="ruleForm2.pass" autocomplete="on"  placeholder="必填" ></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="checkPass">
         <el-input type="password" v-model="ruleForm2.checkPass" autocomplete="off"  placeholder="必填" ></el-input>
    </el-form-item>
    <el-form-item label="真实姓名" prop="realName"  >
         <el-input type="text" v-model="ruleForm2.realName" autocomplete="off" ></el-input>
    </el-form-item>
    <el-form-item label="手机号" prop="phone"  >
         <el-input type="text" v-model="ruleForm2.phone" autocomplete="off" ></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email"  >
         <el-input type="text" v-model="ruleForm2.email" autocomplete="off" ></el-input>
    </el-form-item>
       <el-form-item label="学校" prop="school"  >
         <el-input type="text" v-model="ruleForm2.school" autocomplete="off" ></el-input>
    </el-form-item>
    <el-form-item label="年龄" prop="age" >
         <el-input v-model.number="ruleForm2.age"></el-input>
    </el-form-item>
    <el-form-item>
         <el-button type="primary" @click="submitForm('ruleForm2')">提交</el-button>
    <el-button @click="resetForm('ruleForm2')">重置</el-button>
  </el-form-item>
</el-form>
    </div>
  </div>
</template>

<script>
  export default {
       name:"Register",
    data() {

           var checkPhone = (rule, value, callback) => {
      if (!value) {
          callback();
        } else {
          const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
          console.log(reg.test(value));
          if (reg.test(value)) {
            callback();
          } else {
            return callback(new Error('请输入正确的手机号'));
          }
        }
        
      };
           var validateName = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入账号'));
        } else {
         if(value.length < 6){
               callback(new Error('不能少于6位'));
         }else if(value.length >12){
              callback(new Error('不能超过12位'));
         }
          callback();
        }
      };
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
        } else if (value !== this.ruleForm2.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        ruleForm2: {
          name:'',
          pass: '',
          checkPass: '',
          realName:'',
          phone:'',
          email:'',
          school:'',
          age: ''
        },
        rules2: {
          email: [
               {
               type: 'email',//要检验的类型（number，email，date等）
               message: '请输入正确的邮箱地址',
               trigger: ['blur', 'change']
               }
           ],
           name:[ { validator: validateName, trigger: 'blur' }],
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
            phone: [
            { validator: checkPhone, trigger: 'blur' }
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
          this.$axios.post('/user/register', {
       
               name:this.ruleForm2.name,
               passwd:this.ruleForm2.pass,
               realName:this.ruleForm2.realName,
               phone:this.ruleForm2.phone,
               email:this.ruleForm2.email,
               school:this.ruleForm2.school,
               age: this.ruleForm2.age
            }).then(function (response) {
                 console.log(response);
                 if(response.data.code ==1){
                    that.$message({
                      type:"success",
                      message:response.data.msg
                    })
              
              
                        // lets.onsubmit("clickitem1")
               
                 }else{
                      that.$message(response.data.msg)
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
    }
  }
</script>

<style  scoped lang="scss">

</style>
