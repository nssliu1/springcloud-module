package com.nssliu.dataserver.utils.es;

import com.nssliu.dataserver.utils.classloader.MyClassLoader1;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/2/26 15:48
 * @describe:
 */
public class EsUtil2 {
    /*
    public static void addDoc(List list, String classFilePath) throws IOException, NoSuchMethodException, ClassNotFoundException {
        Class<XContentBuilder> xContentBuilderClass = XContentBuilder.class;
        Object builder = XContentFactory.jsonBuilder();

        Method startObject = xContentBuilderClass.getDeclaredMethod("startObject",null);
        Method startObjectStr = xContentBuilderClass.getDeclaredMethod("startObject",String.class);
        Method endObject = xContentBuilderClass.getDeclaredMethod("endObject",null);
        Method field = xContentBuilderClass.getDeclaredMethod("field",String.class,String.class);

        MyClassLoader1 classloader = new MyClassLoader1("D:\\0liuzh\\0study\\0githubs\\allproject\\0createEntity\\", "myClassloader");
        Class cls_smdtv = classloader.loadClass("Smdtv_1");

        list.forEach((Object o) ->{
            try{
                Field[] declaredFields = cls_smdtv.getDeclaredFields();
                for (Field field1: declaredFields){
                    field1.setAccessible(true);
                    Object o1 = field1.get(o);
                    System.out.println(o1);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });



        XContentBuilder doc = XContentFactory.jsonBuilder()
                .startObject()
                .field("XM","平顶山法人4")
                //.field("number","0000004")
                .endObject();

        //添加一个doc
        IndexResponse response = client.prepareIndex(indexName,type,null)//id为null，由ES自己生成
                .setSource(doc).get();
        System.out.println(response.status());//打印添加是否成功

    }*/
}
