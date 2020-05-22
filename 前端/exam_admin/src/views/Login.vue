<template>
      <div class="hello">
   
  <p  class="p1">   {{ msg }}  </p>
  <el-radio-group v-model="tabPosition" style="margin-bottom: 30px;" class="radioa">
  <el-radio-button   @click.native.prevent="clickitem(1)"  label="login"  >    登录 </el-radio-button>
  <el-radio-button   @click.native.prevent="clickitem(2)"   label="reg" >  注册 </el-radio-button>   
  </el-radio-group>

  <router-view/>

  </div>
</template>

<script>
export default {
  name:"Login",
  data () {
    return {
      msg: '欢迎登录考试系统',
        tabPosition: 'login'
    }
  },
  methods:{
     clickitem (e) { 
    console.log(e)
     if(e == 1){
         this.tabPosition='login'
         this.$router.push('/exam/login')
     }else if(e == 2){
             this.tabPosition='reg'
                    this.$router.push('/exam/reg')
     }
 },
  }

}
</script>

<style  scoped lang="scss">
.p1{
    margin-top: 60px;
    font-size:35px
}
.radioa{
     margin-top: 10px
}

</style>
