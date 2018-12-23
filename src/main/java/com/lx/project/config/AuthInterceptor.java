package com.lx.project.config;

import com.lx.project.base.CurrentUser;
import com.lx.project.base.CurrentUserContext;
import com.lx.project.consts.Consts;
import com.lx.project.entity.SysAccessLog;
import com.lx.project.service.ISysAccessLogService;
import com.lx.project.service.ISysUserService;
import com.lx.project.utils.JSONModel;
import com.lx.project.utils.JacksonUtils;
import com.lx.project.utils.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Enumeration;

public class AuthInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

    private ISysUserService sysUserService;

    private ISysAccessLogService sysAccessLogService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    private String[] ignoreUrls = "/,/**.css,/**.js,/hs,/**.html,/sys/user/signIn,/swagger*,/v2/api-docs".split(",");

    public AuthInterceptor() {
        log.info("ignoreUrls:{}", Arrays.toString(ignoreUrls));
    }

    private static final InheritableThreadLocal<Integer> sysAccessLogIdHub = new InheritableThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String ip = WebUtils.getIpAddr(request);
        String uri = request.getRequestURI();
        String method = request.getMethod().toUpperCase();

        String url = uri;
        String q = request.getQueryString();
        if (StringUtils.isNoneBlank(q)) {
            url = uri + '?' + q;
        }
        // all parameters
        StringBuilder params = new StringBuilder();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {

            if (params.length() > 0) {
                params.append("&");
            }

            String name = parameterNames.nextElement();
            String value = request.getParameter(name);
            params.append(name);
            params.append("=");
            params.append(value);
        }

        String info = "";
        if (o instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) o;
            Method m = hm.getMethod();
            info = m.getDeclaringClass().getSimpleName() + "." + m.getName();
        }

        // 只能取一次
        // String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        // 忽略健康检测日志，太多了
        if ("/hs".equals(uri)) {
            response.getOutputStream().write("OK".getBytes("UTF-8"));// the end
            return false;
        }

        log.info("req:{},{}:{},{}", url, ip, request.getMethod(), info);
        int id = sysAccessLogService.add(url,ip,method,request.getHeader("User-Agent"));
        sysAccessLogIdHub.set(id);

        for (String rule : ignoreUrls) {
            boolean b = antPathMatcher.match(rule, uri);
            if (b) {
                return true;
            }
        }

        // 权限
        String token = request.getHeader(Consts.TOKEN);
        if (StringUtils.isBlank(token)) {
            log.error("token is empty ip:{}", ip);
            outError(id, response, "999990", "未登录或登录失效");
            return false;
        }

        CurrentUser currentUser = sysUserService.queryByAccessToken(token);
        if (null == currentUser) {
            log.error("token is error ip:{},token:{}", ip, token);
            outError(id, response, "999990", "登录失效，请重新登录");
            return false;
        }

        sysAccessLogService.updateAccountById(id, currentUser.getDomainName());
        CurrentUserContext.set(currentUser);

        return true;//过
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        CurrentUserContext.reset();

        int id = sysAccessLogIdHub.get();
        sysAccessLogIdHub.remove();
        sysAccessLogService.updateEndTime(LocalDateTime.now(),id);
    }

    private void outError(int id, HttpServletResponse response, String code, String msg) throws IOException {
        JSONModel json = new JSONModel();
        json.setMsg(msg);
        json.setCode(code);
        String res = JacksonUtils.toJson(json);

        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.getOutputStream().write(res.getBytes("UTF-8"));

        sysAccessLogService.updateEndTime(LocalDateTime.now(),id);
    }

    public ISysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(ISysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public ISysAccessLogService getSysAccessLogService() {
        return sysAccessLogService;
    }

    public void setSysAccessLogService(ISysAccessLogService sysAccessLogService) {
        this.sysAccessLogService = sysAccessLogService;
    }
}
