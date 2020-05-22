<template>

 <el-form   :model="ruleForm2"    cell-style="line-height"   :rules="rules2"  status-icon  inline-message  ref="ruleForm2"  label-width="200px" >

    <el-form-item label="用 户 名"  >
         <el-input type="text" v-model="ruleForm2.name" autocomplete="off"  disabled></el-input>
    </el-form-item>
    <el-form-item label="手 机 号"    prop="phone" >
         <el-input type="text" v-model="ruleForm2.phone" autocomplete="on"   ></el-input>
    </el-form-item>

     <el-form-item label="邮箱" prop="email"  >
         <el-input type="text" v-model="ruleForm2.email" autocomplete="off" ></el-input>
    </el-form-item>
  
   <el-form-item label="真 实 姓 名" prop="realName"  >
         <el-input type="text" v-model="ruleForm2.realName" autocomplete="off" ></el-input>
    </el-form-item>
       <el-form-item label="学  校" prop="school"  >
         <el-input type="text" v-model="ruleForm2.school" autocomplete="off" ></el-input>
    </el-form-item>
    <el-form-item label="年  龄" prop="age" >
         <el-input type="number" v-model="ruleForm2.age"></el-input>
    </el-form-item>
       <el-form-item label="专  业" prop="profession" >
         <el-input v-model="ruleForm2.profession"></el-input>
    </el-form-item>
  <br>
    <el-form-item>
         <el-button type="primary" @click="submitForm('ruleForm2')">保存</el-button>
  </el-form-item>
</el-form>
  
 
</template>

<script>
export default {
 name:"Message",
 data(){
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
           return{
             ruleForm2: {
              id:'',   
              name:'',
               realName:'',
               phone:'',
               email:'',
               school:'',
               age: '',
               profession:''
                  },
             userInfo:"",
           
            rules2: {
          email: [
               {
               type: 'email',//要检验的类型（number，email，date等）
               message: '请输入正确的邮箱地址',
               trigger: ['blur', 'change']
               }
           ],
            phone: [
            { validator: checkPhone, trigger:['blur', 'change']}
          ],
         
        }
      }
    },
    methods:{
           submitForm(formName) 
           {
         this.$refs[formName].validate((valid) => {
     
          if (valid) {

             var that=this
        console.log("SDfsdf")
              console.log(this.ruleForm2.name)
          this.$axios.put('/user/up',this.ruleForm2, {
             headers: {
                       "X-Token":sessionStorage.getItem("token")
                         },
           
                
            
            }).then(function (response) {
                 console.log(response);
                 if(response.data.code ==1){
                    that.$message({
               message: response.data.msg,
                 type: 'success'
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

    },
   created: function () {
     
        this.userInfo=JSON.parse(sessionStorage.getItem("userInfo"))
        var that=this
        console.log(this.userInfo)
        this.$axios.get('/user/person', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: { "id": that.userInfo.userId }
                             })
                             .then(function (res) {
                                 console.log(res.data);
                                that.ruleForm2.id=res.data.id
                                that.ruleForm2.name=res.data.name
                                that.ruleForm2.realName=res.data.realName
                                that.ruleForm2.phone=res.data.phone
                                that.ruleForm2.email=res.data.email
                                that.ruleForm2.school=res.data.school
                                that.ruleForm2.age=res.data.age
                                 that.ruleForm2.profession=res.data.profession

                             })
                             .catch(function (err) {
                                 console.log(err);
                             });
    },
 
}
</script>

<style>

</style>
