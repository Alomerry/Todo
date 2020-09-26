package service.impl;

import dao.UserDao;
import model.User;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import service.UserService;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public boolean Login(String name, String passwd) {
        User user = userDao.findByNameAndPasswd(name, passwd);
        if (user != null)
            return true;
        else
            return false;
    }
}
