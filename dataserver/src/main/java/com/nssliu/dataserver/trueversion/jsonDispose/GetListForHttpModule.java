package com.nssliu.dataserver.trueversion.jsonDispose;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nssliu.dataserver.entity.AqiData;
import com.nssliu.dataserver.trueversion.annotations.TableFieldDetails;
import com.nssliu.dataserver.trueversion.entity.CallBackEntity;
import com.nssliu.dataserver.utils.python.ExecPythonUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/19 10:08
 * @describe:
 */
public class GetListForHttpModule implements GetListForHttp{
    static String indexName = "es索引名";
    static String indexType = "estype名";
    static Class clazz = AqiData.class;//存储的数据模板
    @Override
    public CallBackEntity getCreatinformation() {
        return new CallBackEntity(indexName,indexType,"",clazz,"",null);
    }

    @Override
    public CallBackEntity getList() throws IllegalAccessException, InstantiationException {
        CallBackEntity callBackEntity = new CallBackEntity();
        callBackEntity.setIndexName(indexName);
        callBackEntity.setType(indexType);
        callBackEntity.setClazz(clazz);
        List list = new ArrayList();


        try {

            callBackEntity.setList(list);
        }catch (Exception e){
            e.printStackTrace();
        }


        return callBackEntity;
    }
}
