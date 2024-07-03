package com.wanan.bigevent.service.impl;

import com.wanan.bigevent.mapper.UserMapper;
import com.wanan.bigevent.pojo.User;
import com.wanan.bigevent.service.UserService;
import com.wanan.bigevent.utils.Md5Util;
import com.wanan.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUserName(String username) {
        return userMapper.findUserByUserName(username);
    }

    @Override
    public void register(String username, String password) {
        userMapper.add(username, Md5Util.getMD5String(password));
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatar) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatar, id);
    }

    @Override
    public void updatePwd(String newPwd, Integer id) {
        userMapper.updatePwd(newPwd, id);
    }
}
