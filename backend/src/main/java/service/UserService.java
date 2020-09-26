package service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean Login(String name,String passwd);
}
