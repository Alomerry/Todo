package model.todo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Todo implements Serializable {
    private String id;
    private String userId;
    private int doneAt;
    private String content;
    private String color;
    private String status;
    private int createdAt;
    private int updatedAt;
    private Boolean isDeleted;
}