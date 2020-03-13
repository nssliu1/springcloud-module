package com.nssliu.dataserver.service.esinterface;

import java.util.List;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/12 11:16
 * @describe:
 */
public interface EsInput {
    Object buildStruct(Object client, Class clazz,String indexName,String typeName);
    Object saveData(Object client,Class clazz,Object data);
    Object saveDatas(Object client,Class clazz,List datas);
}
