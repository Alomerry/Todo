package service.impl;

import dao.TodoDao;
import model.todo.po.Todo;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import service.TodoService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Resource
    private TodoDao todoDao;

    @Override
    public List<Todo> getTodosByUserId(String userId) {
        ObjectId uid = new ObjectId(userId);
        List<Todo> todos = todoDao.findAllByUserIdAndIsDeleted(uid, false);
        return todos;
    }
}
