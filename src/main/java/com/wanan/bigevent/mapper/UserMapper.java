package com.wanan.bigevent.mapper;

import com.wanan.bigevent.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    // 根据用户名查找用户信息
    @Select("select * from user where username = #{username}")
    User findUserByUserName(String username);

    // 添加用户信息
    @Insert("insert into user(username, password, create_time, update_time)" +
            "values (#{username}, #{password}, now(), now())")
    void add(String username, String password);

    @Update("update user set nickname=#{nickname}, email=#{email}, update_time=#{updateTime} where id=#{id}")
    void update(User user);

    @Update("update user set user_pic = #{avatar}, update_time= now() where id = #{id}")
    void updateAvatar(String avatar, Integer id);

    @Update("update user set password = #{newPwd}, update_time= now() where id = #{id}")
    void updatePwd(String newPwd, Integer id);
}
