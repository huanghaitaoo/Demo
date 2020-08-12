package cn.kgc.demo.springboot_demo.service.Impl;

import cn.kgc.demo.springboot_demo.mapper.UserMapper;
import cn.kgc.demo.springboot_demo.pojo.User;
import cn.kgc.demo.springboot_demo.service.IDUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements IDUserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    @Transactional(readOnly = true)
    public boolean verify(String userCode, String userPassword) {
        boolean result = false;

        System.out.println(userCode);
        User user = userMapper.selectByUserCode(userCode);

        System.out.println(user+"-----------------------------");
        if(user==null){
            throw new RuntimeException(userCode + "不存在!");
        }else {
            result = user.getUserpassword().equals(userPassword);
        }
        return result;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void register(User user) {
        this.userMapper.insert(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> searchAll() {
        return this.userMapper.selectByExample(null);
    }
}
