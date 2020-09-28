package dao;

import model.user.po.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDao extends MongoRepository<User, ObjectId> {
    User findByNameAndPasswd(String name, String passwd);

    User findByName(String name);
}
