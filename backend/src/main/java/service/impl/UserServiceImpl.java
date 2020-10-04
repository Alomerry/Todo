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

    public boolean Login(String name, String password) {
        User user = userDao.findByNameAndPasswdAndIsDeleted(name, password, false);
        if (user != null)
            return true;
        else
            return false;
    }

    @Override
    public boolean Register(String name, String password, String repeatPassword) {
        if (!password.equals(repeatPassword)) {
            return false;
        }
        if (userDao.findByNameAndIsDeleted(name, false) != null) {
            // todo throw 错误或是怎么样。。。区分注册失败类型
            return false;
        }
        if (userDao.insert(new User(name, password)) != null) {
            return true;
        }
        return false;
    }

    public User FindUserByName(String name) {
        return userDao.findByNameAndIsDeleted(name, false);
    }
}
