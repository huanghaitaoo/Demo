package cn.kgc.demo.springboot_demo.service.Impl;

import cn.kgc.demo.springboot_demo.SpringbootDemoApplication;
import cn.kgc.demo.springboot_demo.mapper.UserMapper;
import cn.kgc.demo.springboot_demo.pojo.User;
import cn.kgc.demo.springboot_demo.service.IDUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootDemoApplication.class)
public class TestUserServiceImpl {
    @Autowired
    private IDUserService userService;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testVerify(){
        //测试数据
        String userCode = "admin";
        String userPassword = "1234567";

        //期望
        boolean result = true;
        //获取实际结果
        result = userService.verify(userCode,userPassword);
        //断言
        Assert.assertTrue(result);
    }
    @Test
    public void testRegister(){
        User user = new User();
        user.setUsercode("dasd");
        user.setUsername("黄");
        user.setUserpassword("111");
        user.setUserrole(3);

        this.userService.register(user);

        user = this.userMapper.selectByUserCode("list");
        Assert.assertNotNull(user);
    }

    @Test
    public void testSearchAll(){
        List<User> userList = this.userService.searchAll();
        int size =userList.size();
        boolean bool = size == 17;
        Assert.assertTrue(bool);
    }
}
