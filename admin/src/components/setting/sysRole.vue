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
                <TableItem title="角色ID"  prop="rid" :width="180"></TableItem>
                <TableItem title="角色名"  prop="rname"></TableItem>
                <TableItem title="角色描述" prop="rdesc">
                </TableItem>
                <TableItem title="角色值"  prop="rval"></TableItem>
                <TableItem title="创建时间" prop="inserttime"></TableItem>
                <TableItem title="更新时间"  prop="updatetime"></TableItem>
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

        <Modal v-model="roleModal.opened" :close-on-mask="false" :has-divider="true" :hasCloseIcon="true">
            <div slot="header">{{roleModal.data.rid == null?'新增':'编辑'}}角色</div>
            <div>
                <Form :label-width="100" :model="roleModal.data" ref="form" :rules="roleModal.rules" :top="0.2">
                    <FormItem label="角色名" prop="rname">
                        <input type="text" v-model="roleModal.data.rname"/>
                    </FormItem>
                    <FormItem label="角色描述" prop="rdesc">
                        <textarea type="text" v-model="roleModal.data.rdesc"/>
                    </FormItem>
                    <FormItem label="角色值" prop="rval">
                        <input type="text"  v-model="roleModal.data.rval"/>
                    </FormItem>

                </Form>
            </div>
            <div slot="footer">
                <button class="h-btn" @click="roleModal.opened = false">取消</button>
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
                    total: 0,
                },
                roleModal: {
                    opened: false,
                    data: {
                        rid: '', rname: '', rdesc: '',rval: '', inserttime: '', updatetime: ''
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
            },
            loadData() {
                fetch.post('/sys/role/list', this.search).then(res => {
                    log(res)
                    if (200 == res.code) {
                        let result = res.message;
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
                    this.$Message(res.message);
                    log(res);
                    if (200 == res.code) {
                        this.roleModal.opened = false;
                        this.loadData();
                    }
                });
            },
            showAdd() {
                this.roleModal.opened = true;
                this.roleModal.data.rid = null;
                this.roleModal.data.rname = '';
                this.roleModal.data.rdesc = '';
                this.roleModal.data.rval = '';

            },
            showUpdate(item) {
                this.roleModal.opened = true;
                this.roleModal.data.rid = item.rid;
                this.roleModal.data.rname = item.rname;
                this.roleModal.data.rdesc = item.rdesc;
                this.roleModal.data.rval = item.rval;

            }, showDelete(item) {
                this.$Confirm('确认要删除'+item.rname+'？').then(()=>{
                    fetch.post('/sys/role/delete', {'rid': item.rid}).then(res => {
                        this.$Message(res.message);
                        if (200 == res.code) {
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