package controller;

import com.alibaba.fastjson.JSONObject;
import config.JwtConfig;
import core.Result;
import core.ResultCode;
import model.member.po.Member;
import model.member.vo.LoginRequest;
import model.member.vo.LoginResponse;
import model.member.vo.RegisterRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.MemberService;
import utils.MapUtils;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@RestController
public class MemberController {

    @Resource
    private MemberService memberService;

    @Resource
    private JwtConfig jwtConfig;

    @PostMapping(value = "/login")
    Result Login(@Valid @RequestBody LoginRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return new Result().setCode(ResultCode.FORBIDDEN).setMessage(result.getFieldError().getDefaultMessage());
        }
        // todo 需要封装
        if (memberService.Login(request.getName(), request.getPassword())) {
            Member user = memberService.FindUserByName(request.getName());
            LoginResponse response = new LoginResponse(user.getId(), user.getName(), jwtConfig.createToken(user.getId()));
            try {
                Map<String, Object> responseMapper = MapUtils.ConvertObjectToMap(response);
                JSONObject res = new JSONObject(responseMapper);
                return new Result().setCode(ResultCode.OK).setData(res);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return new Result().setCode(ResultCode.FORBIDDEN);
            }
        } else {
            return new Result().setCode(ResultCode.FORBIDDEN);
        }
    }

    @PostMapping("/register")
    Result Register(@Valid @RequestBody RegisterRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return new Result().setCode(ResultCode.FORBIDDEN).setMessage(result.getFieldError().getDefaultMessage());
        }
        if (memberService.Register(request.getName(), request.getPassword(), request.getRepeatPassword())) {
            return new Result().setCode(ResultCode.OK);
        } else {
            return new Result().setCode(ResultCode.FORBIDDEN);
        }
    }
}
