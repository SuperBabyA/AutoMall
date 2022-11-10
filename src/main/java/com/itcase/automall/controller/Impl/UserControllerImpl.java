package com.itcase.automall.controller.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcase.automall.bll.Impl.AbsSuperService;
import com.itcase.automall.bll.Impl.UserServiceImpl;
import com.itcase.automall.controller.inter.IUserController;
import com.itcase.automall.entity.User;
import com.itcase.automall.utils.HttpResult;
import com.itcase.automall.utils.encryption.MD5Util;
import com.itcase.automall.utils.mail.MailService;
import com.itcase.automall.utils.redisUtils.RedisUtil;
import com.itcase.automall.utils.snowflake.IdGeneratorSnowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserControllerImpl extends AbsSuperController implements IUserController {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("userServiceImpl")
    private AbsSuperService userService;

    @Autowired
    private RedisUtil redisUtil ;

    @Autowired
    private MailService mailService ;

    @Override
    public AbsSuperService getBll() {
        return userService;
    }

    @GetMapping("sendcode/{email}")
    public HttpResult sendCode(@PathVariable("email") String email) {
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        mailService.sendCode(email,"AutoMall 验证码", String.valueOf(code));
        redisUtil.set(email, String.valueOf(code), 5 * 60);
        return new HttpResult("200", "发送成功", null);
    }

    @GetMapping("checkcode/{email}/{code}")
    public HttpResult checkCode(
            @PathVariable("email") String email,
            @PathVariable("code") String code
    ) {
        String _code = (String) redisUtil.get(email);
        if (_code == null || _code == "") {
            return new HttpResult("401", "验证码错误", null);
        }
        if (_code.equals(code)) {
            redisUtil.del(email);
            return new HttpResult("200", "验证码正确", null);
        } else {
            return new HttpResult("401", "验证码错误", null);
        }
    }

    // 用户注销 / 管理员删除用户
    @DeleteMapping("/delete_user/{id}")
    public String delUser(@PathVariable("id") Long id) throws IOException {
        user.setId(id);
        getBll().setModel(user);
        HttpResult httpResult = getBll().delete();
        return new ObjectMapper().writeValueAsString(httpResult);
    }

    //批量删除用户
    @PostMapping("/batch_del_user")
    public String batchDelUsers(@RequestBody List<Long> ids) throws IOException {
        System.out.println("@@@"+ids);
        HttpResult httpResult = getBll().batchDel(ids);
        return new ObjectMapper().writeValueAsString(httpResult);
    }

    //用户登录
    @GetMapping("/login_user/{email}/{password}")
    public String UserLogin(@PathVariable String email,
                            @PathVariable String password) throws IOException {
        Map<String, Object> cons = new HashMap<>();
        cons.put("email",email);
        cons.put("password", MD5Util.inputPassToFormPass(password));
        HttpResult httpResult = ((UserServiceImpl)getBll()).findByEmail(cons);
        return new ObjectMapper().writeValueAsString(httpResult);
    }

    //修改用户信息
    @PutMapping("/edit_user")
    public String editUser(@RequestBody String userString) throws IOException {
        User iUser = new ObjectMapper().readValue(userString, User.class);
        iUser.setPassword(MD5Util.inputPassToFormPass(iUser.getPassword()));
        getBll().setModel(iUser);
        HttpResult httpResult = getBll().update();
        return new ObjectMapper().writeValueAsString(httpResult);
    }

    //新用户注册
    @PostMapping("/save_user")
    public String addUser(@RequestBody String userString) throws IOException {
        User iUser = new ObjectMapper().readValue(userString, User.class);
        iUser.setId(new IdGeneratorSnowflake().snowflakeId());
        iUser.setPassword(MD5Util.inputPassToFormPass(iUser.getPassword()));
        getBll().setModel(iUser);
        HttpResult httpResult = getBll().save();
        return new ObjectMapper().writeValueAsString(httpResult);
    }

    //分页查询用户
    @GetMapping("/find_by_page_user/{pageNumber}/{rowsCount}/{telephone}/{accountName}")
    public String findByPage(@PathVariable("pageNumber") Integer pageNumber,
                              @PathVariable("rowsCount") Integer rowsCount,
                              @PathVariable("telephone") Long telephone,
                              @PathVariable("accountName") String accountName) throws IOException {
        HashMap<String, Object> cons = new HashMap<>();
        if (telephone != null && telephone != -1)
            cons.put("telephone", telephone);
        if (!"-1".equals(accountName) && accountName != null &&
                !"".equals(accountName))
            cons.put("accountName",accountName);
        HttpResult httpResult = getBll().findByPage(cons, pageNumber, rowsCount);
        return new ObjectMapper().writeValueAsString(httpResult);
    }

    //查询用户信息
    @GetMapping("/find_user/{id}")
    public String findUser(@PathVariable("id") Long id)  throws IOException {
        user.setId(id);
        getBll().setModel(user);
        HttpResult httpResult = getBll().findById();
        return new ObjectMapper().writeValueAsString(httpResult);
    }
}
