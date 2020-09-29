package model.todo.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
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
