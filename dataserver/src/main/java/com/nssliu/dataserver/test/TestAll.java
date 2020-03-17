package com.nssliu.dataserver.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nssliu.dataserver.service.esinterface.EsInput;
import com.nssliu.dataserver.service.esinterface.EsInputInternet;
import com.nssliu.dataserver.utils.HttpRequests;
import com.nssliu.dataserver.utils.PropertiesUtils.PropertiesUtil;
import com.nssliu.dataserver.utils.classloader.MyClassLoader1;
import com.nssliu.dataserver.utils.es.EsUtils;
import com.nssliu.dataserver.utils.time.TimeUtil;
import com.nssliu.dataserver.utils.yml.YmlUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/2/26 14:57
 * @describe:
 */
public class TestAll {
    @Test
    public void testdbes(){
        /*TransportClient client = EsUtils.getClient();
        System.out.println(client);*/
        Map m1,es,create;
        m1 = YmlUtils.yamlTree();
        es = (Map)m1.get("javaToEs");

        String javaName =  es.get("javaName").toString();
        create = (Map) es.get("create");
        String index = create.get("index").toString();
        String type = create.get("type").toString();
        try{

            String className = javaName;
            EsInput esInput = new EsInputInternet();
            MyClassLoader1 classloader = new MyClassLoader1("D:\\0liuzh\\0study\\0githubs\\allproject\\0createEntity\\", "myClassloader");
            Class clazz = classloader.loadClass(className.substring(0, 1).toUpperCase() + className.substring(1));
            esInput.buildStruct(EsUtils.getClient(),clazz,index,type);
        }catch (Exception e){

        }
    }

    @Test
    public void yamls() throws ClassNotFoundException {

        Map m1,m2,m3,m4,m5,m6,m7;
        m1 = YmlUtils.yamlTree();
        //通过map我们取值就可以了.

        m4 = (Map) m1.get("cc");
        List list = (List) m4.get("parent");
        m5 = (Map)list.get(0);
        String name = (String)m5.get("name");
        List childs = (List)m5.get("childs");
        Integer type = (Integer)m5.get("type");

        for(int i = 0;i<childs.size();i++) {
            Map map = (Map) childs.get(i);
            String chiName = (String) map.get("name");
            String chiCode =  map.get("code").toString();
            System.out.println(chiCode);
            System.out.println(chiName);
        }

    }


    @Test
    public void testdbesSaveData() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        String indexName = null;
        String indexType = null;

        Map root,es,create;
        root = YmlUtils.yamlTree();
        es = (Map)root.get("javaToEs");

        String javaName =  es.get("javaName").toString();
        create = (Map) es.get("create");
        indexName = create.get("index").toString();
        indexType = create.get("type").toString();


        String className = "Fj";
        EsInput esInput = new EsInputInternet();
        MyClassLoader1 classloader = new MyClassLoader1("D:\\0liuzh\\0study\\0githubs\\allproject\\0createEntity\\", "myClassloader");
        Class clazz = classloader.loadClass(className.substring(0, 1).toUpperCase() + className.substring(1));

        Field area_name = clazz.getDeclaredField("area_name");
        Field area_code = clazz.getDeclaredField("area_code");
        Field date_date = clazz.getDeclaredField("date_date");
        Field date_str = clazz.getDeclaredField("date_str");
        Field price = clazz.getDeclaredField("price");
        area_name.setAccessible(true);
        area_code.setAccessible(true);
        date_date.setAccessible(true);
        date_str.setAccessible(true);
        price.setAccessible(true);


        Map m1,m2,m3,m4,m5,m6,m7;
        m1 = YmlUtils.yamlTree();
        //通过map我们取值就可以了.

        m4 = (Map) m1.get("cc");
        List list = (List) m4.get("parent");
        m5 = (Map)list.get(0);

        String httpUri = (String) m4.get("httpUri");
        String parentName = (String)m5.get("name");
        String parentNames = (String)m5.get("names");
        String parentCode = (String)m5.get("code");
        List childs = (List)m5.get("childs");
        Integer type = (Integer)m5.get("type");
        String s1 = HttpRequests.sendGet(httpUri + "?defaultCityName=" + parentName + "&region=");
        System.out.println(s1);
        Object series = JSON.parseObject(s1).get("series");

        JSONArray objects = JSON.parseArray(series.toString());
        Object parse = JSON.parseObject(objects.get(0).toString());
        Object data = JSON.parseObject(parse.toString()).get("data");
        JSONArray objects1 = JSON.parseArray(data.toString());
        List<Object> listdata = new ArrayList<>();
        Map <String,Object> mapsp = new HashMap<>();
        for (int i = 0;i<objects1.size();i++){
            //System.out.println(objects1.get(i));
            JSONArray objects2 = JSON.parseArray(objects1.get(i).toString());
            System.out.println(objects2.get(0)+":::"+objects2.get(1));
            Object o = clazz.newInstance();
            area_name.set(o,parentNames);
            area_code.set(o,parentCode);
            String timestrp = TimeUtil.testTimeStamp(objects2.get(0).toString());
            date_date.set(o, TimeUtil.testTimeStamp(objects2.get(0).toString()));
            date_str.set(o,TimeUtil.testTimeStamp(objects2.get(0).toString()));
            price.set(o,new Double(objects2.get(1).toString()));
            //listdata.add(o);
            mapsp.put(timestrp,o);
        }
        mapsp.forEach((k, v) -> {
            listdata.add(v);

        });

        esInput.saveDatas(EsUtils.getClient(),clazz,listdata,indexName,indexType);


//-==============================二手房价同步市级别结束   开始同步新房


        //保存市级别
        for(int i = 0;i<childs.size();i++) {
            List<Object> listdata2 = new ArrayList<>();
            Map <String,Object> maps = new HashMap<>();
            Map map = (Map) childs.get(i);
            String chiName = (String) map.get("name");
            String chiNames = (String) map.get("names");
            String chiCode =  map.get("code").toString();

            String s = HttpRequests.sendGet(httpUri + "?defaultCityName=" + parentName + "&region=" + chiName);
            Object series1 = JSON.parseObject(s).get("series");

            JSONArray objects11 = JSON.parseArray(series1.toString());
            Object parse1 = JSON.parseObject(objects11.get(0).toString());
            Object data1 = JSON.parseObject(parse1.toString()).get("data");
            JSONArray objects111 = JSON.parseArray(data1.toString());
            for (int j = 0;j<objects111.size();j++){
                //System.out.println(objects1.get(i));
                JSONArray objects2 = JSON.parseArray(objects111.get(j).toString());
                System.out.println(objects2.get(0)+":::"+objects2.get(1));
                Object o = clazz.newInstance();
                area_name.set(o,chiNames);
                area_code.set(o,chiCode);
                String timestr = TimeUtil.testTimeStamp(objects2.get(0).toString());
                date_date.set(o, TimeUtil.testTimeStamp(objects2.get(0).toString()));
                date_str.set(o,TimeUtil.testTimeStamp(objects2.get(0).toString()));
                price.set(o,new Double(objects2.get(1).toString()));
                maps.put(timestr,o);
                //listdata2.add(o);
            }
            //map转换list
            maps.forEach((k, v) -> {
                listdata2.add(v);

            });


            //保存区县级别
            esInput.saveDatas(EsUtils.getClient(),clazz,listdata2,indexName,indexType);
        }


    }

    @Test
    public void  aa(){
        Class<Object> objectClass = Object.class;
        //objectClass.getDeclaredMethod()
    }
}
