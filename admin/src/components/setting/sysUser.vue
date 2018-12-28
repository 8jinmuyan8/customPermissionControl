<style lang='less'>
    .right {
        float: right;
    }

    .top10 {
        padding-top: 10px;
    }
</style>
<template>
    <div class="h-panel" v-margin="10">
        <div class="h-panel-bar" v-margin="10">
            <span class="h-panel-title">系统管理/用户管理</span>
            <div class="right">
                <button class="h-btn h-btn-s h-btn-green" @click="showAdd"><i class="h-icon-plus"></i> 新增</button>
            </div>
        </div>
        <div class="h-panel-body">

            <Form mode="inline" :label-width="110" :model="search" ref="form" :top="0.2">
                <FormItem label="账户名">
                    <input type="text" v-model="search.name"/>
                </FormItem>
                <FormItem>
                    <Button color="blue" @click="doSearch"><i class="h-icon-search"></i> 查询</Button>
                </FormItem>
            </Form>

            <Table :datas="list" stripe>
                <TableItem title="No." :width="50">
                    <template slot-scope="props">{{props.index + 1}}</template>
                </TableItem>
                <TableItem title="账户名"  prop="account"></TableItem>
                <TableItem title="名字"  prop="name"></TableItem>

                <TableItem title="邮箱"  prop="email"></TableItem>
                <TableItem title="生日"  prop="birthday"></TableItem>
                <TableItem title="性别">
                    <template slot-scope="props">
                        <span class="" v-if="props.data.sex == 1">男</span>
                        <span class="" v-if="props.data.sex == 0">女</span>
                    </template>
                </TableItem>
                <TableItem title="密码"  prop="password"></TableItem>
                <TableItem title="邮箱" prop="email"></TableItem>
                <TableItem title="角色"  prop="roleName"></TableItem>
                <TableItem title="操作">
                    <template slot-scope="props">
                        <button class="h-btn h-btn-s" @click="showUpdate(props.data)"><i class="h-icon-edit"></i> 编辑
                        </button>
                        <button class="h-btn h-btn-s h-btn-text-red" @click="showDelete(props.data)"><i
                                class="h-icon-trash"></i> 删除
                        </button>
                    </template>
                </TableItem>
            </Table>
            <div class="top10">
                <Pagination :cur="search.page" :pagerSize="search.pageSize" :total="search.total"
                            @change="pageChange"></Pagination>
            </div>
        </div>

        <Modal v-model="userModal.opened" :close-on-mask="false" :has-divider="true" :hasCloseIcon="true">
            <div slot="header">{{userModal.data.id == null?'新增':'编辑'}}用户</div>
            <div>
                <Form :label-width="100" :model="userModal.data" ref="form" :rules="userModal.rules" :top="0.2">
                    <FormItem label="用户名" prop="userName">
                        <input type="text" v-model="userModal.data.userName"/>
                    </FormItem>
                    <FormItem label="昵称" prop="nickName">
                        <input type="text" v-model="userModal.data.nickName"/>
                    </FormItem>
                    <FormItem label="密码" prop="password">
                        <input type="text"  v-model="userModal.data.password"/>
                    </FormItem>
                    <FormItem label="邮箱" prop="email">
                        <input type="text" v-model="userModal.data.email"/>
                    </FormItem>
                    <FormItem label="性别" prop="sex">
                        <Radio v-model="userModal.data.sex" :datas="sexs"></Radio>
                    </FormItem>
                    <FormItem label="封号状态" prop="ban">
                        <div style="padding-top: 5px">
                            <h-switch v-model="userModal.data.ban" :trueValue="1" :falseValue="0"
                                      :small="true"></h-switch>
                        </div>
                    </FormItem>
                    <FormItem label="角色" prop="rname">
                        <Select v-model="userModal.data.rid" keyName="rid" titleName="rname" :datas="roles"></Select>
                    </FormItem>

                </Form>
            </div>
            <div slot="footer">
                <button class="h-btn" @click="userModal.opened = false">取消</button>
                <button class="h-btn h-btn-primary" @click="doSave">确定</button>
            </div>
        </Modal>

    </div>
</template>
<script>
    export default {
        data() {
            return {

                search: {
                    page: 1,
                    pageSize: 10,
                    total: 0
                },

                roles: [],
                sexs: {0: '女', 1: '男'},
                userModal: {
                    opened: false,
                    data: {
                        id: '', userName: '', nickName: '', password: '', email: '', sex: '0', rid: '', ban: '0',rname:''
                    },
                    rules: {
                        required: ['userName', 'password','nickName']
                    }
                },
                list: [],

            }
        },
        mounted() {
            this.init()
        },
        methods: {
            init() {

                this.loadData();
                this.initRole();
            },
            loadData() {
                fetch.post('/sys/user/list', this.search).then(res => {
                    log(res)
                    if ("000000" == res.code) {
                        let list = res.result.list;
                        this.list = null == list ? [] : list;
                        this.search.total = res.result.total;
                    }
                });
            },
            initRole() {
                fetch.get('/sys/role/list').then(res => {
                    if ( "000000" == res.code) {
                        this.roles = res.result.list;
                    }
                });
            },
            doSearch() {
                this.search.page = 1;
                this.loadData();
            },
            doSave() {
                // let validResult = this.$refs.form.valid();
                // log(validResult);
                // if (!validResult.result) {
                //   this.$Message.error(`还有${validResult.messages.length}个错误未通过验证。`);
                //   return;
                // }
                let data = this.userModal.data;
                let password = this.userModal.data.password;

                if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/.exec(password)) {
                    this.$Message.error("密码必须为8个以上大小写加数字");
                    return;
                }

                fetch.post('/sys/user/save', data).then(res => {
                    this.$Message(res.message);
                    log(res);
                    if (200 == res.code) {
                        this.userModal.opened = false;
                        this.loadData();
                    }
                });

            },
            showAdd() {
                this.userModal.opened = true;
                this.userModal.data.id = null ;
                this.userModal.data.userName = '';
                this.userModal.data.nickName = '';
                this.userModal.data.password = '';
                this.userModal.data.email = '';
                this.userModal.data.sex = '0';
                this.userModal.data.ban = '0';
                this.userModal.data.rname = '';
                this.userModal.data.accountDisabled = false;
            },
            showUpdate(item) {
                this.userModal.opened = true;
                this.userModal.data.id = item.id;
                this.userModal.data.userName = item.userName;
                this.userModal.data.nickName = item.nickName;
                this.userModal.data.password = item.password;
                this.userModal.data.email = item.email;
                this.userModal.data.sex = item.sex;
                this.userModal.data.ban = item.ban;
                this.userModal.data.rid = item.rid;
                this.userModal.data.rname = item.rname;
                this.userModal.data.accountDisabled = true;
            }, showDelete(item) {
                this.$Confirm('确认要删除'+item.userName+'？').then(()=>{
                    fetch.post('/sys/user/delete', {'id': item.id}).then(res => {
                        this.$Message(res.message);
                        if (200 == res.code) {
                            this.userModal.opened = false;
                            this.loadData();
                        }
                    });
                }).catch(e => {
                    console.log(e);
                })

            },
            pageChange(page) {
                this.search.page = page.cur;
                this.search.pageSize = page.size;
                this.loadData();
            }
        },
        computed: {}
    }
</script>
