package com.nssliu.dataserver.utils.es;

import com.nssliu.dataserver.entity.Person;
import com.nssliu.dataserver.utils.classloader.MyClassLoader1;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
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
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class EsUtil {
    private static Settings setting;
    private static TransportClient client;
    private static String indexName = "test4";
    private static String type = "nssliuType";
    static {
        //指定ES集群
        setting = Settings.builder().put("cluster.name",
                "my-application").build();
        //创建访问ES服务器的客户端
        try {
            client = new PreBuiltTransportClient(setting)
                   .addTransportAddress(
                           new TransportAddress(
                                   InetAddress.getByName("127.0.0.1"),9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    //getClient
    public static TransportClient getClient(){
        return client;
    }
    //查询所有
    @Test
    public static void testAll(List<String> names) throws IOException, InterruptedException, ExecutionException {
        SearchResponse searchResponse = client.prepareSearch(indexName)
                .setTypes(type).setQuery(QueryBuilders.matchAllQuery())
                .setSize(10000)
                .get();
        SearchHits hits = searchResponse.getHits(); // 获取命中次数，查询结果有多少对象
        System.out.println("查询结果有：" + hits.getTotalHits() + "条");
        Iterator<SearchHit> iterator = hits.iterator();
        int i = 0;
        while (iterator.hasNext()) {


            SearchHit next = iterator.next();
            System.out.println(next.getId());
            Person perso = new Person(next.getId(),names.get(i));
            //Person perso = new Person(next.getId(),"nss");
            test4(perso);
            i++;
        }
    }
    //从ES中查询数据
    @Test
    public void test1() throws UnknownHostException {
        //指定ES集群
        Settings setting = Settings.builder().put("cluster.name",
                "elasticsearch").build();

        //创建访问ES服务器的客户端
        TransportClient client = new PreBuiltTransportClient(setting)
                .addTransportAddress(
                        new TransportAddress(
                                InetAddress.getByName("172.19.220.17"),9300));
        //get方式数据查询 ,参数为Index,type和id
        GetResponse response = client.prepareGet("person2","supermap","AW4hT5aVPh6Mw1aIdE1l").get();

        System.out.println(response.getSourceAsString());
        client.close();
    }
    //插入数据
    //@Test
    public static void test2(Person lpe) throws IOException {
        XContentBuilder doc = XContentFactory.jsonBuilder()
                .startObject()
                .field("XM","平顶山法人4")
                //.field("number","0000004")
                .endObject();

        //添加一个doc
        IndexResponse response = client.prepareIndex(indexName,type,null)//id为null，由ES自己生成
                .setSource(doc).get();
        System.out.println(response.status());//打印添加是否成功

    }
    //关闭连接
    public static void closeClient(){
        client.close();
    }

    //删除文档
    @Test
    public void test3() throws IOException {


        DeleteResponse response = client.prepareDelete(indexName,type,"2db65085-c3e2-4f4d-a729-bcb263e42119")
                .get();
        System.out.println(response.status());//打印添加是否成功
        client.close();
    }

    //更新文档
    @Test
    public static void test4(Person person) throws IOException, ExecutionException, InterruptedException {
        UpdateRequest request = new UpdateRequest();
        request.index(indexName)
                .type(type)
                .id(person.getId())
                .doc(
                        XContentFactory.jsonBuilder().startObject()
                                .field("XM",person.getName())
                                //.field("newadd","新增字段")
                                .endObject()
                );
        UpdateResponse response = client.update(request).get();

        System.out.println(person.getName()+":"+response.status());//打印是否成功
        //client.close();
    }

    @Test
    public void testa(){
        System.out.println("nss");
    }
    //创建结构 测试版本
    @Test
    public static void createStudentIndex(String indexName) {
        EsUtil.indexName = indexName;
        CreateIndexRequestBuilder cib = client.admin().indices().prepareCreate(indexName);
        XContentBuilder mapping = null;
        try {
            mapping = XContentFactory.jsonBuilder()
                    .startObject()//表示开始设置值
                    .startObject("properties")//设置只定义字段，不传参
                    .startObject("no") //定义字段名
                    .field("type", "text") //设置数据类型
                    .endObject()
                    .startObject("name")
                    .field("type", "text")
                    .endObject()
                    .startObject("addreess")
                    .field("type", "text")
                    .endObject()
                    .startObject("age")
                    .field("type", "integer")
                    .endObject()
                    .startObject("phone")
                    .field("type", "text")
                    .endObject()
                    .startObject("score")
                    .field("type", "integer")
                    .endObject()
                    .endObject()
                    .endObject();

        } catch (IOException e) {
            e.printStackTrace();
        }
        cib.addMapping("student", mapping);
        cib.execute().actionGet();

    }
    //通过maps<name,type>创建结构
    public static void createStudentIndex(Map<String,String> maps,String indexName,String type) throws Exception {
        boolean existsIndex = isExistsIndex(indexName);
        if(existsIndex){
            return;
        }

        EsUtil.indexName = indexName;
        EsUtil.type = type;
        XContentBuilder mapping = entityToBuilder(maps);
        CreateIndexRequestBuilder cib = client.admin().indices().prepareCreate(indexName);
        //XContentBuilder mapping = null;
        cib.addMapping(type, mapping);
        cib.execute().actionGet();

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


        Iterator iterator = maps.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();

            builder = startObjectStr.invoke(builder,new Object[]{key});
            builder = field.invoke(builder,new Object[]{"type",value});
            builder = endObject.invoke(builder, null);

        }


        builder = endObject.invoke(builder);
        builder = endObject.invoke(builder);


        XContentBuilder xb = (XContentBuilder)builder;
        return (XContentBuilder) builder;
    }

    //插入数据根据实体
    //@Test
    public static void addDoc(List list,String classFilePath) throws Exception {
        MyClassLoader1 classloader = new MyClassLoader1("D:\\0liuzh\\0study\\0githubs\\allproject\\0createEntity\\", "myClassloader");
        Class cls_smdtv = classloader.loadClass("Smdtv_1");
        Field[] declaredFields = cls_smdtv.getDeclaredFields();
        EsHashMap<String,String> objectObjectEsHashMap = new EsHashMap<>();
        for (Field f: declaredFields) {
            String name = f.getName();
            Class<?> type = f.getType();
            System.out.println(type.toString());
            objectObjectEsHashMap.put(name,type.toString());
        }
        //创建表结构
        EsUtil.indexName = classFilePath;
        EsUtil.type = "66";
        createStudentIndex(objectObjectEsHashMap,indexName,type);
        for(int i =0;i<list.size();i++){
            Object o = list.get(i);
            Class<?> aClass = o.getClass();
            try{
                Class<XContentBuilder> xContentBuilderClass = XContentBuilder.class;
                Object builder = XContentFactory.jsonBuilder();

                Method startObject = xContentBuilderClass.getDeclaredMethod("startObject",null);
                Method endObject = xContentBuilderClass.getDeclaredMethod("endObject",null);
                Method field = xContentBuilderClass.getDeclaredMethod("field",String.class,String.class);

                builder = startObject.invoke(builder, null);
                for (Field field1: declaredFields){
                    //field1.setAccessible(true);
                    String name = field1.getName();
                    Field declaredField = aClass.getDeclaredField(name);
                    declaredField.setAccessible(true);

                    Object o1 = declaredField.get(o);
                    System.out.print(" "+o1);
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
        //添加一个doc
        IndexResponse response = client.prepareIndex(indexName,type,null)//id为null，由ES自己生成
                .setSource(doc).get();
        System.out.println(response.status());//打印添加是否成功
    }
    @Test
    public void testsss(){
        boolean test66 = isExistsIndex("test66");
        System.out.println(test66);
    }
    //判断索引是否存在
    public static boolean isExistsIndex(String indexName){
        IndicesExistsResponse response =
                getClient().admin().indices().exists(
                        new IndicesExistsRequest().indices(new String[]{indexName})).actionGet();
        return response.isExists();
    }
}