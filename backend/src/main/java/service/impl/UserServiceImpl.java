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
        User user = userDao.findByNameAndPasswd(name, passwd);
        if (user != null)
            return true;
        else
            return false;
    }
}
