package model.todo.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document
public class Todo implements Serializable {
    @Id
    private String id;
    private String userId;
    private Date doneAt;
    private String content;
    private String color;
    private String status;
    private Date createdAt;
    private Date updatedAt;
    private Boolean isDeleted;
}
