package com.itcase.automall.controller.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcase.automall.bll.Impl.AbsSuperService;
import com.itcase.automall.controller.inter.ICarController;
import com.itcase.automall.entity.Car;
import com.itcase.automall.utils.HttpResult;
import com.itcase.automall.utils.snowflake.IdGeneratorSnowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/car")
public class CarControllerImpl extends AbsSuperController implements ICarController {

    @Autowired
    private Car car;

    @Autowired
    @Qualifier("carServiceImpl")
    private AbsSuperService carService;

    @Override
    public AbsSuperService getBll() {
        return carService;
    }

    //查询单个汽车的信息
    @GetMapping("/find_car/{id}")
    public HttpResult findCar(@PathVariable("id") Long id) throws IOException {
        car.setId(id);
        getBll().setModel(car);
        return getBll().findById();
    }

    //根据id删除汽车对象
    @DeleteMapping("/delete_car/{id}")
    public HttpResult deleteCar(@PathVariable Long id) throws IOException {
        car.setId(id);
        getBll().setModel(car);
        HttpResult httpResult = getBll().delete();
        return httpResult;
    }

    //多条件分页查询汽车及类型
    @GetMapping("/find_by_page_car/{pageNumber}/{rowsCount}/{minPrice}/{maxPrice}/{shelvesTime}/{genre}/{brand}")
    public HttpResult findByPage(@PathVariable("pageNumber") Integer pageNumber,
                             @PathVariable("rowsCount") Integer rowsCount,
                             @PathVariable("minPrice") Float minPrice,
                             @PathVariable("maxPrice") Float maxPrice,
                             @PathVariable("shelvesTime") String shelvesTime,
                             @PathVariable("genre") String genre,
                             @PathVariable("brand") String brand) throws IOException, ParseException {
        System.out.println("数据类型为："+shelvesTime.getClass().getSimpleName()+",值为："+shelvesTime);
        HashMap<String, Object> cons = new HashMap<>();
        if (minPrice != null && minPrice != -1)
            cons.put("minPrice", minPrice);
        if (maxPrice != null && maxPrice != -1)
            cons.put("maxPrice", maxPrice);
        if (!"-1".equals(shelvesTime) && shelvesTime != null)
            cons.put("shelvesTime",new SimpleDateFormat("yyyy-MM-dd").parse(shelvesTime));
        if (!"-1".equals(genre) && genre != null)
            cons.put("genre",genre);
        if (!"-1".equals(brand) && brand != null)
            cons.put("brand",brand);
        HttpResult httpResult = getBll().findByPage(cons, pageNumber, rowsCount);
        return httpResult;
    }

    //新增汽车对象
    @PostMapping("/save_car")
    public HttpResult saveCar(@RequestBody Car car) throws IOException {
//        Car carObj = new ObjectMapper().readValue(carStr, Car.class);
//        carObj.setId(new IdGeneratorSnowflake().snowflakeId());
        car.setId(new IdGeneratorSnowflake().snowflakeId());
        getBll().setModel(car);
        HttpResult httpResult = getBll().save();
        return httpResult;
    }

    //根据id修改汽车信息
    @PutMapping("/edit_car")
    public HttpResult editCar(@RequestBody Car car) throws IOException {
        getBll().setModel(car);
        HttpResult httpResult = getBll().update();
        return httpResult;
    }

    //批量删除汽车信息
    @PostMapping("/batch_del_car/{ids}")
    public HttpResult batchDel(@PathVariable("ids") String ids) throws IOException {
//        HttpResult httpResult = getBll().batchDel(ids);
//        return httpResult;
        List<Long> list = Arrays.stream(ids.split("-")).map(item -> {
            return Long.parseLong(item);
        }).collect(Collectors.toList());
        return getBll().batchDel(list);
    }

    @GetMapping("/getAll")
    public HttpResult getAll() {
        return getBll().findAll();
    }
}
