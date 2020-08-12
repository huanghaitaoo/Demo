package cn.kgc.demo.springboot_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//实现接口的动态绑定
@MapperScan(basePackages = "cn.kgc.demo.springboot_demo.mapper")
//扫描业务层
@ComponentScan(basePackages = {"cn.kgc.demo.springboot_demo.service.Impl",
                                "cn.kgc.demo.springboot_demo.constroller",
                                "cn.kgc.demo.springboot_demo"})
//开启事务注解
@EnableTransactionManagement
public class SpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }

}
