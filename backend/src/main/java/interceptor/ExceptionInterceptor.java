package interceptor;

//@RestControllerAdvice
public class ExceptionInterceptor {
    public Object handleMethodArgumentNotValidException() {
        return null;
    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {}
//
//    @ResponseBody
//    @ExceptionHandler(Exception.class)
//    public Object handleException(Exception ex) {
//        System.out.println(ex.getMessage());
//        return ex;
//    }
}
