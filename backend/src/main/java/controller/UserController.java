package controller;

import config.JwtConfig;
import core.Result;
import core.ResultCode;
import model.user.vo.LoginRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private JwtConfig jwtConfig;

    @PostMapping("/login")
    Result Login(@Valid  @RequestBody LoginRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return new Result().setCode(ResultCode.FORBIDDEN).setMessage(result.getFieldError().getDefaultMessage());
        }
//        if (userService.Login(request.getName(), request.getPasswd())) {
//            User user = userService.FindUserByName(request.getName());
//            LoginResponse response = new LoginResponse(user.getId().toHexString(), user.getName(), jwtConfig.createToken(user.getId().toHexString()));
//            try {
//                Map<String, Object> responseMapper = MapUtils.ConvertObjectToMap(response);
//                JSONObject res = new JSONObject(responseMapper);
//                return new Result().setCode(ResultCode.OK).setData(res);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//                return new Result().setCode(ResultCode.FORBIDDEN);
//            }
//        }
        return new Result().setCode(ResultCode.OK);
    }
}
