<template>


<el-row class="container">
        <el-col :span="24" class="header">
            <el-col :span="5" class="logo" :class="'logo-width'">
                <a href="#" style="text-decoration:none;color:#FFFFFF;">
                    后台管理系统
                </a>
            </el-col>
            <el-col :span="2">
                <div class="tools" @click.prevent="collapse">
                    <i class="fa fa-align-justify"></i>
                </div>
            </el-col>
            <el-col :span="10" class="userinfo">
                <el-dropdown trigger="hover">
                    <span class="el-dropdown-link userinfo-inner ">
                        你好：{{userInfo.name}}
                    </span>
                    <el-dropdown-menu slot="dropdown"> 
                  
                        <el-dropdown-item @click.native="logout()">
                            注销登录
                        </el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-col>
        </el-col>
        <el-col :span="24" class="main">
            <aside :class="collapsed?'menu-collapsed':'menu-expanded'">
                <!--导航菜单-->
                <el-menu background-color="#304156" text-color="#fff" active-text-color="#ffd04b" v-bind:style=" collapsed ? {} : { 'width': '230px' } " :default-active="$route.path" class="el-menu-vertical-demo" unique-opened router v-show="!collapsed">
                    <template v-for="(item,index) in menuData" v-if="!item.hidden">
                        <el-submenu :index="index+''" v-if="!item.leaf">
                            <template slot="title">
                                <i :class="item.iconCls"></i>{{item.name}}
                            </template>
                            <el-menu-item v-for="child in item.children" :index="child.path" :key="child.path" v-if="!child.hidden">
                                {{child.name}}
                            </el-menu-item>
                        </el-submenu>
                        <el-menu-item v-if="item.leaf&&item.children.length>0" :index="item.children[0].path">
                            <i :class="item.iconCls"></i>{{item.children[0].name}}
                        </el-menu-item>
                    </template>
                </el-menu>
                <!--导航菜单-折叠后-->
                <ul class="el-menu el-menu-vertical-demo collapsed el-menu-collapsed" v-show="collapsed" ref="menuCollapsed">
                    <li v-for="(item,index) in menuData" v-if="!item.hidden" class="el-submenu item">
                        <template v-if="!item.leaf">
                            <div class="el-submenu__title" style="padding-left: 20px;" @mouseover="showMenu(index,true)" @mouseout="showMenu(index,false)">
                                <i :class="item.iconCls"></i>
                            </div>
                            <ul class="el-menu submenu" :class="'submenu-hook-'+index" @mouseover="showMenu(index,true)" @mouseout="showMenu(index,false)">
                                <li v-for="child in item.children" v-if="!child.hidden" :key="child.path" class="el-menu-item" style="padding-left: 40px; color: #fff;" :class="$route.path==child.path?'is-active':''" @click="$router.push(child.path)">
                                    {{child.name}}
                                </li>
                            </ul>
                        </template>
                        <template v-else>
                            <li class="el-submenu">
                                <div class="el-submenu__title el-menu-item" style="padding-left: 20px;height: 56px;line-height: 56px;padding: 0 20px;" :class="$route.path==item.children[0].path?'is-active':''" @click="$router.push(item.children[0].path)">
                                    <i :class="item.iconCls"></i>
                                </div>
                            </li>
                        </template>
                    </li>
                </ul>
            </aside>
            <section class="content-container" style="position:relative;top:-30px">
             
                    <el-col :span="24"  >
                        <transition name="fade" mode="out-in">
                            <router-view></router-view>
                        </transition>
                    </el-col>
    
            </section>
        </el-col>

    </el-row>

</template>

<script>

