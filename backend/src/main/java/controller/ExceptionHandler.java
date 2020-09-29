package controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;

//@RestController
//@RequestMapping
public class ExceptionHandler implements ErrorController {
    @Value("${spring.server.path:#{'/error'}}")
    private String errorPath;
//
//    @RequestMapping
//    public Result error(HttpServletRequest request) {
//        Result result = new Result();
//        result.setCode(ResultCode.BAD_REQUEST);
//        result.setMessage(request.toString());
//        System.out.println(request.toString());
//        return result;
//    }
//
    @Override
    public String getErrorPath() {
        return errorPath;
    }
}
