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
      <span class="h-panel-title">系统管理 / 登录记录</span>
    </div>
    <div class="h-panel-body">

      <Form mode="inline" :label-width="110" :model="search" :top="0.2">
        <FormItem label="账号">
          <input type="text" v-model="search.account" />
        </FormItem>
        <FormItem>
          <Button color="blue" @click="doSearch" ><i class="h-icon-search"></i> 查询</Button>
        </FormItem>
      </Form>

      <Table :datas="list" stripe >
        <TableItem title="No." :width="50" ><template slot-scope="props">{{props.index + 1 + (search.page - 1) * search.pageSize}}</template></TableItem>
        <TableItem title="账号" :width="150" prop="username"></TableItem>
        <TableItem title="IP" :width="100" prop="ipAddr"></TableItem>
        <TableItem title="是否成功" :width="80">
          <template slot-scope="props">
            <span class="h-tag h-tag-bg-green" v-if="props.data.status == 1">成功</span>
            <span class="h-tag h-tag-bg-red" v-if="props.data.status == 0">失败</span>
          </template>
        </TableItem>
        <TableItem title="操作时间" :width="150" prop="inserttime"></TableItem>
        <TableItem title="User-Agent" prop="userAgent"></TableItem>
      </Table>

      <div class="top10">
        <Pagination :cur="search.page" :pagerSize="search.pageSize" :total="search.total" @change="pageChange"></Pagination>
      </div>
    </div>

  </div>
</template>
<script>
export default {
  data() {
    return {
      search:{
        page: 1,
        pageSize: 10,
        total: 0,
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
      fetch.post('/sys/user/sign/log/list', this.search).then(res=>{
        let result = res.result;
        let list = null == result.list ? [] : result.list;
        this.list = list;
        this.search.total = result.total;
      })
    },
    doSearch() {
      this.search.page = 1;
      this.loadData();
    },
    pageChange(page) {
      this.search.page = page.cur;
      this.search.pageSize = page.size;
      this.loadData();
    },
  },
  computed: {

  }
}
</script>
