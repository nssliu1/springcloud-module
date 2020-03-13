package com.nssliu.dataserver.service.esinterface;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nssliu.dataserver.utils.HttpRequests;
import com.nssliu.dataserver.utils.classloader.MyClassLoader1;
import com.nssliu.dataserver.utils.es.EsUtils;
import com.nssliu.dataserver.utils.time.TimeUtil;
import com.nssliu.dataserver.utils.yml.YmlUtils;
import org.elasticsearch.search.SearchHits;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/2/26 14:57
 * @describe:
 */
@Service
public class EsService {
    public void createEsIndex(String indexName,String type,String className){

        Map m1,es,create;
        m1 = YmlUtils.yamlTree();
        es = (Map)m1.get("javaToEs");

        String javaName =  es.get("javaName").toString();
        create = (Map) es.get("create");
        if(indexName==null){
            indexName = create.get("index").toString();

        }
        if (type == null) {
            type = create.get("type").toString();
        }
        if(className == null){
            className = javaName;
        }
        try{
            EsInput esInput = new EsInputInternet();
            MyClassLoader1 classloader = new MyClassLoader1("D:\\0liuzh\\0study\\0githubs\\allproject\\0createEntity\\", "myClassloader");
            Class clazz = classloader.loadClass(className.substring(0, 1).toUpperCase() + className.substring(1));
            esInput.buildStruct(EsUtils.getClient(),clazz,indexName,type);
        }catch (Exception e){

        }
    }

    //=========

    //创建java结构对es库表
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

    //同步新房
    @Test
    public void syncNewHouse() throws Exception {


        String indexName = null;
        String indexType = null;

        Map root,es,create,ccroot,ccCity;
        root = YmlUtils.yamlTree();
        es = (Map)root.get("javaToEs");

        String javaName =  es.get("javaName").toString();
        create = (Map) es.get("syncEsNew");
        indexName = create.get("index").toString();
        indexType = create.get("type").toString();

        String className = javaName;
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


        //获取业务yml根节点
        ccroot = (Map) root.get("cc");
        List list = (List) ccroot.get("parent");
        ccCity = (Map)list.get(0);

        String httpUriOld = (String) ccroot.get("httpUriOld");
        String parentName = (String)ccCity.get("name");
        String parentNames = (String)ccCity.get("names");
        String parentCode = (String)ccCity.get("code");
        List childs = (List)ccCity.get("childs");
        Integer type = (Integer)ccCity.get("type");
        String shortName = ccCity.get("shortName").toString();
        //获取get
        String s1 = HttpRequests.sendGet(httpUriOld + "?city=" + shortName + "&proptype=11&district=allsq1&town=&sinceyear=5&flag=1&based=price&dtype=line");
        JSONArray rows = JSON.parseArray(JSON.parseObject(JSON.parseArray(s1).get(1).toString()).get("rows").toString());
        List<Object> listdata = new ArrayList<>();
        for(int i =0;i<rows.size();i++){
            String month = (String) JSON.parseObject(rows.get(i).toString()).get("month").toString().replace("-",".");
            Double d = new Double(JSON.parseObject(rows.get(i).toString()).get("data").toString());
            Object o = clazz.newInstance();
            area_name.set(o,parentNames);
            area_code.set(o,parentCode);
            date_date.set(o, month);
            date_str.set(o,month);
            price.set(o,d);
            if (EsUtils.existDoc(indexName,parentCode,month))
                continue;
            listdata.add(o);
        }
        //同步父级长春数据
        //esInput.saveDatas(EsUtils.getClient(),clazz,listdata,indexName,indexType);


        //保存市级别
        for(int j = 0;j<childs.size();j++) {
            List<Object> listdata2 = new ArrayList<>();
            Map map = (Map) childs.get(j);
            String chiNames = (String) map.get("names");
            String chiCode =  map.get("code").toString();

            s1 = HttpRequests.sendGet(httpUriOld + "?city=" + shortName + "&proptype=11&district=" + chiNames + "&town=&sinceyear=5&flag=1&based=price&dtype=line");
            JSONArray rows1 = JSON.parseArray(JSON.parseObject(JSON.parseArray(s1).get(1).toString()).get("rows").toString());

            for(int i =0;i<rows.size();i++){
                String month = (String) JSON.parseObject(rows.get(i).toString()).get("month").toString().replace("-",".");
                Double d = new Double(JSON.parseObject(rows.get(i).toString()).get("data").toString());
                Object o = clazz.newInstance();
                area_name.set(o,chiNames);
                area_code.set(o,chiCode);
                date_date.set(o, month);
                date_str.set(o,month);
                price.set(o,d);
                if (EsUtils.existDoc(indexName,chiCode,month))
                    continue;
                listdata.add(o);
            }
        }
        //保存区县级别
        esInput.saveDatas(EsUtils.getClient(),clazz,listdata,indexName,indexType);





    }
    //同步二手房
    @Test
    public void testdbesSaveData() throws Exception {
        String indexName = null;
        String indexType = null;

        Map root,es,create;
        root = YmlUtils.yamlTree();
        es = (Map)root.get("javaToEs");

        String javaName =  es.get("javaName").toString();
        create = (Map) es.get("syncEsOld");
        indexName = create.get("index").toString();
        indexType = create.get("type").toString();



        String className = javaName;
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
            if (EsUtils.existDoc(indexName,parentCode,TimeUtil.testTimeStamp(objects2.get(0).toString())))
                continue;
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
                if (EsUtils.existDoc(indexName,chiCode,TimeUtil.testTimeStamp(objects2.get(0).toString())))
                    continue;
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
    public boolean existDoc(String indexName,String area_code,String date_str) throws Exception {
        SearchHits hpold = EsUtils.matchQuery(indexName, area_code, date_str);

        System.out.println(hpold.totalHits>=1);
        if(hpold.totalHits>=1){
            return true;
        }
        return false;
    }
}
