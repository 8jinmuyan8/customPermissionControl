package com.lx.project.utils;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

public class Md5Utils {

    public static String text(String str) {
        try {
            return DigestUtils.md5DigestAsHex(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {

        }
        return null;
    }
}
