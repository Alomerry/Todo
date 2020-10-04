<template>
	<div>
		<a-modal
			v-model="canAddTodo"
			title="Create Todo"
			:footer="null">
			<a-form-model ref="todoForm" :model="todoForm" :rules="todoRules" v-bind="layout">
				<a-form-model-item has-feedback label="Content" prop="pass">
					<a-textarea autoSize :auto-size="{ minRows: 3, maxRows: 5 }" v-model="todoForm.content"></a-textarea>
				</a-form-model-item>
				<a-form-model-item has-feedback label="Color">
					<a-select v-model="todoForm.color" default-value="酒红" style="width: 120px">
						<a-icon slot="suffixIcon" type="smile" :style="{color:todoForm.color}"/>
						<a-select-option value="#f50">
							<span style="color: #f50">酒红</span>
						</a-select-option>
						<a-select-option value="#2db7f5">
							<span style="color: #2db7f5">海蓝</span>
						</a-select-option>
						<a-select-option value="#87d068">
							<span style="color: #87d068">翠绿</span>
						</a-select-option>
					</a-select>
				</a-form-model-item>
				<a-form-model-item :wrapper-col="{ span: 14, offset: 4 }">
					<a-button type="primary" @click="addOneTodo()" :loading="isCreating">
						Submit
					</a-button>
				</a-form-model-item>
			</a-form-model>
		</a-modal>
		<a-row :gutter="[8,32]">
			<a-col :span="4">
				<a-input-search v-model="keyword" @search="search"/>
			</a-col>
			<a-col :span="4">
				<a-select default-value="Done" style="width: 120px" @change="searchByStatus">
					<a-select-option value="done">
						Done
					</a-select-option>
					<a-select-option value="undo">
						Undo
					</a-select-option>
				</a-select>
			</a-col>
			<a-col :span="6">
				<a-range-picker @change="changeDateRange" :locale="locale"/>
			</a-col>
			<a-col :span="2">
				<a-button @click="canAddTodo = true">New</a-button>
			</a-col>
			<a-col :span="2">
				<a-button @click="getTodos">Reload</a-button>
			</a-col>
		</a-row>
		<a-row :gutter="[8,32]">
			<a-col :span="2">Color</a-col>
			<a-col :span="7">Todo</a-col>
			<a-col :span="4">CreatedAt</a-col>
			<a-col :span="4">DoneAt</a-col>
			<a-col :span="2">Status</a-col>
			<a-col :span="5">Operation</a-col>
		</a-row>
		<a-row>
			<a-list item-layout="horizontal" :data-source="todos" :rowKey="todo=>todo.id">
				<a-list-item slot="renderItem" slot-scope="todo">
					<Todo :tid="todo.id"
								:content="todo.content"
								:created-at="todo.createdAt"
								:color="todo.color"
								:status="todo.status"
								:done-at="todo.doneAt"
								:ref="`todo${todo.id}`"
								@click="operateTodo"></Todo>
				</a-list-item>
			</a-list>
		</a-row>
		<a-pagination :current="page" :total="total" :size="pageSize" :pageSizeOptions="['5', '10']" showSizeChanger
									@change="changePage" @showSizeChange="changePage"/>
	</div>
</template>

<script>
  import Todo from "../components/Todo";
  import CONSTANT from "../utils/Constant";
  import API from "../utils/API";
  import locale from 'ant-design-vue/es/date-picker/locale/zh_CN';

  export default {
    name: "Todos",
    components: {Todo},
    data() {
      let validateContent = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('Please input the content'));
        } else {
          callback();
        }
      };
      return {
        locale,
        isCreating: false,
        page: 1,
        pageSize: "10",
        total: -1,
        canAddTodo: false,
        keyword: "",
        todos: [],
        current: 1,
        todoForm: {
          content: "",
          color: '#f50',
        },
        layout: {
          labelCol: {span: 4},
          wrapperCol: {span: 14},
        },
        todoRules: {
          content: [{validator: validateContent, trigger: 'change'}],
        },
      };
    },
    created() {
      this.getTodos();
    },
    mounted() {
    },
    methods: {
      search(value) {
        console.log(value)
        let map = new Map();
        map.set("keyword", value)
        this.getTodos(null, null, map)
      },
      searchByStatus(value) {
        console.log(value)
        let map = new Map();
        map.set("status", value)
        this.getTodos(null, null, map)
      },
      changePage(current, size) {
        console.log(current, size)
        this.page = current;
        this.pageSize = size;
        this.getTodos();
      },
      addOneTodo() {
        this.isCreating = true;
        API.createTodo(this.todoForm.color, this.todoForm.content).then(
          res => {
            console.log(res);
            this.$message.success("添加成功！");
            this.getTodos();
          },
          err => {
            console.log(err);
            this.$message.error("添加失败！");
          }
        ).catch(err => {
          console.log(err)
        }).finally(() => {
          this.canAddTodo = false;
          this.isCreating = false;
        })
      },

      changeDateRange(date, dateString) {
        console.log(date, dateString);
        console.log(new Date(1595295812504).getTime())
        console.log(new Date(dateString[0].valueOf() / 1000).toLocaleDateString())
        let map = new Map();
        map.set("startAt", date[0].valueOf())
        map.set("endAt", date[1].valueOf())
        this.getTodos(null, null, map);
      },

      operateTodo(id, operation) {
        console.log(id)
        console.log(operation)
        switch (operation) {
          case CONSTANT.OPERATION_DELETE: {
            this.deleteTodoById(id);
            break;
          }
          case CONSTANT.OPERATION_DONE: {
            this.doneTodoById(id);
            break;
          }
        }
      },

      getTodos(sortBy, isAscend, map) {
        API.getTodos(this.page, this.pageSize, sortBy, isAscend, map).then(
          res => {
            console.log(res.data.total)
            this.total = res.data.total;
            this.todos = res.data.todos;
            this.todos = this.todos ?? [];
          },
          err => {
            console.log(err)
            this.$message.error(err.response.data)
            this.$store.dispatch("logout")
            setTimeout(function () {
              window.location = "/login"
            }, 500)
          }
        ).catch(err => {
          console.log(err)
        })
      },

      deleteTodoById(id) {
        API.deleteTodo(id).then(
          res => {
            console.log(res);
            this.$message.success("删除成功！");
            this.todos.forEach(function (value, index, arr) {
              if (id == value.id) {
                console.log(index)
                arr.splice(index, 1);
                return true;
              }
            })
          },
          err => {
            console.log(err)
          }
        ).catch(err => {
          console.log(err)
        })
      },

      doneTodoById(id) {
        console.log(id);
        let map = new Map();
        map.set("status", 2);
        API.updateTodo(id, map).then(
          res => {
            console.log(res);
            this.$message.success("修改成功！");
            this.getTodos();
          },
          err => {
            console.log(err);
            this.$message.error("修改失败！");
            this.getTodos();
          }
        ).then(err => {
          console.log(err);
        });
      }
    },
  }
</script>

<style scoped>

</style>