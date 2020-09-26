package controller;

import com.alibaba.fastjson.JSONObject;
import core.Result;
import core.ResultCode;
import model.user.vo.LoginRequest;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    Result Login(@ModelAttribute LoginRequest request) {
//        if (userService.Login(request.getName(), request.getPasswd())) {
//            System.out.println("success");
//            return new Result().setCode(ResultCode.OK).setData(new JSONObject());
//        }
        return new Result().setCode(ResultCode.FORBIDDEN);
    }
}
