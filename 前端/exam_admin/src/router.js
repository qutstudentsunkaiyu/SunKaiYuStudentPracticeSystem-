import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Login from './views/Login.vue'
import LoginForm from './views/LoginForm.vue'
import Register from './views/LoginRegister.vue'
import Admin from './views/Admin.vue'
import Main from './views/Main.vue'

import Message from './views/persionnal/Message.vue'
import Pass from './views/persionnal/Pass.vue'

import UserMsg from './views/userManager/UserMsg.vue'
import UserMod from './views/userManager/UserMod.vue'

import Exam from './views/exam/Exam.vue'
import ExamQuestion from './views/exam/ExamQuestion.vue'
import ExamHistory from './views/exam/ExamHistory.vue'
import ExamTeacherList from   './views/exam/ExamTeacherList.vue'

import Question from './views/question/Question.vue'
import QuestionList from './views/question/QuestionList.vue'
import Invite from './views/question/Invite.vue'
import InviteList from './views/question/InviteResult.vue'

import Preview from './views/questionPreview/Preview.vue'
import ExamResultPreview from  './views/questionPreview/ExamResultPreview.vue'

import Test from './views/test/Test.vue'
import TestAnswer from './views/test/TestAnswer.vue' 

Vue.use(Router)

export default new Router({
  mode:'history',
  base:process.env.BASE_URL,
  routes: [
 
    {
      path: '/exam',
      name: 'login',
 
      component: Login,
      iconCls: 'fa fa-address-card',
      children:[
        {
          path:'login',
          name:'form',
          hidden:true,
          component: LoginForm
        },
        {
          path:'reg',
          name:'reg',
          hidden:true,
          component: Register
        }

      ]
    },

    {
      path:'/admin',
      name:'admin',
      component:Admin,
      children: [
        {
          path:'/admin',
          name:'      个人中心',
          code:['admin','student','teacher'],
          iconCls: 'fa fa-cog',
          component:Main,
          children: [
            {
            path: '/admin',
            name: '个人信息',
            
            component: Message,
              },
              {
                path: '/admin/pass',
                name: '修改密码',
         
                component: Pass,
                  }
           ]
        },
        {
          path:'/admin/user',
          name:' 用户管理',
          code:['admin'],
          iconCls: 'fa fa-user-o',
          component:Main,
          children: [
            {
            path: '/admin/user/msg',
            name: '用户信息',
            component: UserMsg,
              },
              // {
              //   path: '/admin/user/mod',
              //   name: '用户权限',
              //   component: UserMod,
              //     },
           
           ]
        },
        {
          path:'/admin/exam',
          name:' 考试中心',
          code:['student'],
          iconCls: 'fa fa-line-chart',
          component:Main,
          children: [
            {
            path: '/admin/exam/',
            name:  '随机练习',
            component: Exam,
              },
              {
                path: '/admin/exam/teacherexam',
                name:  '参加考试',
                component: ExamTeacherList,
                  },
              
          
                  {
                    path: '/admin/exam/history',
                    name:  '考试历史',
                    component: ExamHistory,
                      },
                     

                  
           
           ]
        },
        {
          path:'/admin/question',
          name:' 试卷中心',
          code:['teacher'],
          iconCls: 'fa fa-file-text',
          component:Main,
          children: [
            {
            path: '/admin/question',
            name: '上传试卷',
            component: Question,
              },
              {
                path: '/admin/question/list',
                name: '试卷管理',
                component: QuestionList,
                  },
              {
                    path: '/admin/question/invite',
                    name: '学生考试',
                    component: Invite,
               },
              {
                        path: '/admin/question/invitelist',
                        name: '考试记录',
                        component: InviteList,
               },
           
           ]
        },
      
       ]
    },

    {
      path:'/tquestion/preview',
      name:' previews',
    
      component:Preview,

    },
    {
      path:'/tquestion/preview_exam',
      name:' previews',
    
      component:ExamResultPreview,

    },
    
    {
      path:'/test',
      name:' test',
    
      component:Test,

    },
    {
      path:'/testAnswer',
      name:' testAnswer',
    
      component:TestAnswer,

    }
   
 
  ]
})
