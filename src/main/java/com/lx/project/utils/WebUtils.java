package com.lx.project.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

public class WebUtils {


    private static Logger log = LoggerFactory.getLogger(WebUtils.class);

    /**
     * 获取IP
     *
     * @param request
     * @return IP
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }


    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        if (cookieName != null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookieName.equals(cookie.getName()))
                        return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static String getLocalHostIpAddr() {
        InetAddress ia = null;
        String ip = null;
        try {
            ia = ia.getLocalHost();
            ip = ia.getHostAddress();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ip;
    }
}
