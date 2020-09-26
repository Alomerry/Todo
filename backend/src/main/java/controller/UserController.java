package controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    String home(@RequestParam("name") String name,
                @RequestParam("passwd") String passwd) {
        if (userService.Login(name, passwd)) {
            return "login success";
        }
        return "Hello World!";
    }
}
