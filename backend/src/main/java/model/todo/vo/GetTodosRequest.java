package model.todo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetTodosRequest implements Serializable {
    // todo 自动提取空字段名称
    private Integer page = 1;
    private Integer perPage = 20;
    private String color = "";
    private String status = "undo";
    private boolean isAscend = true;
    private String sortBy = "createdAt";
    private Integer startAt = null;
    private Integer endAt = null;
}
