<template>
  <div>
    <a-row class="login-form">
      <a-col span="10" offset="7">
        <a-form-model ref="loginForm" :model="loginForm" :rules="loginRules" v-bind="layout">
          <a-form-model-item has-feedback label="Name" prop="name">
            <a-input v-model="loginForm.name" type="text" autocomplete="off"/>
          </a-form-model-item>
          <a-form-model-item has-feedback label="Password" prop="password">
            <a-input v-model="loginForm.password" type="password" autocomplete="off"/>
          </a-form-model-item>
          <a-form-model-item :wrapper-col="{ span: 14, offset: 4 }">
            <a-button type="primary" @click="submitForm('loginForm')" :loading="isLogining">
              Submit
            </a-button>
            <a-button style="margin-left: 10px" @click="resetForm('loginForm')">
              Reset
            </a-button>
          </a-form-model-item>
          <a @click="canRegister = true">
            Don't have an account? Sign up now!
          </a>
        </a-form-model>
      </a-col>
    </a-row>
    <div>
      <a-modal v-model="canRegister" title="Register" :footer="null">
        <a-form-model ref="registerForm" :model="registerForm" :rules="registerRules" v-bind="layout">
          <a-form-model-item has-feedback label="Name" prop="name">
            <a-input v-model="registerForm.name" type="text" autocomplete="off"/>
          </a-form-model-item>
          <a-form-model-item has-feedback label="Password" prop="password">
            <a-input v-model="registerForm.password" type="password" autocomplete="off"/>
          </a-form-model-item>
          <a-form-model-item has-feedback label="Repeat" prop="repeatPassword">
            <a-input v-model="registerForm.repeatPassword" type="password" autocomplete="off"/>
          </a-form-model-item>
          <a-form-model-item :wrapper-col="{ span: 14, offset: 4 }">
            <a-button type="primary" @click="submitForm('registerForm')" :loading="isRegistering">
              Submit
            </a-button>
            <a-button style="margin-left: 10px" @click="resetForm('registerForm')">
              Reset
            </a-button>
          </a-form-model-item>
        </a-form-model>
      </a-modal>
    </div>
  </div>
</template>

<script>
import 'ant-design-vue/dist/antd.css';
import API from '../utils/API'

export default {
  name: "Login",
  data() {
    let validateRegisterUserName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the username'));
      } else if (this.registerForm.name.length < 6) {
        callback(new Error('Please input more than 6 characters'));
      } else if (this.registerForm.name.length > 12) {
        callback(new Error('Please input less than 12 characters'));
      } else {
        callback();
      }
    };
    let validateRegisterPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the password again'));
      } else if (this.registerForm.repeatPassword !== this.registerForm.password) {
        callback(new Error("Two inputs don't match!"));
      } else {
        callback();
      }
    };

    let validateUserName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the username'));
      } else if (this.loginForm.name.length < 6) {
        callback(new Error('Please input more than 6 characters'));
      } else if (this.loginForm.name.length > 12) {
        callback(new Error('Please input less than 12 characters'));
        this.$refs.user.validateField('password');
      } else {
        callback();
      }
    };
    let validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the password'));
      } else if (this.loginForm.password.length < 6) {
        callback(new Error('Please input more than 6 characters'));
      } else if (this.loginForm.password.length > 18) {
        callback(new Error('Please input less than 18 characters'));
      } else {
        callback();
      }
    };

    return {
      isLogining: false,
      isRegistering: false,
      canRegister: false,
      loginForm: {
        name: '',
        password: '',
      },
      loginRules: {
        name: [{validator: validateUserName, trigger: 'change'}],
        password: [{validator: validatePassword, trigger: 'change'}],
      },
      registerForm: {
        name: '',
        password: '',
        repeatPassword: '',
      },
      registerRules: {
        name: [{validator: validateRegisterUserName, trigger: 'change'}],
        password: [{validator: validateRegisterPassword, trigger: 'change'}],
        repeatPassword: [{validator: validateRegisterPassword, trigger: 'change'}],
      },
      layout: {
        labelCol: {span: 4},
        wrapperCol: {span: 14},
      },
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (formName.startsWith("login")) {
            this.login(this.loginForm.name, this.loginForm.password)
          } else if (formName.startsWith("register")) {
            this.register(this.registerForm.name, this.registerForm.password, this.registerForm.repeatPassword);
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    login(name, password) {
      this.isLogining = true;
      API.login(name, password).then(
          res => {
            this.$message.success("Login success!");
            console.log(res.data);
            this.$store.dispatch("login", {
              id: res.data.id,
              name: res.data.name,
              accessToken: res.data.accessToken,
            });
            setTimeout(function () {
              window.location = "/todos";
            }, 300);
          },
          error => {
            this.$message.error(error.response.data)
            console.log(error.response)
          }
      ).catch(error => {
        console.log(error)
      }).finally(() => {
        this.isLogining = false;
      })
    },

    register(name, password, repeatPassword) {
      this.isRegistering = true;
      API.register(name, password, repeatPassword).then(
          res => {
            if (res.data.code == 200) {
              this.$message.success("Register success!");
            }else{
              this.$message.error("Register failed!");
            }
          },
          error => {
            this.$message.error(error.response.data)
          }
      ).catch(error => {
        console.log(error)
      }).finally(() => {
        this.isRegistering = false;
        this.canRegister = false;
      })
    }
  },
  mounted() {
  },
}
</script>

<style scoped>

</style>