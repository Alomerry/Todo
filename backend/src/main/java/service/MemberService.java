package service;

import model.member.po.Member;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    boolean Login(String name, String repeatPassword);

    boolean Register(String name,String password,String repeatPassword);
    Member FindUserByName(String name);
}
