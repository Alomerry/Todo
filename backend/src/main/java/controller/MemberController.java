package controller;

import config.JwtConfig;
import core.Result;
import core.ResultCode;
import exception.base.InvalidParameterException;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import model.member.po.Member;
import model.member.vo.LoginRequest;
import model.member.vo.LoginResponse;
import model.member.vo.RegisterRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.MemberService;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class MemberController extends BaseController {

    @Resource
    private MemberService memberService;

    @Resource
    private JwtConfig jwtConfig;

    @PostMapping(value = "/login")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "test")
    })
    LoginResponse Login(@Valid @RequestBody LoginRequest request, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            throw new InvalidParameterException(result.getFieldError().getDefaultMessage(), new String[]{result.getFieldError().getField()});
        }
        if (memberService.Login(request.getName(), request.getPassword())) {
            Member user = memberService.FindUserByName(request.getName());
            return new LoginResponse(user.getId(), user.getName(), jwtConfig.createToken(user.getId()));
        } else {
            throw new InvalidParameterException("账号或密码错误", null);
        }
    }

    @PostMapping(value = "/register")
    Object Register(@Valid @RequestBody RegisterRequest request, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return new Result().setCode(ResultCode.FORBIDDEN).setMessage(result.getFieldError().getDefaultMessage());
        }
        if (memberService.Register(request.getName(), request.getPassword(), request.getRepeatPassword())) {
            return null;
        }
        // todo
        return null;
    }
}
