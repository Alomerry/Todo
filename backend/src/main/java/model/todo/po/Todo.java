package model.todo.po;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
public class Todo implements Serializable {
    @Id
    private String id;
    private String memberId;
    private Date doneAt;
    private String content;
    private String color;
    private String status;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
    private boolean isDeleted;
}
