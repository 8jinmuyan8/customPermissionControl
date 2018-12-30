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
      <span class="h-panel-title">系统管理 / 菜单管理</span>
    </div>
    <div class="h-panel-body">
      <div>
        <Button @click="expandAll">全部展开</Button>
        <Button @click="foldAll">全部收起</Button>
        <Button class="h-btn h-btn-green" @click="showAdd(1)">新增一级菜单</Button>
        <Button class="h-btn h-btn-text-green" :disabled="disabled" @click="showAdd(2)"><i class="h-icon-plus"></i> 新增子菜单</Button>
        <Button class="h-btn h-btn-text-green" :disabled="disabled" @click="showUpdate"><i class="h-icon-edit"></i> 编辑</Button>
        <Button class="h-btn h-btn-text-red" :disabled="disabled" @click="showDel"><i class="h-icon-trash"></i> 删除</Button>
      </div>
      <div class="top10">
        <Tree v-width="400" :filterable="true" :option="menuTree" :toggleOnSelect="true" ref="menuTree" @select="select" >
        <div slot-scope="props" slot="item"><input type="text" v-model="props.item.name" /><i class="h-icon-edit"></i><i class="h-icon-trash"></i></div>
      </Tree>
      </div>
    </div>

    <Modal v-model="menuModal.opened" :close-on-mask="false" :has-divider="true" :hasCloseIcon="true">
      <div slot="header">{{menuModal.data.id == ''?'新增':'编辑'}}菜单</div>
      <div >
        <Form :label-width="80" :model="menuModal.data" ref="form" :rules="menuModal.rules" :top="0.2">
          <FormItem label="名称" prop="name">
            <input type="text" v-model="menuModal.data.name" />
          </FormItem>
          <FormItem label="图标" prop="icon">
            <input type="text" v-model="menuModal.data.icon" />
          </FormItem>
          <FormItem label="路径" prop="path">
            <input type="text" v-model="menuModal.data.path" />
          </FormItem>
          <FormItem label="序号" prop="seq">
            <input type="text" placeholder="请输入数字" v-model="menuModal.data.seq" />
          </FormItem>
        </Form>
      </div>
      <div slot="footer">
        <button class="h-btn" @click="menuModal.opened = false">取消</button>
        <button class="h-btn h-btn-primary" @click="doSave">确定</button>
      </div>
    </Modal>

  </div>
</template>
<script>
export default {
  data() {
    return {
      cur: null,
      menuModal: {
        opened: false,
        data: {
          id: '', seq: '', name: '', path: '', icon: '', parentId:''
        },
        rules: {
          required: ['name','path','seq']
        }
      },
      menuTree: {
        keyName: 'id',
        parentName: 'parentId',
        titleName: 'displayName',
        dataMode: 'list',
        datas: [] 
      }
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init() {
      fetch.get('/sys/menu/list').then(res=>{
        if ('000000' == res.code) {
          let list = res.result;
          for (let i in list) {
            let item = list[i];
            item.treeIcon = item.icon;
            item.displayName = item.name + " --- " + item.seq;
          }
          this.menuTree.datas = list;
          this.$refs.menuTree.refresh();
          this.cur = null;
        }
      });
    },
    doSave() {
      let validResult = this.$refs.form.valid();
      if (!validResult.result) {
        this.$Message.error(`还有${validResult.messages.length}个错误未通过验证。`);
        return;
      }
      fetch.post('/sys/menu/save',this.menuModal.data).then(res=>{
        this.$Message(res.msg)
        if ('000000' == res.code) {
          this.menuModal.opened = false;
          this.init();
        }
      });
    },
    expandAll() {
      this.$refs.menuTree.expandAll();
    },
    foldAll() {
      this.$refs.menuTree.foldAll();
    },
    select(data) {
      this.cur = data;
    },
    showAdd(flag) {
      this.menuModal.opened = true;
      this.menuModal.data.id = '';
      if (1 == flag) {
        this.menuModal.data.parentId = '';// 1级菜单
      } else {
        this.menuModal.data.parentId = this.cur.id;
      }
      this.menuModal.data.seq = 1;
      this.menuModal.data.icon = '';
      this.menuModal.data.path = '';
      this.menuModal.data.name = '';
    },
    showUpdate() {
      this.menuModal.opened = true;
      log(this.cur);
      this.menuModal.data = this.cur;
    },
    showDel() {
      this.$Confirm('确认要删除选中？').then(()=>{
        fetch.post('/sys/menu/del',{id:this.cur.id}).then(res=>{
        this.$Message(res.msg)
        if ('000000' == res.code) {
          this.menuModal.opened = false;
          this.init();
        }
      });
      })
    }

  },
  computed: {
    disabled() {
      return null == this.cur;
    }
  }
}
</script>