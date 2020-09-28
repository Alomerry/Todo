package model.todo.po;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Todo {
    @Id
    private ObjectId id;
    private String content;
}
