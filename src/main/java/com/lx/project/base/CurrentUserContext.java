package com.lx.project.base;

import org.springframework.core.NamedInheritableThreadLocal;

public abstract class CurrentUserContext {

    private static final NamedInheritableThreadLocal<CurrentUser> userInfoDtoHolder = new NamedInheritableThreadLocal<>("user information");

    /**
     * resetUserInfo
     */
    public static void reset() {
        userInfoDtoHolder.remove();
    }


    /**
     * setUserInfo
     * @param currentUser
     */
    public static void set(CurrentUser currentUser) {
        userInfoDtoHolder.set(currentUser);
    }

    /**
     * getUserInfo
     * @return
     */
    public static CurrentUser get() {
        return userInfoDtoHolder.get();
    }
}

