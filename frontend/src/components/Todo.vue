<template>
  <div style="width: 100%">
    <a-row :gutter="[8,8]">
      <a-col :span="2">
        <a-popover title="Pick a color" trigger="click">
          <template slot="content">
            <a-button-group>
              <a-button :loading="isLoading" style="color: #f50" @click="changeColor('#f50')">@</a-button>
              <a-button :loading="isLoading" style="color: #2db7f5" @click="changeColor('#2db7f5')">@</a-button>
              <a-button :loading="isLoading" style="color: #87d068" @click="changeColor('#87d068')">@</a-button>
            </a-button-group>
          </template>
          <a-tag :color="todo.color">
            @
          </a-tag>
        </a-popover>

      </a-col>
      <a-col :span="7">
        <div v-show="!isEditable" @click="isEditable = !isEditable">{{ todo.content }}</div>
        <div v-show="isEditable" @keypress.enter="isEditable = !isEditable">
          <a-input-search :loading="isLoading" v-model="todo.content" enterButton="OK"
                          @search="updateTodo('content')"></a-input-search>
        </div>
      </a-col>
      <a-col :span="4">
        <div>{{ formatDateTime('createdTime') }}</div>
      </a-col>
      <a-col :span="4">
        <div>{{ formatDateTime('doneTime') }}</div>
      </a-col>
      <a-col :span="2">
        <div v-if="todo.status == 'done'">
          <a-tag color="#87d068">done</a-tag>
        </div>
        <div v-else>
          <a-tag color="#f50">undo</a-tag>
        </div>
      </a-col>
      <a-col :span="5">
        <a-button size="small" type="danger" ghost @click="$emit('click',todo.id,OPERATION_DELETE)">Delete
        </a-button>&nbsp;
        <template v-if="status!=2">
          <a-button size="small" type="primary" ghost @click="$emit('click',todo.id,OPERATION_DONE)">Done</a-button>
        </template>
      </a-col>
    </a-row>
  </div>
</template>

<script>
import API from "../utils/API";
import CONSTANT from "../utils/Constant";

export default {
  name: "Todo",
  props: {
    content: {
      type: String,
      require: false,
    },
    tid: {
      type: String,
      require: false,
    },
    color: {
      type: String,
      require: false,
    },
    createdAt: {
      type: Number,
      require: true,
    },
    doneAt: {
      type: String,
      require: false,
      default: "-"
    },
    status: {
      type: String,
      require: false,
    },
  },
  data() {
    return {
      isEditable: false,
      isLoading: false,
      OPERATION_DELETE: CONSTANT.OPERATION_DELETE,
      OPERATION_DONE: CONSTANT.OPERATION_DONE,
      todo: {
        id: this.tid,
        content: this.content,
        color: this.color,
        createdAt: this.createdAt,
        doneAt: this.doneAt,
        status: this.status,
      },
    }
  },
  mounted() {
    console.log(new Date(1568027067567));
    let a = 1568027067567
    console.log(a.toLocaleString());
  },
  methods: {
    changeColor(color) {
      console.log(color)
      this.todo.color = color;
      this.updateTodo("color");
    },
    async updateTodo(column) {
      this.isLoading = true;
      if (this[column] != this.todo[column]) {
        let map = new Map();
        map.set(column, this.todo[column])
        await API.updateTodo(this.todo.id, map).then(
            res => {
              console.log(res)
              this.$message.success("修改成功！");
            },
            err => {
              this.$message.error("修改失败！");
              this.todo[column] = this[column];
              console.log(err)
            }
        ).catch(err => {
          console.log(err)
        }).finally(() => {
          this.isLoading = false;
          this.isEditable = false;
        })
      }
      this.isLoading = false;
      this.isEditable = false;
    },

    formatDateTime(name) {
      if (this[name] ?? false) {
        return this[name].toLocaleString();
      } else {
        return "-";
      }
    }
  },
  computed: {
    createdTime() {
      return new Date(this.createdAt / 1000);
    },
    doneTime() {
      let time = new Date(this.doneAt / 1000);
      if (time == "Invalid Date")
        return null;
      else
        return time;
    }
  }
}
</script>

<style scoped>

</style>