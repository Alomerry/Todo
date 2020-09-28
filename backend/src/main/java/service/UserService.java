package service;

import model.user.po.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean Login(String name, String passwd);

    User FindUserByName(String name);
}
