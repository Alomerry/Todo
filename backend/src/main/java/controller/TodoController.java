package controller;

import com.alibaba.fastjson.JSONObject;
import core.Result;
import core.ResultCode;
import model.todo.po.Todo;
import model.todo.vo.GetTodosRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;
import service.TodoService;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TodoController extends BaseController {

    @Resource
    private TodoService todoService;

    @GetMapping(value = "/todos")
    public Result getTodos(GetTodosRequest request, @RequestAttribute String userId) {
        System.out.println(request.toString());
        System.out.println(userId);
        JSONObject result = new JSONObject();
        List<Todo> todos = todoService.getTodosByUserId(userId);
        result.put("todos", todos);
        result.put("total", todos.size());
        return new Result().setCode(ResultCode.OK).setData(result);
    }
}
