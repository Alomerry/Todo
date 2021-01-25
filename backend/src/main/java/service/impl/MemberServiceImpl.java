package service.impl;

import dao.MemberDao;
import exception.base.InvalidParameterException;
import model.member.po.Member;
import org.springframework.stereotype.Service;
import service.MemberService;

import javax.annotation.Resource;

@Service
public class MemberServiceImpl implements MemberService {
    @Resource
    private MemberDao memberDao;

    public boolean Login(String userName, String passwd) {
        Member member = memberDao.findByNameAndPasswdAndIsDeleted(userName, passwd, false);
        if (member != null)
            return true;
        else
            return false;
    }

    @Override
    public boolean Register(String userName, String passwd, String repeatPasswd) {
        if (!passwd.equals(repeatPasswd)) {
            throw new InvalidParameterException("两次密码不一致", new Object[]{"password", "repeat password"});
        }
        if (memberDao.findByNameAndIsDeleted(userName, false) != null) {
            throw new InvalidParameterException("用户名已存在", new Object[]{"user name"});
        }
        if (memberDao.insert(new Member(userName, passwd)) != null) {
            return true;
        }
        throw new InvalidParameterException("未知错误", null);
    }

    public Member FindUserByName(String userName) {
        return memberDao.findByNameAndIsDeleted(userName, false);
    }
}
