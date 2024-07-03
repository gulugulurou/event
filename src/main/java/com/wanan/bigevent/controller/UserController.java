package com.wanan.bigevent.controller;

import com.auth0.jwt.JWT;
import com.wanan.bigevent.pojo.Result;
import com.wanan.bigevent.pojo.User;
import com.wanan.bigevent.service.UserService;
import com.wanan.bigevent.utils.JwtUtil;
import com.wanan.bigevent.utils.Md5Util;
import com.wanan.bigevent.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.findUserByUserName(username);
        if (user == null) {
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户名已占用");
        }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password)  {
        User byUserName = userService.findUserByUserName(username);
        if (byUserName == null) {
            return Result.error("账号密码错误");
        }
        boolean checkPasswordFlag = Md5Util.checkPassword(password, byUserName.getPassword());
        if (checkPasswordFlag) {
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("id", byUserName.getId());
            claims.put("username", byUserName.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("账号密码错误");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader("Authorization") String token*/) {
        /*Map<String, Object> tokenMap = JwtUtil.parseToken(token);
        String username = (String) tokenMap.get("username");*/

        Map<String, Object> tokenMap = ThreadLocalUtil.get();
        String username = (String) tokenMap.get("username");
        User user = userService.findUserByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam("avatar") @URL String avatar) {
        userService.updateAvatar(avatar);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> map) {
        String oldPwd = map.get("old_pwd");
        String newPwd = map.get("new_pwd");
        String rePwd = map.get("re_pwd");
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }
        Map<String, Object> objMap = ThreadLocalUtil.get();

        String username = (String) objMap.get("username");
        User user = userService.findUserByUserName(username);
        if (! Md5Util.checkPassword(oldPwd, user.getPassword())) {
            return Result.error("原密码填写有误");
        }
        if (!newPwd.equals(rePwd)) {
            return Result.error("两次新密码填写内容不同");
        }
        userService.updatePwd(Md5Util.getMD5String(newPwd), user.getId());
        return Result.success();
    }
}
