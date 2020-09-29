package controller;

import core.Result;
import core.ResultCode;
import model.todo.po.Todo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.TodoService;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TodoController {

    @Resource
    private TodoService todoService;

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public Result getTodos(@RequestParam("userId") String userId) {
        List<Todo> todos = todoService.getTodosByUserId(userId);
        return new Result().setCode(ResultCode.FORBIDDEN);
    }
}
