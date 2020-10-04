package model.todo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Data
public class GetTodosRequest implements Serializable {
    // todo 自动提取空字段名称
    private Integer page = 1;
    private Integer perPage = 20;
    private String color = "";
    private String status = "undo";
    private Boolean isAscend = true;
    private String sortBy = "createdAt";
    private Integer startAt = null;
    private Integer endAt = null;
}
