
<template>
   
 <el-form   :model="examMsg"    cell-style="line-height"   :rules="rules2"  status-icon  inline-message  ref="ruleForm2"  label-width="200px" >

    <el-form-item label="试卷类型："  >
        <el-select v-model="examMsg.subjectId" placeholder="请选择">
           <el-option
             v-for="item in options"
             :key="item.value"
             :label="item.label"
             :value="item.value">
           </el-option>
             </el-select>
    </el-form-item>

    <el-form-item label="选择题数目："    prop="selectNum" >
         <el-input type="number" v-model="examMsg.selectNum" autocomplete="on"   ></el-input>
    </el-form-item>

    <el-form-item label="书面题数目："    prop="writeNum" >
         <el-input type="number" v-model="examMsg.writeNum" autocomplete="on"   ></el-input>
    </el-form-item>
  
  <el-form-item label="考试时长："    prop="totalTime" >
         <el-input type="number" v-model="examMsg.totalTime" autocomplete="on"   ></el-input>
    </el-form-item>
  <br>
    <el-form-item>
         <el-button type="primary" @click="submitForm('ruleForm2')">保存</el-button>
  </el-form-item>
</el-form>
</template>

<script>
export default {
name:"Exam",
data(){
           return{
             examMsg:{
               userId:'',
               subjectId:1,
               tquestionId:0,
               totalTime:'',
               examType:0,
               selectNum:'',
               writeNum:'',
             },
             examTestMsg:'',
              options: [{
                  value: 1,
                  label: 'JAVA'
                }, {
                  value: 2,
                  label: 'Andorid'
                }, {
                  value: 3,
                  label: 'Python'
                }],
         
             userInfo:"",
           
            rules2: {
          subjectId: [
               { required: true, message: '请选择学科', trigger: 'blur' },
           ],
            totalTime: [
              { required: true, message: '请输入考试时间', trigger: 'blur' },
          ],
           selectNum: [
               { required: true, message: '请输入选择题数目', trigger: 'blur' },
           ],
            writeNum: [
              { required: true, message: '请输入书面题数目', trigger: 'blur' },
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
                this.$axios.post('/exam',this.examMsg, {
             headers: {
                       "X-Token":sessionStorage.getItem("token")
                         },
                
            }).then(function (response) {
                 console.log(response);
                           that.$message({
                   type:"success",
                   message:"即将开始考试"
                    })
                    
                that.examTestMsg=response.data
                  console.log(    that.examTestMs);
                  that.$router.push({
                      path: '/test',
                      query: {
                        examId: that.examTestMsg.id ,
                        tquestionId: 0,
                               totalTime:  that.examMsg.totalTime,
                        selectNum:that.examMsg.selectNum,
                          writeNum:that.examMsg.writeNum,
                        examTitle:"随机试题"

                   }
        })
              
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
 created:function(){
         this.userInfo=JSON.parse(sessionStorage.getItem("userInfo"))
          this.examMsg.userId=this.userInfo.userId
 }
}
</script>

<style>

</style>