let data = () => {
        return {
            sysName: '后台管理系统',
            menuData: [],
            collapsed: false,
             userInfo:'',
            userRole: '管理员',
        }
    }

    let initMenu = function() {
        console.log(this.userRole)
        for(let i in this.$router.options.routes) {
   
            let roota = this.$router.options.routes[i].children
         for(var j=0;j< roota.length;j++) {
            let root=roota[j]
          
    
            if(root.hidden)
                continue
            var flag=0;
            var bool=false;
        
            for(var m=0; m<root.code.length; m++){
                flag++;
               if(root.code[m]==this.userRole){
                   break
               }
               if(flag==root.code.length){
                   bool=true
               }
            }
            if(bool){continue} 
            let children = []
            for(let j in root.children) {
                let item = root.children[j]
                if(item.hidden)
                    continue
                
                children.push(item)
            }

            if(children.length < 1)
                continue
            this.menuData.push(root)
            root.children = children
        }
         }
    }

    export default {
        data: data,
        methods: {
            initMenu: initMenu,
            //折叠导航栏
            collapse: function() {
                this.collapsed = !this.collapsed;
            },
            showMenu: function(i, status) {
                this.$refs.menuCollapsed.getElementsByClassName('submenu-hook-' + i)[0].style.display = status ? 'block' : 'none';
            },
         logout() {
        
              this.$confirm('确认要退出吗？','提示',{}).then(() => {
                   console.log("this");
                console.log("this");
                sessionStorage.clear();
                this.$router.push("/exam/login")
         
         
        }).catch(() => {
        });
      },
        },
    created: function () {
             if(sessionStorage.getItem("userInfo")==null){
                 this.$router.push("/exam/login")
             }
            console.group('created 创建完毕状态===============》');
         this.userInfo=JSON.parse(sessionStorage.getItem("userInfo"))
         this.userRole=this.userInfo.roleCode
         this.sysUserName=this.userInfo.name
      },
        mounted: function() {
            this.initMenu()
        }
    }




</script>

<style scoped="scoped" lang="scss">
    .container {
        position: absolute;
        top: 0;
        bottom: 0;
        width: 100%;
        .header {
            height: 60px;
            line-height: 60px;
            background:#304156;
            color: #fff;
            .userinfo {
                text-align: right;
                padding-right: 35px;
                float: right;
                .userinfo-inner {
                    cursor: pointer;
                    color: #fff;
                    img {
                        width: 40px;
                        height: 40px;
                        border-radius: 20px;
                        margin: 10px 0 10px 10px;
                        float: right;
                    }
                }
            }
            .logo {
                height: 60px;
                font-size: 22px;
                padding-left: 20px;
                padding-right: 20px;
                border-color: rgba(238, 241, 146, 0.3);
                border-right-width: 1px;
                border-right-style: solid;
                img {
                    width: 40px;
                    float: left;
                    margin: 10px 10px 10px 18px;
                }
                .txt {
                    color: #fff;
                }
            }
            .logo-width {
                width: 230px;
            }
            .logo-collapse-width {
                width: 60px;
            }
            .tools {
                padding: 0 23px;
                width: 14px;
                height: 60px;
                line-height: 60px;
                cursor: pointer;
            }
        }
        .main {
            display: flex;
            position: absolute;
            top: 60px;
            bottom: 0;
            overflow: hidden;
            aside {
                flex: 0 0 230px;
                width: 230px;
                /*侧边菜单*/
                .el-menu {
                    height: 100%;
                    background-color: #304156;
                    /*选中列*/
                    .el-menu-item {
                        background-color: #304156;
                    }
                    .el-submenu__title {
                        i {
                            color: #fff;
                        }
                    }
                }
                .el-menu:first-child {
                    overflow-y: auto !important;
                }
                .el-menu-collapsed li:hover {
                    background-color: #304156;
                }
                /*当前选中菜单*/
                .is-opened {
                    color: #fff;
                    background-color: #fff;
                }
                .collapsed {
                    width: 60px;
                    .item {
                        position: relative;
                    }
                    .submenu {
                        position: absolute;
                        top: 0;
                        left: 60px;
                        z-index: 99999;
                        height: auto;
                        display: none;
                    }
                }
            }
            .menu-collapsed {
                flex: 0 0 60px;
                width: 60px;
            }
            .menu-expanded {
                flex: 0 0 230px;
                width: 230px;
            }
            .content-container {
                flex: 1;
                overflow-y: hidden;
                padding: 20px;
                background-color: #fff;
                .breadcrumb-container {
                    display: none;
                    .title {
                        width: 200px;
                        float: left;
                        color: #475669;
                    }
                    .breadcrumb-inner {
                        float: right;
                    }
                }
                .content-wrapper {
                    box-sizing: border-box;
                }
            }
        }
    }
</style>
