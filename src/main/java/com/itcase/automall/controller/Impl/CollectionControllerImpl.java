package com.itcase.automall.controller.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcase.automall.bll.Impl.AbsSuperService;
import com.itcase.automall.bll.Impl.CollectionServiceImpl;
import com.itcase.automall.controller.inter.ICollectionController;
import com.itcase.automall.entity.Collection;
import com.itcase.automall.utils.HttpResult;
import com.itcase.automall.utils.snowflake.IdGeneratorSnowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/collection")
public class CollectionControllerImpl extends AbsSuperController implements ICollectionController {

    @Autowired
    private Collection collection;

    @Autowired
    private CollectionServiceImpl collectionService;

    @Autowired
    @Qualifier("collectionServiceImpl")
    private AbsSuperService bll;

    @Override
    public AbsSuperService getBll() {
        return bll;
    }

    //用户删除收藏
    @DeleteMapping("/delete_collection/{id}")
    public HttpResult delCollection(@PathVariable("id") Long id) throws IOException {
        collection.setId(id);
        getBll().setModel(collection);
        HttpResult httpResult = getBll().delete();
        return httpResult;
    }

    //批量删除用户收藏
    @PostMapping("/batch_del_collection")
    public HttpResult batchDelColl(@RequestBody List<Long> ids) throws IOException {
        HttpResult httpResult = getBll().batchDel(ids);
        return httpResult;
    }

    //查询单个用户的收藏
    @GetMapping("/find_collection/{userCollectionId}")
    public HttpResult findUserCollection(@PathVariable("userCollectionId") Long userCollectionId) throws IOException {
        collection.setUserCollectionId(userCollectionId);
        collectionService.setModel(collection);
        HttpResult httpResult = collectionService.findUserCollection();
        return httpResult;
    }

    //新增收藏
    @PostMapping("/save_collection")
    public HttpResult saveCollection(@RequestBody String collString) throws IOException {
        Collection coll = new ObjectMapper().readValue(collString, Collection.class);
        coll.setId(new IdGeneratorSnowflake().snowflakeId());
        getBll().setModel(coll);
        HttpResult httpResult = getBll().save();
        return httpResult;
    }


}
