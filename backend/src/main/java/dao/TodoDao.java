package dao;

import model.todo.po.Todo;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodoDao extends MongoRepository<Todo, ObjectId> {
    List<Todo> findAllByUserIdAndIsDeleted(ObjectId userId,boolean isDeleted);
//    Page<Todo>
}
