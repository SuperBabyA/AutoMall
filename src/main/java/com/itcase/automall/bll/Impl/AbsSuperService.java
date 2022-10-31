package com.itcase.automall.bll.Impl;

import com.itcase.automall.dao.inter.IDoData;
import com.itcase.automall.entity.AbsSuperObject;
import com.itcase.automall.utils.HttpResult;
import org.apache.ibatis.binding.BindingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public abstract class AbsSuperService {

    private AbsSuperObject model;

    // 生成getter/setter方法方便调用对应的model对象
    public AbsSuperObject getModel() {
        return model;
    }

    public void setModel(AbsSuperObject model) {
        this.model = model;
    }

    // 定义一个抽象方法 service实现类进行实现并调用对应的dao
    public abstract IDoData getDao();

    //新增对象
    public HttpResult save() {
        HttpResult httpResult = new HttpResult();
        if (model == null || model.getId() == null) {
            httpResult.setCode("C0001");
            httpResult.setMessage("对象或id为空，保存异常！");
            httpResult.setObject(-1);
            return httpResult;
        }
        if (getDao() == null) {
            httpResult.setCode("C0002");
            httpResult.setMessage("未获得当前访问层对象！");
            httpResult.setObject(-1);
            return httpResult;
        }
        try {
            int result = getDao().addObj(model);    //调用dao接口方法
            if (result > 0) {
                httpResult.setCode("C0000");
                httpResult.setMessage("添加成功！");
                httpResult.setObject(result);
            } else {
                httpResult.setCode("C0003");
                httpResult.setMessage("添加失败！");
                httpResult.setObject(-1);
            }
            return httpResult;
        } catch (DuplicateKeyException e) { //捕获插入或更新数据导致违反主键或唯一约束时引发异常
            httpResult.setCode("C0004");
            httpResult.setMessage("该名称已存在！");
            httpResult.setObject(-1);
            return httpResult;
        } catch (DataIntegrityViolationException e) {   //捕获插入或更新数据导致违反外键或唯一约束时引发异常
            httpResult.setCode("C0005");
            httpResult.setMessage("插入数据关联的数据不存在，添加失败！");
            httpResult.setObject(-1);
            return httpResult;
        } catch (Exception e) {     //捕获其他所有异常
            httpResult.setCode("C0006");
            httpResult.setMessage("未知异常，添加失败！");
            httpResult.setObject(-1);
            return httpResult;
        }
    }

    //删除单个对象
    public HttpResult delete() {
        HttpResult httpResult = new HttpResult();
        if (model == null ||
                model.getId() == null ||
                "".equals(model.getId())) {
            httpResult.setCode("R0001");
            httpResult.setMessage("对象或id为空，删除失败！");
            httpResult.setObject(-1);
            return httpResult;
        }
        if (getDao() == null) {
            httpResult.setCode("R0002");
            httpResult.setMessage("未获取到访问层对象！");
            httpResult.setObject(-1);
            return httpResult;
        }
        try {
            int result = getDao().deleteObj(model.getId());     //调用dao接口方法
            if (result > 0) {
                httpResult.setCode("R0000");
                httpResult.setMessage("删除数据成功！");
                httpResult.setObject(result);
            } else {
                httpResult.setCode("R0003");
                httpResult.setMessage("删除数据失败，请重试！");
                httpResult.setObject(-1);
            }
            return httpResult;
        } catch (DataIntegrityViolationException e) {
            httpResult.setCode("R0004");
            httpResult.setMessage("删除有误，删除失败！");
            httpResult.setObject(-1);
            return httpResult;
        } catch (BindingException e) {
            httpResult.setCode("R0005");
            httpResult.setMessage("被删除的数据被依赖无法删除，删除失败！");
            httpResult.setObject(-1);
            return httpResult;
        } catch (Exception e) {
            httpResult.setCode("R0006");
            httpResult.setMessage("被删除的数据被依赖无法删除，删除失败！");
            httpResult.setObject(-1);
            return httpResult;
        }
    }

    //根据id批量删除
    public HttpResult batchDel(List<Long> cons){
        HttpResult httpResult = new HttpResult();
        if (cons == null || cons.size() == 0){
            httpResult.setObject("R0001");
            httpResult.setMessage("删除条件为空，请添加删除条件！");
            httpResult.setObject(-1);
            return httpResult;
        }
        if (getDao() == null){
            httpResult.setCode("R0002");
            httpResult.setMessage("未获得当前访问层对象！");
            httpResult.setObject(-1);
            return httpResult;
        }
        try {
            int result = getDao().batchDeleteByIds(cons);
            if (result > 0){
                httpResult.setCode("R0000");
                httpResult.setMessage("批量删除成功！");
                httpResult.setObject(result);
            }else {
                httpResult.setCode("R0003");
                httpResult.setMessage("批量删除失败！");
                httpResult.setObject(null);
            }
            return httpResult;
        } catch (DataIntegrityViolationException e) {
            httpResult.setCode("R0004");
            httpResult.setMessage("数据被其他数据依赖，批量删除失败！");
            httpResult.setObject(-1);
            return httpResult;
        } catch (BadSqlGrammarException e){
            httpResult.setCode("R0005");
            httpResult.setMessage("未添加批量删除条件，批量删除失败！");
            httpResult.setObject(-1);
            return httpResult;
        }
    }

    //修改对象
    public HttpResult update() {
        HttpResult httpResult = new HttpResult();
        if (model == null || model.getId() == null) {
            httpResult.setCode("U0001");
            httpResult.setMessage("对象或id为空，保存异常！");
            httpResult.setObject(-1);
            return httpResult;
        }
        if (getDao() == null) {
            httpResult.setCode("U0002");
            httpResult.setMessage("未获得当前访问层对象！");
            httpResult.setObject(-1);
            return httpResult;
        }
        try {
            int result = getDao().updateObj(model);
            if (result > 0) {
                httpResult.setCode("U0000");
                httpResult.setMessage("修改成功！");
                httpResult.setObject(result);
            } else {
                httpResult.setCode("U0003");
                httpResult.setMessage("修改失败！");
                httpResult.setObject(-1);
            }
            return httpResult;
        } catch (DuplicateKeyException e) { //捕获插入或更新数据导致违反主键或唯一约束时引发异常
            httpResult.setCode("U0004");
            httpResult.setMessage("该名称已存在！");
            httpResult.setObject(-1);
            return httpResult;
        } catch (DataIntegrityViolationException e) {   //捕获插入或更新数据导致违反外键或唯一约束时引发异常
            httpResult.setCode("U0005");
            httpResult.setMessage("插入数据关联的数据不存在，添加失败！");
            httpResult.setObject(-1);
            return httpResult;
        }
    }

    //查询所有对象
    public HttpResult findAll() {
        HttpResult httpResult = new HttpResult();
        if (getDao() == null) {
            httpResult.setCode("D0001");
            httpResult.setMessage("未获得访问层对象！");
            httpResult.setObject(-1);
            return httpResult;
        }
        List<AbsSuperObject> objects = getDao().findAllObj();       //调用dao接口方法
        if (objects == null || objects.size() == 0) {
            httpResult.setCode("D0002");
            httpResult.setMessage("暂无相应数据！");
            httpResult.setObject(-2);
        } else {
            httpResult.setCode("D0000");
            httpResult.setMessage("已查询出全部数据！");
            httpResult.setObject(objects);
        }
        return httpResult;
    }

    //根据id查询对象
    public HttpResult findById() {
        HttpResult httpResult = new HttpResult();
        if (model == null ||
                model.getId() == null ||
                "".equals(model.getId())) {
            httpResult.setCode("D0001");
            httpResult.setMessage("对象为空，查询失败！");
            httpResult.setObject(-1);
            return httpResult;
        }
        if (getDao() == null) {
            httpResult.setCode("D0002");
            httpResult.setMessage("未获得访问层对象！");
            httpResult.setObject(-1);
            return httpResult;
        }
        AbsSuperObject object = getDao().findById(model.getId());      //调用dao接口方法
        if (object == null) {
            httpResult.setCode("D0003");
            httpResult.setMessage("暂无相应数据！");
            httpResult.setObject(-2);
        } else {
            httpResult.setCode("D0000");
            httpResult.setMessage("已查询出对应数据！");
            httpResult.setObject(object);
        }
        return httpResult;
    }

    //分页查询
    public HttpResult findByPage(Map<String,Object> cons, int pageNumber, int rowsCount){
        HttpResult httpResult = new HttpResult();
        /*        if (cons == null || cons.size() == 0){
            httpResult.setCode("D0001");
            httpResult.setMessage("条件或id为空，查询失败！");
            httpResult.setObject(null);
            return httpResult;
        }*/
        if (pageNumber <= 0 || rowsCount <= 0){
            httpResult.setCode("D0001");
            httpResult.setMessage("所给的页码或每页显示的条数不符合大于零的要求！");
            httpResult.setObject(null);
            return httpResult;
        }
        if (getDao() == null){
            httpResult.setCode("D0002");
            httpResult.setMessage("未获得当前访问层对象！");
            httpResult.setObject(null);
            return httpResult;
        }
        List<Map<String, Object>> result = getDao().findByPage(cons, (pageNumber - 1)*rowsCount, rowsCount);
        if (result != null && result.size() != 0){
            //获取查询总条数
            int total = getDao().getRowsCount(cons);
            httpResult.setCode(String.valueOf(total));
            httpResult.setMessage("系统查询到符合条件的信息【"+total+"】条!");
            httpResult.setObject(result);
        }else {
            httpResult.setCode("D0003");
            httpResult.setMessage("系统没有查询到符合条件的数据!");
            httpResult.setObject(null);
        }
        return httpResult;
    }
}
