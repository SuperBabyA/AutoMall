package com.itcase.automall.controller.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcase.automall.bll.Impl.AbsSuperService;
import com.itcase.automall.controller.inter.IReservationController;
import com.itcase.automall.dao.inter.IDoData;
import com.itcase.automall.entity.Reservation;
import com.itcase.automall.utils.HttpResult;
import com.itcase.automall.utils.snowflake.IdGeneratorSnowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/reservation")
public class ReservationController extends AbsSuperController implements IReservationController {

    @Autowired
    private Reservation reservation;

    @Autowired
    @Qualifier("reservationServiceImpl")
    private AbsSuperService bll;

    @Override
    public AbsSuperService getBll() {
        return bll;
    }

    //保存预约
    @PostMapping("/save_reservation")
    public HttpResult saveReservation(@RequestBody String resString) throws IOException{
        Reservation res = new ObjectMapper().readValue(resString, Reservation.class);
        res.setId(new IdGeneratorSnowflake().snowflakeId());
        getBll().setModel(res);
        HttpResult httpResult = getBll().save();
        return httpResult;
    }

    //删除预约
    @DeleteMapping("/delete_reservation/{id}")
    public HttpResult delReservation(@PathVariable("id") Long id) throws IOException {
        reservation.setId(id);
        getBll().setModel(reservation);
        HttpResult httpResult = getBll().delete();
        return httpResult;
    }

    //分页条件查询
    @GetMapping("/find_reservation/{pageNumber}/{rowsCount}/{dispose}")
    public HttpResult findReservation(@PathVariable("pageNumber") Integer pageNumber,
                                  @PathVariable("rowsCount") Integer rowsCount,
                                  @PathVariable("dispose") String dispose) throws IOException {
        Map<String, Object> cons = new HashMap<>();
        if (!"-1".equals(dispose) && dispose != null &&
                !"".equals(dispose))
            cons.put("dispose",dispose);
        HttpResult httpResult = getBll().findByPage(cons, pageNumber, rowsCount);
        return httpResult;
    }

    //修改预约
    @PutMapping("/edit_reservation")
    public HttpResult editReservation(@RequestBody String resString) throws IOException {
        Reservation res = new ObjectMapper().readValue(resString, Reservation.class);
        getBll().setModel(res);
        HttpResult httpResult = getBll().update();
        return httpResult;
    }
}
