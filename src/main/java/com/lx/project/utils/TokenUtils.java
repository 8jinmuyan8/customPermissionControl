package com.lx.project.utils;

// 先这样设计
public class TokenUtils {

    private static String _key = "xIwqeftolp";

    public static String encrypt(String source) {
        return AESUtils.encrypt(source, _key);
    }

    public static String decrypt(String token) {
        return AESUtils.decrypt(token, _key);
    }


}
