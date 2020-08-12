package cn.kgc.demo.springboot_demo.mapper;

import cn.kgc.demo.springboot_demo.SpringbootDemoApplication;
import cn.kgc.demo.springboot_demo.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootDemoApplication.class)
public class TestDmUserMapper {
    @Autowired
    private UserMapper userMapper;
    @Test

    public void testSelectByPrimaryKey(){
        //准备测试数据
        long id = 15;
        System.out.println(id);
        //准备期望值
        // z 张三
        String userCode = "zhaomin";
        String userName = "赵敏";
        //获取实际值
        User user = userMapper.selectByPrimaryKey(id);
        String actUserCode = user.getUsercode();
        String actUserName = user.getUsername();
        Assert.assertEquals(userCode, actUserCode);
        Assert.assertEquals(userName, actUserName);
    }
}
