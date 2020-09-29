package service;

import model.todo.po.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getTodosByUserId(String userId);
}
