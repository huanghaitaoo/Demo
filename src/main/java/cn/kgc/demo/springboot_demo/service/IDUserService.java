package cn.kgc.demo.springboot_demo.service;

import cn.kgc.demo.springboot_demo.pojo.User;

import java.util.List;

public interface IDUserService {
    //匹配密码
    public boolean verify(String userCode,String userPassword);

    //注册
    public void register(User user);

    //查找所有用户
    public List<User> searchAll();
}
