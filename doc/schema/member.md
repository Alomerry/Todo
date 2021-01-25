# 用户

## 用户

```text
member {
    _id: ObjectId,
    name: String, // 用户名
    nickName: String, // 昵称
    email: String, // 邮箱
    phone: String, // 手机号
    Todo @Alomerry.Wu 移除密码，使用 OAuth
    passwd: String, // 密码
    createdAt: DateTime, // 创建时间
    updatedAt: DateTime, // 更新时间
    isDeleted: Boolean, // 是否已删除
    Todo @Alomerry.Wu 用户状态罗列
    status: String, // 状态
}
```