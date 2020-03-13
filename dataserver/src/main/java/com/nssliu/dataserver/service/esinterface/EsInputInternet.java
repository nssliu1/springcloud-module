package com.nssliu.dataserver.service.esinterface;

import com.nssliu.dataserver.utils.es.EsHashMap;
import com.nssliu.dataserver.utils.es.EsUtils;
import org.elasticsearch.client.transport.TransportClient;

import java.util.List;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/12 11:19
 * @describe:
 */
public class EsInputInternet implements EsInput{
    @Override
    public Object buildStruct(Object client, Class clazz,String indexName,String typeName) {
        String code = null;
        try {

            EsHashMap<String,String> javaEsMapping = (EsHashMap<String, String>) EsUtils.getJavaEsMapping(clazz);
            EsUtils.createStudentIndex((TransportClient)client,javaEsMapping,indexName,typeName);
            code = "正常";
        }catch (Exception e){
            code = "失败";
        }

        return code;
    }

    @Override
    public Object saveData(Object client, Class clazz, Object data) {
        return null;
    }

    @Override
    public Object saveDatas(Object client, Class clazz, List datas,String indexName,String type) {
        try{
            EsUtils.addDoc(clazz,datas,(TransportClient)client,indexName,type);

        }catch (Exception e){

        }
        return null;
    }
}
