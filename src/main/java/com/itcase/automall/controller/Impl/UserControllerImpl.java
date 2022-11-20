package com.itcase.automall.controller.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.logging.Handler;
import java.util.stream.Collectors;

@Slf4j
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

/*    @Autowired
    private MailService mailService ;*/

    @Override
    public AbsSuperService getBll() {
        return userService;
    }

    @GetMapping( value="sendcode/{email:.+}")
    public String sendCode(@PathVariable("email") String email) {

        HttpResult httpResult = ((UserServiceImpl) getBll()).sendCode(email);
        if (httpResult.getCode().equals("200")) {
            return "200";
        }else {
            return "401";
        }
    }

    @GetMapping("checkcode/{email}/{code}")
    public HttpResult checkCode(
            @PathVariable("email") String email,
            @PathVariable("code") String code
    ) {
        return ((UserServiceImpl) getBll()).checkCode(email, code);
    }

    // 用户注销 / 管理员删除用户
    @DeleteMapping("/delete_user/{id}")
    public HttpResult delUser(@PathVariable("id") Long id) throws IOException {
        user.setId(id);
        getBll().setModel(user);
        HttpResult httpResult = getBll().delete();
        return httpResult;
    }

    //批量删除用户
    @PostMapping("/batch_del_user/{ids}")
    public HttpResult batchDelUsers(@PathVariable("ids") String ids) throws IOException {
//        HttpResult httpResult = getBll().batchDel(ids);
//        return httpResult;
        List<Long> list = Arrays.stream(ids.split("-")).map(item -> {
            return Long.parseLong(item);
        }).collect(Collectors.toList());
        return getBll().batchDel(list);
    }

    //用户登录
    @PostMapping("/login")
    public HttpResult UserLogin(@RequestBody HashMap<String, Object> cons) throws IOException {
        String code = (String) redisUtil.get(cons.get("email").toString());
        if ( !cons.get("code").toString().equals(code) ) {
            return new HttpResult("401", "验证码错误", null);
        }
        cons.put("password", MD5Util.inputPassToFormPass(cons.get("password").toString()));
        HttpResult httpResult = ((UserServiceImpl)getBll()).findByEmail(cons);
        return httpResult;
    }

    //修改用户信息
    @PutMapping("/edit_user")
    public HttpResult editUser(@RequestBody User user) throws IOException {
        user.setPassword(MD5Util.inputPassToFormPass(user.getPassword()));
        getBll().setModel(user);
        return getBll().update();
    }

    //新用户注册
    @PostMapping("/save_user")
    public HttpResult addUser(@RequestBody User user) throws IOException {
        user.setId(new IdGeneratorSnowflake().snowflakeId());
        user.setPassword(MD5Util.inputPassToFormPass(user.getPassword()));
        getBll().setModel(user);
        HttpResult httpResult = getBll().save();
        return httpResult;
    }

    //分页查询用户
    @GetMapping("/find_by_page_user/{pageNumber}/{rowsCount}/{telephone}/{accountName}")
    public HttpResult findByPage(@PathVariable("pageNumber") Integer pageNumber,
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
        return httpResult;
    }

    //查询用户信息
    @GetMapping("/find_user/{id}")
    public HttpResult findUser(@PathVariable("id") Long id)  throws IOException {
        user.setId(id);
        getBll().setModel(user);
        return getBll().findById();
    }
}
