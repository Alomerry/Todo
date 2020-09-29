package service.impl;

import dao.UserDao;
import model.user.po.User;
import org.springframework.stereotype.Service;
import service.UserService;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public boolean Login(String name, String passwd) {
        User user = userDao.findByNameAndPasswdAndIsDeleted(name, passwd, false);
        if (user != null)
            return true;
        else
            return false;
    }

    public User FindUserByName(String name) {
        return userDao.findByNameAndIsDeleted(name, false);
    }
}
