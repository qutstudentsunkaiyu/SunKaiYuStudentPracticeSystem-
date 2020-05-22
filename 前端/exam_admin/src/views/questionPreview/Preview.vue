<template>
    <div style="padding:20px;color:#000000	">
      <h1>{{tquestionName}}</h1>
      <br>

      <el-table
    :data="selectData"
    border
    style="width:1080px;margin-left:20%;">
    <el-table-column
      fixed="right"
   
      label="选择题"
    >
        <template slot-scope="scope"    style="border:5px solid red">
           标题 : <el-input v-model="scope.row.title"  style="width:900px;clear:right;"  placeholder="请输入内容"></el-input><p></p>
         <p></p>
           选项A :  <el-input v-model="scope.row.selectAItem"  style="width:800px;clear:right;"  placeholder="请输入内容"></el-input><p></p>
           选项B :  <el-input v-model="scope.row.selectBItem"  style="width:800px;clear:right;"  placeholder="请输入内容"></el-input><p></p>
           选项C :  <el-input v-model="scope.row.selectCItem"  style="width:800px;clear:right;"  placeholder="请输入内容"></el-input><p></p>
           选项D :  <el-input v-model="scope.row.selectDItem"  style="width:800px;clear:right;"  placeholder="请输入内容"></el-input><p></p>
          <p></p>    <p></p>
           正确选项  : <el-input v-model="scope.row.correct"   style="width:100px;clear:right;"  placeholder="请输入内容"></el-input><p></p>
           分    值  :  <el-input v-model="scope.row.score"  type="number" style="width:100px;clear:right;"  placeholder="请输入内容"></el-input><p></p>
      
      
        <el-button
         size="medinue" style="margin-left:20%;margin-right:30px;"
          @click="handleEdit(scope.$index, scope.row)">保存</el-button>
        <el-button
           size="medinue"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
               <p></p>    <p></p>
   <hr style="height:1px;border:none;border-top:1px dashed #0066CC;" />
      </template>
      
    </el-table-column>
  </el-table>
  <p></p> <p></p>  <p></p>

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
           标题 :
           <el-input
               type="textarea"
               :autosize="{ minRows: 2, maxRows: 4}"
               placeholder="请输入内容"
               v-model="scope.row.title">
             </el-input>
        <p></p>
            正确答案  : 
              <el-input
               type="textarea"
               :autosize="{ minRows: 2, maxRows: 4}"
               placeholder="请输入内容"
               v-model="scope.row.answer">
             </el-input>
                       分    值  :  <el-input v-model="scope.row.score"  type="number" style="width:100px;clear:right;"  placeholder="请输入内容"></el-input><p></p>
      
      
        <el-button
         size="medinue" style="margin-left:20%;margin-right:30px;"
          @click="handleEditWrite(scope.$index, scope.row)">保存</el-button>
        <el-button
           size="medinue"
          type="danger"
          @click="handleDeleteWrite(scope.$index, scope.row)">删除</el-button>
               <p></p>    <p></p>
   <hr style="height:1px;border:none;border-top:1px dashed #0066CC;" />
      </template>
      
    </el-table-column>
   
  </el-table>
           <el-button
           size="medinue"
          type="primary"
          @click="toList()">结束预览</el-button>
    </div>
</template>

<script>
export default {
 name:"Preview",
 data(){
     return{
         selectData:'',
         writeData:'',
         tquestionName:'',
         tquestionId:'',
     }
 },
 methods:{
     toList(){
       this.$router.push("/admin/question/list")
     },

          handleEdit(index, row) {
               var that=this
        console.log(index, row);

                 this.$axios.put('/tquestionItemSelect',row, {
             headers: {
                       "X-Token":sessionStorage.getItem("token")
                         },
                
            }).then(function (response) {
                 console.log(response);
                 if(response.data.code ==1){
                    that.$message({ 
                                    message: response.data.msg,
                                 type: 'success'})

                      that.geteData()
              
                        // lets.onsubmit("clickitem1")
               
                 }else{
                      that.$message(response.data.msg)
                 }
               }).catch(function (error) {
                 console.log(error);
               });
      },
      handleDelete(index, row) {

        

                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
                  var that=this
             this.$axios.delete('/tquestionItemSelect', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: {id:row.id}
                             })
                             .then(function (res) {
                              that.$message({ 
                                    message: res.data.msg,
                                 type: 'success'})

                                 that.geteData()
                          
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
 
      },
// 书面题
   handleEditWrite(index, row) {
               var that=this
        console.log(index, row);

                 this.$axios.put('/tquestionItemWrite',row, {
             headers: {
                       "X-Token":sessionStorage.getItem("token")
                         },
                
            }).then(function (response) {
                 console.log(response);
                 if(response.data.code ==1){
                    that.$message({ 
                                    message: response.data.msg,
                                 type: 'success'})

                      that.geteData()
              
                        // lets.onsubmit("clickitem1")
               
                 }else{
                      that.$message(response.data.msg)
                 }
               }).catch(function (error) {
                 console.log(error);
               });
      },
      handleDeleteWrite(index, row) {

        

                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
                  var that=this
             this.$axios.delete('/tquestionItemWrite', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: {id:row.id}
                             })
                             .then(function (res) {
                              that.$message({ 
                                    message: res.data.msg,
                                 type: 'success'})

                                 that.geteData()
                          
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
 
      },


      geteData(){
           var that=this
              that.selectData=""
               that.writeData=""
            this.$axios.get('/tquestionItemSelect', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: {tquestionId:this.$route.query.tquestionId}
                             })
                             .then(function (res) {
                                 console.log(res.data);
                                that.selectData=res.data
                                      console.log( that.selectData)
                          
                             })
                             .catch(function (err) {
                                 console.log(err);
                             });

          this.$axios.get('/tquestionItemWrite', {  //params参数必写 , 如果没有参数传{}也可以
                               headers: {
                                    "X-Token":sessionStorage.getItem("token")
                                },
                                params: {tquestionId:this.$route.query.tquestionId}
                             })
                             .then(function (res) {
                                 console.log(res.data);
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
    this.geteData()
     console.log("as")
  onsole.log(this.selectData)

 }
}
</script>

<style scoped lang="scss">
    
.selectDiv{
   margin-left: 30%;
    width: 300px;
    font-size: 20px;
}

</style>
