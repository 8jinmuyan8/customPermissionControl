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
            <span class="h-panel-title">系统管理/角色管理</span>
            <div class="right">
                <button class="h-btn h-btn-s h-btn-green" @click="showAdd"><i class="h-icon-plus"></i> 新增</button>
            </div>
        </div>
        <div class="h-panel-body">

            <Form mode="inline" :label-width="120" :model="search" ref="form" :top="0.2">
                <FormItem label="角色名">
                    <input type="text" v-model="search.rname"/>
                </FormItem>
                <FormItem>
                    <Button color="blue" @click="doSearch"><i class="h-icon-search"></i> 查询</Button>
                </FormItem>
            </Form>

            <Table :datas="list" stripe>
                <TableItem title="No." :width="50">
                    <template slot-scope="props">{{props.index + 1}}</template>
                </TableItem>
                <TableItem title="角色名"  prop="rname"></TableItem>
                <TableItem title="角色描述" prop="description">
                </TableItem>
                <TableItem title="操作人"  prop="domainName"></TableItem>
                <TableItem title="创建时间" prop="inserttime"></TableItem>
                <TableItem title="更新时间"  prop="updatetime"></TableItem>
                <TableItem title="操作">
                    <template slot-scope="props">
                        <button class="h-btn h-btn-s" @click="showUpdate(props.data)"><i class="h-icon-edit"></i> 编辑
                        </button>
                        <button class="h-btn h-btn-s" @click="showMenu(props.data)"><i class="h-icon-setting"></i><span>菜单权限</span></button>
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

        <Modal v-model="roleModal.opened" :close-on-mask="false" :has-divider="true" :hasCloseIcon="true">
            <div slot="header">{{roleModal.data.rid == null?'新增':'编辑'}}角色</div>
            <div>
                <Form :label-width="100" :model="roleModal.data" ref="form" :rules="roleModal.rules" :top="0.2">
                    <FormItem label="角色名" prop="rname">
                        <input type="text" v-model="roleModal.data.rname"/>
                    </FormItem>
                    <FormItem label="角色描述" prop="description">
                        <textarea type="text" v-model="roleModal.data.description"/>
                    </FormItem>
                </Form>
            </div>
            <div slot="footer">
                <button class="h-btn" @click="roleModal.opened = false">取消</button>
                <button class="h-btn h-btn-primary" @click="doSave">确定</button>
            </div>
        </Modal>

        <Modal v-model="menuModal.opened" :close-on-mask="false" :has-divider="true" :hasCloseIcon="true">
            <div slot="header">[{{menuModal.rname}}]菜单权限</div>
            <div>
                <Tree :option="menuModal.menuTree" ref="menuTree" :multiple="true" v-model="menuModal.data" choose-mode="some"></Tree>
            </div>
            <div slot="footer">
                <button class="h-btn" @click="menuModal.opened = false">取消</button>
                <button class="h-btn h-btn-primary" @click="menuConfirm">确定</button>
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
                    total: 0,
                },
                roleModal: {
                    opened: false,
                    data: {
                        id: '', rname: '', description:'',domainName: '', inserttime: '', updatetime: ''
                    }

                },
              menuModal: {
                opened: false,
                roleID: '',
                rname: '',
                data: [],
                menuTree: {
                  keyName: 'id',
                  parentName: 'parentId',
                  titleName: 'name',
                  dataMode: 'list',
                  datas: []
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
              fetch.get('/sys/menu/list').then(res=>{
                if ('000000' == res.code) {
                  let list = res.result;
                  for (let i in list) {
                    let item = list[i];
                    item.treeIcon = item.icon;
                  }
                  this.menuModal.menuTree.datas = list;
                }
              });

            },
            loadData() {
                fetch.post('/sys/role/list', this.search).then(res => {
                    if ("000000" == res.code) {
                        let result = res.result;
                        let list = result.list;
                        this.list = null == list ? [] : list;
                        this.search.total = result.total;
                        log(this.list)
                    }
                });

            },
            doSearch() {
                this.search.page = 1;
                this.loadData();
            },
            doSave() {

                let data = this.roleModal.data;

                fetch.post('/sys/role/save', data).then(res => {
                    this.$Message(res.msg);
                    log(res);
                    if ("000000" == res.code) {
                        this.roleModal.opened = false;
                        this.loadData();
                    }
                });
            }, menuConfirm() {
            let ids = '';
            let arr = this.menuModal.data;
            for (let i in arr) {
              ids += arr[i]+',';
            }
            let p = {roleID:this.menuModal.roleID,menuIds:ids};
            fetch.post('/sys/role/authMenus',p).then(res=>{
              this.$Message(res.msg);
              if ('000000' == res.code) {
                this.menuModal.opened = false;
              }
            });
          },
          showMenu(data) {
            this.menuModal.opened = true;
            this.menuModal.roleID = data.id;
            this.menuModal.rname = data.rname;
            this.menuModal.data = [];
            // 当前选中的
            fetch.get('/sys/role/menuIds?roleID=' + data.id).then(res=>{
              if ('000000' == res.code) {
                this.menuModal.data = res.result;
                this.$refs.menuTree.refresh();
              }
            });
          },
            showAdd() {
                this.roleModal.opened = true;
                this.roleModal.data.id = null;
                this.roleModal.data.rname = '';
                this.roleModal.data.description = '';


            },
            showUpdate(item) {
                this.roleModal.opened = true;
                this.roleModal.data.id = item.id;
                this.roleModal.data.rname = item.rname;
                this.roleModal.data.description = item.description;


            }, showDelete(item) {
                this.$Confirm('确认要删除'+item.rname+'？').then(()=>{
                    fetch.post('/sys/role/delete', {'rid': item.rid}).then(res => {
                        this.$Message(res.msg);
                        if ("000000" == res.code) {
                            this.roleModal.opened = false;
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
