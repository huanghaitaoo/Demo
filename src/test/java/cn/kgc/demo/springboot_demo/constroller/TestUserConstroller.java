package cn.kgc.demo.springboot_demo.constroller;

import cn.kgc.demo.springboot_demo.SpringbootDemoApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMultipartHttpServletRequestDsl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootDemoApplication.class)
@AutoConfigureMockMvc //启用MockMvc
public class TestUserConstroller {
    @Autowired
    private UserConstroller userConstroller;

    @Autowired
    private MockMvc mockMvc; //发送和接收web的请求对象

    @Test
    public void testLoginPage() throws Exception {
        String url = "/loginPage";
        //封装成一个get请求
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url);
        //设置可以接收响应的内容
        builder.accept(MediaType.TEXT_HTML);
        //用mockMvc发送请求
        ResultActions actions = mockMvc.perform(builder);
        //获取行为的结果
        MvcResult mvcResult = actions.andReturn();
        //获取响应
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        String content = response.getContentAsString();
        System.out.println(status);
        System.out.println(content+"-------------++++");
    }
    @Test
    public void testLogin(){

    }
    @Test
    public void testRegistePage(){

    }
    @Test
    public void testRegiste(){

    }
    @Test
    public void testUserList() throws Exception {
        String url = "/userList";
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url);
        builder.accept(MediaType.TEXT_HTML);

        ResultActions actions =mockMvc.perform(builder);
        MvcResult result = actions.andReturn();

        MockHttpServletResponse response = result.getResponse();
        System.out.println(response.getContentAsString());
    }
}
