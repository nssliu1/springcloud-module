package com.nssliu.dataserver.trueversion.jsonDispose;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.nssliu.dataserver.entity.Fj;
import com.nssliu.dataserver.service.esinterface.EsInput;
import com.nssliu.dataserver.service.esinterface.EsInputInternet;
import com.nssliu.dataserver.trueversion.entity.CallBackEntity;
import com.nssliu.dataserver.utils.HttpRequests;
import com.nssliu.dataserver.utils.classloader.MyClassLoader1;
import com.nssliu.dataserver.utils.es.EsUtils;
import com.nssliu.dataserver.utils.time.TimeUtil;
import com.nssliu.dataserver.utils.yml.YmlUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/17 10:53
 * @describe:
 */
public class GetListForHttpHPNews implements  GetListForHttp{
    static String indexName = "new";
    static String indexType = "cc";
    static Class clazz = Fj.class;
    @Override
    public CallBackEntity getCreatinformation() {
        return new CallBackEntity(indexName,indexType,"",clazz,"",null);
    }

    @Override
    public CallBackEntity getList() throws IllegalAccessException, InstantiationException {

        CallBackEntity callBackEntity = new CallBackEntity();
        try {

            Map root,es,create,ccroot,ccCity;
            root = YmlUtils.yamlTree();
            es = (Map)root.get("javaToEs");

            String javaName =  es.get("javaName").toString();
            create = (Map) es.get("syncEsNew");
            /*indexName = create.get("index").toString();
            indexType = create.get("type").toString();*/

            callBackEntity.setIndexName(indexName);
            callBackEntity.setType(indexType);
            callBackEntity.setClazz(Fj.class);



            String className = javaName;
            EsInput esInput = new EsInputInternet();
            MyClassLoader1 classloader = new MyClassLoader1("D:\\0liuzh\\0study\\0githubs\\allproject\\0createEntity\\", "myClassloader");
            //Class clazz = classloader.loadClass(className.substring(0, 1).toUpperCase() + className.substring(1));

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


            //保存市级别
            for(int j = 0;j<childs.size();j++) {
                List<Object> listdata2 = new ArrayList<>();
                Map map = (Map) childs.get(j);
                String chiNames = (String) map.get("names");
                System.out.println(chiNames);
                System.out.println(chiNames);
                System.out.println(chiNames);
                System.out.println(chiNames);
                String chiCode =  map.get("code").toString();

                s1 = HttpRequests.sendGet(httpUriOld + "?city=" + shortName + "&proptype=11&district=" + chiNames + "&town=&sinceyear=5&flag=1&based=price&dtype=line");
                JSONArray rows1 = JSON.parseArray(JSON.parseObject(JSON.parseArray(s1).get(1).toString()).get("rows").toString());

                for(int i =0;i<rows1.size();i++){
                    String month = (String) JSON.parseObject(rows1.get(i).toString()).get("month").toString().replace("-",".");
                    Double d;
                    if(JSON.parseObject(rows1.get(i).toString()).get("data")==null){

                        //d = new Double(JSON.parseObject(rows1.get(i).toString()).get("value").toString());
                        d = new Double(0);
                    }else {

                        d = new Double(JSON.parseObject(rows1.get(i).toString()).get("data").toString());
                    }
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

            callBackEntity.setList(listdata);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return callBackEntity;
    }
}
