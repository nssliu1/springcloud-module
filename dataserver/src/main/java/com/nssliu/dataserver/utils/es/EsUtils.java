package com.nssliu.dataserver.utils.es;

import com.nssliu.dataserver.entity.Person;
import com.nssliu.dataserver.entity.Table;
import com.nssliu.dataserver.service.JdbcGetData;
import com.nssliu.dataserver.utils.PropertiesUtils.PropertiesUtil;
import com.nssliu.dataserver.utils.classloader.MyClassLoader1;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class EsUtils {
    private static Settings setting;
    private static TransportClient client;
    private static String indexName = "hp2";
    private static String type = "cc";
    private static String createEsIndexName;
    private static String createEsType;
    private static Integer queryDBColumn = 50;
    static {
        createEsIndexName = PropertiesUtil.getProperties_1("createEsIndexName");
        createEsType = PropertiesUtil.getProperties_1("createEsType");
        queryDBColumn = Integer.valueOf(PropertiesUtil.getProperties_1("queryDBColumn"));
        String esClusterName = PropertiesUtil.getProperties_1("esClusterName");
        String esIp = PropertiesUtil.getProperties_1("esIp");
        Integer esPort = Integer.valueOf(PropertiesUtil.getProperties_1("esPort"));
        //指定ES集群
        setting = Settings.builder().put("cluster.name",
                esClusterName).build();
        //创建访问ES服务器的客户端
        try {
            client = new PreBuiltTransportClient(setting)
                   .addTransportAddress(
                           new TransportAddress(
                                   InetAddress.getByName(esIp),esPort));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    //getClient
    public static TransportClient getClient(){
        return client;
    }

 //创建结构 测试版本
//通过maps<name,type>创建结构
    public static void createStudentIndex(TransportClient client ,Map<String,String> maps,String indexName,String type) throws Exception {
        boolean existsIndex = isExistsIndex(indexName);
        if(existsIndex){
            return;
        }

        EsUtils.indexName = indexName;
        EsUtils.type = type;
        XContentBuilder mapping = entityToBuilder(maps);
        CreateIndexRequestBuilder cib = client.admin().indices().prepareCreate(indexName);
        //XContentBuilder mapping = null;
        cib.addMapping(type, mapping);
        CreateIndexResponse createIndexResponse = cib.execute().actionGet();

    }

    public static XContentBuilder entityToBuilder(Map<String,String> maps) throws Exception {


        Class<XContentBuilder> xContentBuilderClass = XContentBuilder.class;
        Object builder = XContentFactory.jsonBuilder();


        Method startObject = xContentBuilderClass.getDeclaredMethod("startObject",null);
        Method startObjectStr = xContentBuilderClass.getDeclaredMethod("startObject",String.class);
        Method endObject = xContentBuilderClass.getDeclaredMethod("endObject",null);
        Method field = xContentBuilderClass.getDeclaredMethod("field",String.class,String.class);
        builder = startObject.invoke(builder,null);//start
        builder = startObjectStr.invoke(builder,new Object[] {"properties"});
        //加入geojson
        builder = addGeoPointMapping(builder, startObjectStr, field, endObject);
        Iterator iterator = maps.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();

            builder = startObjectStr.invoke(builder,new Object[]{key});
            if(key.equals("date_date")){
                builder = field.invoke(builder,new Object[]{"type","date"});
                field.invoke(builder,new Object[]{"format","yyyy.MM"});
            }else{

                builder = field.invoke(builder,new Object[]{"type",value});
            }


            builder = endObject.invoke(builder, null);

        }

        builder = endObject.invoke(builder);
        builder = endObject.invoke(builder);


        //XContentBuilder xb = (XContentBuilder)builder;
        return (XContentBuilder) builder;
    }

    //通过java类生成对应es映射
    public static Object getJavaEsMapping(String tableName){
        EsHashMap<String,String> objectObjectEsHashMap = new EsHashMap<>();
        try{

            MyClassLoader1 classloader = new MyClassLoader1("D:\\0liuzh\\0study\\0githubs\\allproject\\0createEntity\\", "myClassloader");
            Class cls_smdtv = classloader.loadClass(tableName.substring(0, 1).toUpperCase() + tableName.substring(1));
            Field[] declaredFields = cls_smdtv.getDeclaredFields();
            for (Field f: declaredFields) {
                String name = f.getName();
                Class<?> type = f.getType();
                objectObjectEsHashMap.put(name,type.toString());
            }
        }catch (Exception e){

        }
        return objectObjectEsHashMap;
    }
    //通过java类生成对应es映射
    public static Object getJavaEsMapping(Class clazz){
        EsHashMap<String,String> objectObjectEsHashMap = new EsHashMap<>();
        try{
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field f: declaredFields) {
                String name = f.getName();
                Class<?> type = f.getType();
                objectObjectEsHashMap.put(name,type.toString());
            }
        }catch (Exception e){

        }
        return objectObjectEsHashMap;
    }
    //插入数据根据实体
    //@Test
    public static void addDoc(Class clazz,List datas,TransportClient client) throws Exception {
        Field[] declaredFields = clazz.getDeclaredFields();
        //分页查找数据库记录


        Class<XContentBuilder> xContentBuilderClass = XContentBuilder.class;
        Method startObject = xContentBuilderClass.getDeclaredMethod("startObject",null);
        Method startObjectStr = xContentBuilderClass.getDeclaredMethod("startObject",String.class);
        Method endObject = xContentBuilderClass.getDeclaredMethod("endObject",null);
        Method field = xContentBuilderClass.getDeclaredMethod("field",String.class,String.class);
        Method doubleField = xContentBuilderClass.getDeclaredMethod("field", String.class, double.class);
        Method latlonField = xContentBuilderClass.getDeclaredMethod("latlon", String.class, double.class, double.class);




        for(Object o : datas){
            Class<?> aClass = o.getClass();
            try{
                Object builder = XContentFactory.jsonBuilder();

                builder = startObject.invoke(builder, null);

                for (Field field1: declaredFields){
                    //field1.setAccessible(true);
                    String name = field1.getName();
                    Field declaredField = aClass.getDeclaredField(name);
                    declaredField.setAccessible(true);

                    Object o1 = declaredField.get(o);
                    if(o1!=null){
                        builder = field.invoke(builder, new Object[]{name, o1.toString()});
                    }else {
                        builder = field.invoke(builder, new Object[]{name, null});
                    }
                }
                builder = endObject.invoke(builder, null);
                addEss(builder);
            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }

    public static void addEss(Object builder){
        XContentBuilder doc = (XContentBuilder) builder;

        IndexResponse response = client.prepareIndex(indexName,type,null)//id为null，由ES自己生成
                .setSource(doc).get();
        System.out.println(response.status());//打印添加是否成功
    }

    //判断索引是否存在
    public static boolean isExistsIndex(String indexName){
        IndicesExistsResponse response =
                getClient().admin().indices().exists(
                        new IndicesExistsRequest().indices(new String[]{indexName})).actionGet();
        return response.isExists();
    }
    //添加geo_point结构
    private static Object addGeoPointMapping(Object builder,Method startObjectStr,Method field,Method endObject){


        try {
            builder = startObjectStr.invoke(builder,new Object[]{"location"});
            builder = field.invoke(builder,new Object[]{"type","geo_point"});
            //builder = field.invoke(builder,new Object[]{"geohash_prefix",true});
            //builder = field.invoke(builder,new Object[]{"geohash_precision","1km"});
            builder = endObject.invoke(builder);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return builder;
    }

    private static Object addGeoPointDataVTwo(Method doubleField,Object builder,Class cls_smdtv,Object cls_smdtv_data){
        cls_smdtv = cls_smdtv_data.getClass();
        try {
            //获取smx
            Field smx = cls_smdtv.getDeclaredField("smx");//lat
            smx.setAccessible(true);
            Object smx_data = smx.get(cls_smdtv_data);
            //获取smy
            Field smy = cls_smdtv.getDeclaredField("smy");//lon
            smy.setAccessible(true);
            Object smy_data = smy.get(cls_smdtv_data);
            //组装geo_point
            //builder = field.invoke(builder, new Object[]{"location", (Double)smy_data, (Double)smx_data});
            builder = doubleField.invoke(builder, new Object[]{"lat", smy_data});
            builder = doubleField.invoke(builder, new Object[]{"lon", smx_data});
        }catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return builder;
    }


}