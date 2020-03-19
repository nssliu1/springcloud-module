package com.nssliu.dataserver.trueversion.jsonDispose;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nssliu.dataserver.entity.AqiData;
import com.nssliu.dataserver.entity.Fj;
import com.nssliu.dataserver.entity.ScenePoint;
import com.nssliu.dataserver.trueversion.annotations.TableFieldDetails;
import com.nssliu.dataserver.trueversion.entity.CallBackEntity;
import com.nssliu.dataserver.utils.HttpRequests;
import com.nssliu.dataserver.utils.python.ExecPythonUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/17 10:53
 * @describe:
 */
public class GetListForHttpAqi implements  GetListForHttp{
    static String indexName = "aqi";
    static String indexType = "cc";
    static Class clazz = AqiData.class;

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

            String s = ExecPythonUtils.execPython("E:\\supermap\\work\\2020\\长春\\空气质量\\", "cc.py");
            //System.out.println(s);
            JSONObject jsonObject = JSON.parseObject(s);
            String time = jsonObject.get("updateTime").toString();
            System.out.println(time);
            JSONObject jsonObject1 = JSON.parseObject(jsonObject.get("data").toString());
            System.out.println(jsonObject1.get("岱山公园"));
            Iterator<Map.Entry<String, Object>> iterator = jsonObject1.entrySet().iterator();
            Class<AqiData> aqiDataClass = clazz;
            while (iterator.hasNext()){
                Map.Entry entry = (Map.Entry)iterator.next();
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
                Iterator<Map.Entry<String, Object>> iterator1 = JSON.parseObject(entry.getValue().toString()).entrySet().iterator();

                AqiData aqiData = aqiDataClass.newInstance();
                Field updateTime = aqiDataClass.getDeclaredField("updateTime");
                updateTime.setAccessible(true);
                updateTime.set(aqiData,time);
                while (iterator1.hasNext()){
                    Map.Entry entry1 = (Map.Entry)iterator1.next();
                    Field field = aqiDataClass.getDeclaredField(entry1.getKey().toString());
                    field.setAccessible(true);
                    String typeName = field.getGenericType().getTypeName();

                    TableFieldDetails annotation = field.getAnnotation(TableFieldDetails.class);
                    if(annotation!=null){

                        if(typeName.equals("double")){
                            if(entry1.getValue()!=null && !"".equals(entry1.getValue())){

                                field.set(aqiData,new Double(entry1.getValue().toString()));
                            }
                        }else {
                            if(entry1.getValue()!=null && !"".equals(entry1.getValue())){

                                field.set(aqiData,entry1.getValue().toString());
                            }

                        }
                    }

                    System.out.print(entry1.getKey()+":"+entry1.getValue());
                    System.out.println();

                }
                list.add(aqiData);
            }


            callBackEntity.setList(list);
        }catch (Exception e){
            e.printStackTrace();
        }


        return callBackEntity;
    }
}
