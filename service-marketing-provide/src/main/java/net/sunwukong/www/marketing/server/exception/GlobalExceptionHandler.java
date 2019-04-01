package net.sunwukong.www.marketing.server.exception;


import com.sdkinfo.www.core.lang.Console;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


/**
 * 类说明:异常处理类
 *
 * @author ideacoding
 * create 2018-04-11 11:29
 * Email ：ideacoding@163.com
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder 数据绑定器
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {}

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model 绑定Model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "Magical Sam");
    }

    /**
     * 全局异常捕捉处理
     * @param ex 全局异常对象
     * @return 响应客服端消息
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    //@FieldRep(encode = false)
    public ResponseData errorHandler(Exception ex) {
        Console.error("执行全局异常捕捉:{}",ex.getMessage());
        ex.printStackTrace();
        ResponseData responseData = new ResponseData();
        responseData.setMessage(ex.getMessage());
        responseData.setCode(500);
        return responseData;
    }

    /**
     * 拦截捕捉自定义异常 GrilException.class
     * @param ex 自定义异常对象
     * @return  响应客服端消息
     */
    @ResponseBody
    @ExceptionHandler(value = GrilException.class)
    //@FieldRep(encode = false)
    public ResponseData myErrorHandler(GrilException ex) {
        Console.error("执行自定义异常捕捉:{}",ex.getMsg());
        ResponseData responseData = new ResponseData();
        responseData.setMessage(ex.getMsg());
        responseData.setCode(ex.getCode());
        return responseData;
    }

}
