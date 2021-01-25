package service.impl;

import dao.MemberDao;
import model.member.po.Member;
import org.springframework.stereotype.Service;
import service.MemberService;

import javax.annotation.Resource;

@Service
public class MemberServiceImpl implements MemberService {
    @Resource
    private MemberDao memberDao;

    public boolean Login(String name, String password) {
        Member user = memberDao.findByNameAndPasswdAndIsDeleted(name, password, false);
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
        if (memberDao.findByNameAndIsDeleted(name, false) != null) {
            // todo throw 错误或是怎么样。。。区分注册失败类型
            return false;
        }
        if (memberDao.insert(new Member(name, password)) != null) {
            return true;
        }
        return false;
    }

    public Member FindUserByName(String name) {
        return memberDao.findByNameAndIsDeleted(name, false);
    }
}
