package service;

import model.user.po.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean Login(String name, String repeatPassword);

    boolean Register(String name,String password,String repeatPassword);
    User FindUserByName(String name);
}
