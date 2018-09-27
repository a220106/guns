package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.user.UserAPI;
import com.stylefeng.guns.api.user.UserInfoModel;
import com.stylefeng.guns.api.user.UserModel;
import com.stylefeng.guns.rest.common.CurrentUser;
import com.stylefeng.guns.rest.modular.auth.VO.ResponseVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user/")
@RestController
public class UserController {

    @Reference(interfaceClass = UserAPI.class)
    UserAPI userAPI;

    @RequestMapping(value = "register",method = RequestMethod.POST)
    public ResponseVO register(UserModel userModel){
        if (userModel.getUsername()==null||userModel.getUsername().trim().length()==0){
            return ResponseVO.serviceFail("用户名不能为空");
        }
        if (userModel.getPassword()==null||userModel.getPassword().trim().length()==0){
            return ResponseVO.serviceFail("密码不能为空");
        }
        boolean isSuccess = userAPI.register(userModel);
        if (isSuccess){
            return  ResponseVO.sucess("注册成功");
        }else {
            return ResponseVO.serviceFail("注册失败");
        }
    }
    @RequestMapping(value = "check",method = RequestMethod.POST)
    public ResponseVO check(@RequestParam String userName) {
        if (userName!=null&&userName.trim().length()>=0){
            boolean notExists = userAPI.checkUsername(userName);
            if (notExists)
                return ResponseVO.sucess("用户名不存在");
            else
                return ResponseVO.sucess("用户名已存在");
        }else
            return ResponseVO.serviceFail("用户名不能为空");

    }
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public ResponseVO logout() {
        return ResponseVO.sucess("用户名退出成功");
    }
    @RequestMapping(value = "getUserInfo",method = RequestMethod.GET)
    public ResponseVO getUserInfo() {
        String userId = CurrentUser.getCurrentUser();
        if (userId!=null||userId.trim().length()>=0){
            int uuid = Integer.valueOf(userId);
            UserInfoModel userInfoModel = userAPI.getUserInfo(uuid);
            if (userInfoModel!=null){
                return ResponseVO.sucess(userInfoModel);
            }else {
                return ResponseVO.serviceFail("用户查询失败");
            }
        }
        return ResponseVO.sucess("用户名未登录");
    }
    @RequestMapping(value = "updateUserInfo",method = RequestMethod.POST)
    public ResponseVO updateUserInfo(UserInfoModel userInfoModel) {
        String userId = CurrentUser.getCurrentUser();
        if (userId!=null||userId.trim().length()>=0){
            int uuid = Integer.valueOf(userId);
            if (uuid!=userInfoModel.getUuid())
                return ResponseVO.serviceFail("业务异常");
            UserInfoModel userInfo = userAPI.updateUserInfo(userInfoModel);
            if (userInfoModel!=null){
                return ResponseVO.sucess(userInfo);
            }else {
                return ResponseVO.serviceFail("修改失败");
            }
        }
        return ResponseVO.sucess("用户名未登录");
    }
}
