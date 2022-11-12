package com.itcase.automall.controller.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcase.automall.bll.Impl.AbsSuperService;
import com.itcase.automall.controller.inter.IOrderController;
import com.itcase.automall.entity.Orders;
import com.itcase.automall.utils.HttpResult;
import com.itcase.automall.utils.snowflake.IdGeneratorSnowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("api/order")
public class OrderControllerImpl  extends AbsSuperController implements IOrderController {

    @Autowired
    private Orders orders;

    @Autowired
    @Qualifier("ordersServiceImpl")
    private AbsSuperService bll;

    @Override
    public AbsSuperService getBll() {
        return bll;
    }

    //删除订单
    @DeleteMapping("/delete_order/{id}")
    public HttpResult delOrder(@PathVariable("id") Long id) throws IOException {
        orders.setId(id);
        getBll().setModel(orders);
        HttpResult httpResult = getBll().delete();
        return httpResult;
    }

    //分页多条件查询订单
    @GetMapping("/find_by_page_order/{pageNumber}/{rowsCount}/{userAddressId}/{state}")
    public HttpResult findUserOrder(@PathVariable("pageNumber") Integer pageNumber,
                                @PathVariable("rowsCount") Integer rowsCount,
                                @PathVariable("userAddressId") Long userAddressId,
                                @PathVariable("state") String state) throws IOException {
        HashMap<String, Object> cons = new HashMap<>();
        if (userAddressId != null && userAddressId != -1)
            cons.put("userAddressId", userAddressId);
        if (!"-1".equals(state) && state != null &&
                !"".equals(state))
            cons.put("state",state);
        HttpResult httpResult = getBll().findByPage(cons, pageNumber, rowsCount);
        return httpResult;
    }

    //新增订单
    @PostMapping("/save_order")
    public HttpResult saveOrder(@RequestBody String orderString) throws IOException {
        Orders order = new ObjectMapper().readValue(orderString, Orders.class);
        order.setId(new IdGeneratorSnowflake().snowflakeId());
        order.setOrderNumber(new Date().getTime());
        System.out.println(order);
        getBll().setModel(order);
        HttpResult httpResult = getBll().save();
        return httpResult;
    }

    //修改订单
    @PutMapping("/edit_order")
    public HttpResult editOrder(@RequestBody String orderString) throws IOException {
        Orders order = new ObjectMapper().readValue(orderString, Orders.class);
        getBll().setModel(order);
        HttpResult httpResult = getBll().update();
        return httpResult;
    }
}
