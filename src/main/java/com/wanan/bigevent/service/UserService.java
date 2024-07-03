package com.wanan.bigevent.service;

import com.wanan.bigevent.pojo.User;

public interface UserService {

    User findUserByUserName(String username);

    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatar);

    void updatePwd(String newPwd, Integer id);
}
