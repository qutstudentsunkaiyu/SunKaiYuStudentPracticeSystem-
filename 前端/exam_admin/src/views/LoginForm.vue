<template>
     <div style="width:100% ;vertical-align:middle; text-align:center ; ">
    <div style="width:500px;     display:inline-block; ">
       
  <el-form   :model="ruleForm2" status-icon :rules="rules2"  cell-style="line-height"   ref="ruleForm2" label-width="100px" >
    <el-form-item label="账户" prop="name"  >
         <el-input type="text" v-model="ruleForm2.name" autocomplete="off" placeholder="请输入账户" ></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="pass"  >
         <el-input type="password" v-model="ruleForm2.pass" autocomplete="off"  placeholder="请输入密码" ></el-input>
    </el-form-item>

    <el-form-item>
         <el-button type="primary" @click="submitForm('ruleForm2')">确认</el-button>
    <el-button @click="resetForm('ruleForm2')">重置</el-button>
  </el-form-item>
</el-form>
    </div>
  </div>
</template>

<script>
export default {
name:"LoginForm",
data(){
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
          callback();
        }
      };
      return{
             ruleForm2: {
             name:'',
             pass: '',
             },
        
          rules2: {
            name:[ { validator: validateName, trigger: 'blur' }],
            pass: [
                 { validator: validatePass, trigger: 'blur' }
          ],
         
        }
      }
   
},
  methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
            var that=this
          if (valid) {
                  console.log(sessionStorage.getItem("token"))
              console.log(this.ruleForm2.name)
          this.$axios.post('/user/login', {
       
               name:this.ruleForm2.name,
               passwd:this.ruleForm2.pass,
            }).then(function (response) {
                 console.log(response);
                 if(response.data.code == 1){
              
                    sessionStorage.setItem("token",response.data.msg)
                console.log(sessionStorage.getItem("token"))
              
                       that.$message({
                             type: 'success',
                             message: '登录成功!'
                           });
                           // lets.onsubmit("clickitem1")
                            that.$axios.get('/user/info', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: {   }
                             })
                             .then(function (res) {
                                 console.log(res.data);
                                 sessionStorage.setItem("userInfo",JSON.stringify(res.data))
                                 that.$router.push("/admin")
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });
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

<style>

</style>
