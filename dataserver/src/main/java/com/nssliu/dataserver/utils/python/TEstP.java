package com.nssliu.dataserver.utils.python;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nssliu.dataserver.entity.AqiData;
import com.nssliu.dataserver.trueversion.annotations.TableFieldDetails;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/18 17:10
 * @describe:
 */
public class TEstP {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        List list = new ArrayList();
        String s = ExecPythonUtils.execPython("E:\\supermap\\work\\2020\\长春\\空气质量\\", "cc.py");
        //System.out.println(s);
        JSONObject jsonObject = JSON.parseObject(s);
        String time = jsonObject.get("updateTime").toString();
        System.out.println(time);
        JSONObject jsonObject1 = JSON.parseObject(jsonObject.get("data").toString());
        System.out.println(jsonObject1.get("岱山公园"));
        Iterator<Map.Entry<String, Object>> iterator = jsonObject1.entrySet().iterator();
        Class<AqiData> aqiDataClass = AqiData.class;
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            Iterator<Map.Entry<String, Object>> iterator1 = JSON.parseObject(entry.getValue().toString()).entrySet().iterator();

            AqiData aqiData = aqiDataClass.newInstance();
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
        System.out.println(list);
    }
}
