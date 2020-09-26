package dao;

import model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDao extends MongoRepository<User, ObjectId> {
    User findByNameAndPasswd(String name, String passwd);
}
