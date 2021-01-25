# Todo

## Todo

```text
todo {
    _id: ObjectId,
    memberId: ObjectId, // member._id
    content: String, // 内容
    color: String, // 颜色
    Todo @Alomerry.Wu todo 状态罗列
    status: String, // 状态
    doneAt: String, // 完成时间
    createdAt: DateTime,
    updatedAt: DateTime,
    isDeleted: Boolean,
}
```