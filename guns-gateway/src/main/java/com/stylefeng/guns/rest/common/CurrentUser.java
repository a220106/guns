package com.stylefeng.guns.rest.common;

import com.stylefeng.guns.api.user.UserInfoModel;
import org.springframework.stereotype.Service;


@Service
public class CurrentUser {

    private static final  ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static String getCurrentUser(){
        return threadLocal.get();
    }
    public static void  saveUserID(String userID){
        threadLocal.set(userID);
    }

    /*public static UserInfoModel getCurrentUser(){
        return threadLocal.get();
    }

    public static void  saveUserInfo(UserInfoModel userInfoModel){
        threadLocal.set(userInfoModel);
    }*/
}
