package cn.kgc.demo.springboot_demo.constroller;

import cn.kgc.demo.springboot_demo.pojo.User;
import cn.kgc.demo.springboot_demo.service.IDUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Controller
public class UserConstroller {

    @Autowired
    private IDUserService userService;
    private ReactiveRedisOperations<Object, Object> redisTemplate;

    //登录界面
    @GetMapping("/loginPage")
    public String loginPage(){
        return "login";
    }

    //处理登录验证
    @PostMapping("/login")
    public String login(String userCode, String userPosswrod, Model model, HttpSession session){
        String result="redirect:/userList";
        boolean bool = false;
        String msg = null;
        try{
            bool = this.userService.verify(userCode,userPosswrod);
            if(!bool){
                msg="登录失败,密码不匹配";
            }else {
                //在session中保存当前的用户名
                session.setAttribute("userCode",userCode);
                //往Redis中添加登录标记
                this.redisTemplate.opsForValue().set(userCode,"hasLogin");
                Duration duration = Duration.ofSeconds(1000);
                this.redisTemplate.expire(userCode, duration);
            }
        }catch (Exception e){
            msg = "登录失败: "+e.getMessage();
        }
        if(!bool){
            model.addAttribute("msg",msg);
            result = "redirect:/loginPage";
        }
        return result;
    }

    //注册页面
    @GetMapping("/registePage")
    public String registePage(){
        return "register";
    }

    //处理注册
    @PostMapping("/registe")
    public String registe(User user,Model model){
        String result = "redirect:/loginPage";
        String msg = null;
        try {
            this.userService.register(user);
        }catch (Exception e){
            msg = "注册失败:"+e.getMessage();
            model.addAttribute("msg",msg);
            result = "redirect:/registePage";
        }
        return result;
    }

    //显示用户列表
    @GetMapping("/userList")
    public String userList(Model model){
        List<User> userList = this.userService.searchAll();
        model.addAttribute("userList",userList);
        return "userList";
    }
}
