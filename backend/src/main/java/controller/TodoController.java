package controller;

import core.Result;
import core.ResultCode;
import model.todo.po.Todo;
import model.todo.vo.GetTodosRequest;
import org.springframework.web.bind.annotation.*;
import service.TodoService;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TodoController {

    @Resource
    private TodoService todoService;

    @GetMapping(value = "/todos")
    public Result getTodos(GetTodosRequest request, @RequestAttribute String userId) {
        System.out.println(request.toString());
        System.out.println(userId);
        List<Todo> todos = todoService.getTodosByUserId(userId);

        return new Result().setCode(ResultCode.OK);
    }
}
