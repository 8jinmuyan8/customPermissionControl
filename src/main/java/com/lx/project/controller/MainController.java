package com.lx.project.controller;

import com.lx.project.BizException;
import com.lx.project.utils.JSONModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class MainController extends BaseController{


    @GetMapping("/hs")
    public String hs() {
        return "OK";
    }
    /**
     * 全局错误处理
     */
    @ExceptionHandler
    public JSONModel<String> error(Exception e) {
        log.error(e.getMessage(), e);
        if (e instanceof BizException) {
            BizException be = (BizException) e;
            return JSONModel.buildFailure(be.getMessage());
        }
        return JSONModel.buildFailure("服务器忙，请稍后刷新重试");
    }

}
