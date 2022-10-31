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

    @PostMapping("/save_reservation")
    public String saveReservation(@RequestBody String resString) throws IOException{
        Reservation res = new ObjectMapper().readValue(resString, Reservation.class);
        res.setId(new IdGeneratorSnowflake().snowflakeId());
        getBll().setModel(res);
        HttpResult httpResult = getBll().save();
        return new ObjectMapper().writeValueAsString(httpResult);
    }

    @DeleteMapping("/delete_reservation/{id}")
    public String delReservation(@PathVariable("id") Long id) throws IOException {
        reservation.setId(id);
        getBll().setModel(reservation);
        HttpResult httpResult = getBll().delete();
        return new ObjectMapper().writeValueAsString(httpResult);
    }

    @GetMapping("/find_reservation/{pageNumber}/{rowsCount}/{dispose}")
    public String findReservation(@PathVariable("pageNumber") Integer pageNumber,
                                  @PathVariable("rowsCount") Integer rowsCount,
                                  @PathVariable("dispose") String dispose) throws IOException {
        Map<String, Object> cons = new HashMap<>();
        if (!"null".equals(dispose) && dispose != null &&
                !"".equals(dispose))
            cons.put("state",dispose);
        HttpResult httpResult = getBll().findByPage(cons, pageNumber, rowsCount);
        return new ObjectMapper().writeValueAsString(httpResult);
    }

    @PutMapping("/edit_reservation")
    public String editReservation(@RequestBody String resString) throws IOException {
        Reservation res = new ObjectMapper().readValue(resString, Reservation.class);
        return null;
    }
}
