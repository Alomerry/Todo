package model.todo.po;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Todo {
    @Id
    private ObjectId id;
    private ObjectId userId;
    private Date doneAt;
    private String content;
    private String color;
    private String status;
    private Date createdAt;
    private Date updatedAt;
    private Boolean isDeleted;
}
