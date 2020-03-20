package com.nssliu.dataserver.trueversion;

import com.nssliu.dataserver.entity.*;
import com.nssliu.dataserver.trueversion.annotations.Group;
import com.nssliu.dataserver.trueversion.annotations.TableFieldDetails;
import com.nssliu.dataserver.trueversion.jsonDispose.GetListForHttp;
import com.nssliu.dataserver.trueversion.entity.CallBackEntity;
import com.nssliu.dataserver.trueversion.entity.JavaEsType;
import com.nssliu.dataserver.utils.PropertiesUtils.PropertiesUtil;
import com.nssliu.dataserver.utils.es.EsUtil;
import com.nssliu.dataserver.utils.time.TimeUtil;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/16 13:41
 * @describe: 导出java类，重新定义java类，加入各种标注，编译，
 * http抓取，将数据和配置数据一起解析存入list,
 * 将互联网访问接口解析的fastjaon作为一个用户可更改的java类
 */
public class NewJavaToEsPoint {
    private static GetListForHttp getListForHttp;

    static {
        try {
            try {
                getListForHttp = (GetListForHttp)Class.forName(PropertiesUtil.getProperties_1("eshttpImplement")).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void testCreateIndex(String classFullName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        //GetListForHttp getListForHttp = new GetListForHttpAqi();
        GetListForHttp getListForHttp = (GetListForHttp)Class.forName(classFullName).newInstance();
        CallBackEntity creatinformation = getListForHttp.getCreatinformation();
        if(creatinformation==null){
            System.out.println("CallBackEntity不可为空");
            return;
        }

        createIndex(creatinformation.getIndexName(),creatinformation.getType(),creatinformation.getClazz());

    }

    public static void testSyncData(String classFullName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        //GetListForHttp getListForHttp = new GetListForHttpAqi();
        GetListForHttp getListForHttp = (GetListForHttp)Class.forName(classFullName).newInstance();
        CallBackEntity callBackEntity = getListForHttp.getList();
        if(callBackEntity==null){
            System.out.println("CallBackEntity不可为空");
            return;
        }
        syncData(callBackEntity.getIndexName(),callBackEntity.getType(),callBackEntity.getClazz(),callBackEntity.getList());

    }
    @Test
    public void testCreateIndex() throws InstantiationException, IllegalAccessException {

        //GetListForHttp getListForHttp = new GetListForHttpAqi();
        CallBackEntity creatinformation = getListForHttp.getCreatinformation();
        if(creatinformation==null){
            System.out.println("CallBackEntity不可为空");
            return;
        }
        createIndex(creatinformation.getIndexName(),creatinformation.getType(),creatinformation.getClazz());

    }
    @Test
    public void testSyncData() throws InstantiationException, IllegalAccessException {
        //GetListForHttp getListForHttp = new GetListForHttpAqi();
        CallBackEntity callBackEntity = getListForHttp.getList();
        if(callBackEntity==null){
            System.out.println("CallBackEntity不可为空");
            return;
        }
        syncData(callBackEntity.getIndexName(),callBackEntity.getType(),callBackEntity.getClazz(),callBackEntity.getList());

    }


    public static void createIndex(String indexName,String type,Class clazz) throws IllegalAccessException, InstantiationException {
        JavaEsType classify = classify(clazz);
        reduceMapping(classify.getGroupMap(),classify.getFieldsList(),indexName,type);
    }

    public static void syncData(String indexName,String type,Class clazz,List list) throws InstantiationException, IllegalAccessException {
        JavaEsType classify = classify(clazz);
        //处理需要存储的数据
        List needSave = getNeedSave(indexName, clazz, list);
        saveData(classify.getGroupMap(),classify.getFieldsList(),needSave,indexName,type);
    }

    public static List getNeedSave(String indexName,Class clazz,List list){
        List needSaveList = new ArrayList();
        try {
            for (Object obj: list){
                boolean exit = EsUtil.matchQuery(indexName, clazz, obj);//false是不判断或不存在，true是已经存在
                if(!exit)
                    needSaveList.add(obj);
            }
        }catch (Exception e){

        }
        return needSaveList;

    }



    public static JavaEsType classify(Class clazz) throws InstantiationException, IllegalAccessException {

        Field[] fields = clazz.getDeclaredFields();

        Map<String,List<Field>> groupMap = new HashMap<>();
        List<Field> fieldsList = new ArrayList<Field>();

        for (Field field: fields){
            field.setAccessible(true);
            TableFieldDetails saveField = field.getDeclaredAnnotation(TableFieldDetails.class);
            //System.out.println(field.getName());
            if(saveField!=null){
                Group someGroup = field.getAnnotation(Group.class);
                if(someGroup!=null){
                    String groupName = someGroup.groupName();
                    List<Field> someGrouplist = groupMap.get(groupName);
                    if(someGrouplist==null){
                        someGrouplist = new ArrayList<>();
                        someGrouplist.add(field);
                        groupMap.put(groupName,someGrouplist);
                    }else {
                        someGrouplist.add(field);
                    }
                }else {
                    //不是分组
                    fieldsList.add(field);
                }
                field.setAccessible(true);
            }
        }
       return new JavaEsType(groupMap,fieldsList);

    }
    public static List getList(){
        EsData esData = new EsData();
        esData.setName("nss");
        esData.setDates(TimeUtil.testTimeStamp(String.valueOf(new Date().getTime())));
        esData.setSmx(1.11);
        esData.setSmy(1.11);
        esData.setSmx1(1.112);
        esData.setSmy1(1.112);
        esData.setSmyyy(1.111);
        EsData esData1 = new EsData();
        esData1.setName("nss");
        esData1.setDates(TimeUtil.testTimeStamp(String.valueOf(new Date().getTime())));

        esData1.setSmx(1.11);
        esData1.setSmy(1.11);
        esData1.setSmx1(1.112);
        esData1.setSmy1(1.112);
        esData1.setSmyyy(1.2222);
        List list = new ArrayList();
        list.add(esData);
        list.add(esData1);
        return list;
    }
    public static void saveData(Map<String,List<Field>> groupMap,List<Field> fieldsList,List list,String indexName,String type){
        try {
            Class<XContentBuilder> xContentBuilderClass = XContentBuilder.class;
            Method startObject = xContentBuilderClass.getDeclaredMethod("startObject",null);
            Method startObjectStr = xContentBuilderClass.getDeclaredMethod("startObject",String.class);
            Method endObject = xContentBuilderClass.getDeclaredMethod("endObject",null);
            Method field = xContentBuilderClass.getDeclaredMethod("field",String.class,String.class);
            Method doubleField = xContentBuilderClass.getDeclaredMethod("field", Class.forName("java.lang.String"), double.class);




                for(int i =0;i<list.size();i++){
                    Object builder = XContentFactory.jsonBuilder();
                    //开始
                    builder = startObject.invoke(builder, null);

                    //添加组数据
                    builder = addGroupData(groupMap, list.get(i), doubleField, startObjectStr, endObject,builder);
                    //添加单个数据
                    builder = addFieldData(fieldsList, list.get(i), field, builder);


                    builder = endObject.invoke(builder, null);
                    XContentBuilder xb = (XContentBuilder) builder;
                    System.out.println(xb.string());
                    //添加数据


                    EsUtil.addEss(xb,indexName,type);


                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Object addGroupData(Map<String,List<Field>> groupMap,Object obj,Method doubleField,Method startObjectStr,Method endObject,Object builder){
        try {
            Iterator<Map.Entry<String, List<Field>>> iterator = groupMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String groupName = (String) entry.getKey();
                List<Field> groupFields = (List<Field>) entry.getValue();
                Group annotation = groupFields.get(0).getAnnotation(Group.class);
                builder = startObjectStr.invoke(builder, new Object[]{annotation.groupName()});
                for (Field field: groupFields){
                    field.setAccessible(true);
                    TableFieldDetails annotation1 = field.getAnnotation(TableFieldDetails.class);
                    String typeName = field.getGenericType().getTypeName();
                    Method method = getMethod(typeName);
                    method.invoke(builder,new Object[]{annotation1.esName(),field.get(obj)});
                }

                builder = endObject.invoke(builder, null);
            }



        }catch (Exception e){

        }
        return builder;
    }
    public static Object addFieldData(List<Field> fieldsList,Object obj,Method field,Object builder){
        try {

            for (Field field1: fieldsList){
                TableFieldDetails annotation = field1.getAnnotation(TableFieldDetails.class);
                String typeName = field1.getGenericType().getTypeName();
                Method method = getMethod(typeName);
                Object o = field1.get(obj);
                if(o!=null){
                    builder = method.invoke(builder, new Object[]{annotation.esName(), o});
                }else {
                    builder = method.invoke(builder, new Object[]{annotation.esName(), null});

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return builder;
    }

    public static void reduceMapping(Map<String,List<Field>> groupMap,List<Field> fieldsList,String indexName,String type){

        createIndex(groupMap,fieldsList,indexName,type);
    }

    public static void createIndex(Map<String,List<Field>> groupMap,List<Field> fieldsList,String indexName,String type){
        System.out.println(groupMap);
        System.out.println(fieldsList);
        try {

            Class<XContentBuilder> xContentBuilderClass = XContentBuilder.class;
            Object builder = XContentFactory.jsonBuilder();


            //Method startObjectStr = xContentBuilderClass.getDeclaredMethod("startObject", String.class);
            Method startObject = xContentBuilderClass.getDeclaredMethod("startObject",null);
            Method startObjectStr = xContentBuilderClass.getDeclaredMethod("startObject",String.class);
            Method endObject = xContentBuilderClass.getDeclaredMethod("endObject",null);
            Method field = xContentBuilderClass.getDeclaredMethod("field",String.class,String.class);
            builder = startObject.invoke(builder,null);//start
            builder = startObjectStr.invoke(builder,new Object[] {"properties"});
            //加入geojson
            //builder = addGeoPointMapping(builder, startObjectStr, field, endObject);
            //创建组
            builder = (XContentBuilder)addGroup(groupMap, builder, startObjectStr, field, endObject);
            //创建单个
            builder = (XContentBuilder)addField(fieldsList, builder, startObjectStr, field, endObject);


            builder = endObject.invoke(builder);
            builder = endObject.invoke(builder);


            XContentBuilder mapping = (XContentBuilder)builder;
            boolean existsIndex = EsUtil.isExistsIndex(indexName);
            if(existsIndex){
                return;
            }
            //EsUtil.addEss(builder);
            EsUtil.createIndex(mapping,indexName,type);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static Object addGroup(Map<String,List<Field>> groupMap,Object builder,Method startObjectStr,Method field,Method endObject){
        Iterator<Map.Entry<String, List<Field>>> iterator = groupMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            String groupName = (String)entry.getKey();
            List<Field> groupFields = (List<Field>) entry.getValue();
            Group annotation = groupFields.get(0).getAnnotation(Group.class);
            TableFieldDetails annotation1 = groupFields.get(0).getAnnotation(TableFieldDetails.class);


            try {
                builder = startObjectStr.invoke(builder,new Object[]{groupName});
                builder = field.invoke(builder,new Object[]{"type",annotation.groupType()});
                if(!"".equals(annotation1.param())){
                    field.invoke(builder,new Object[]{annotation1.param(),annotation1.paramDetail()});
                }
                //builder = field.invoke(builder,new Object[]{"geohash_prefix",true});
                //builder = field.invoke(builder,new Object[]{"geohash_precision","1km"});
                builder = endObject.invoke(builder);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


        }
        return builder;
    }
    public static Object addField(List<Field> fields,Object builder,Method startObjectStr,Method field,Method endObject){
        try{

            for (Field esfield:fields){
                TableFieldDetails annotation = esfield.getAnnotation(TableFieldDetails.class);

                builder = startObjectStr.invoke(builder,new Object[]{annotation.esName()});
                builder = field.invoke(builder,new Object[]{"type",annotation.esType()});
                if(!"".equals(annotation.param())){
                    field.invoke(builder,new Object[]{annotation.param(),annotation.paramDetail()});
                }
                builder = endObject.invoke(builder, null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return builder;
    }

    public static Method getMethod(String type){
        try{
            Class<XContentBuilder> xContentBuilderClass = XContentBuilder.class;

            if(type==null){
                throw new RuntimeException("空数据类型");
            }
            if("double".equals(type) || "java.lang.Double".equals(type)){
                return xContentBuilderClass.getDeclaredMethod("field", Class.forName("java.lang.String"), double.class);

            }
            if("float".equals(type) || "java.lang.Float".equals(type)){
                return xContentBuilderClass.getDeclaredMethod("field", Class.forName("java.lang.String"), float.class);

            }
            if("int".equals(type) || "java.lang.Integer".equals(type)){
                return xContentBuilderClass.getDeclaredMethod("field", Class.forName("java.lang.String"), int.class);

            }
            if("byte".equals(type) || "java.lang.Byte".equals(type)){
                return xContentBuilderClass.getDeclaredMethod("field", Class.forName("java.lang.String"), byte.class);

            }
            if("short".equals(type) || "java.lang.Short".equals(type)){
                return xContentBuilderClass.getDeclaredMethod("field", Class.forName("java.lang.String"), short.class);

            }
            if("long".equals(type) || "java.lang.Long".equals(type)){
                return xContentBuilderClass.getDeclaredMethod("field", Class.forName("java.lang.String"), long.class);

            }
            if("boolean".equals(type) || "java.lang.Boolean".equals(type)){
                return xContentBuilderClass.getDeclaredMethod("field", Class.forName("java.lang.String"), boolean.class);

            }
            if("char".equals(type) || "java.lang.Character".equals(type)){
                return xContentBuilderClass.getDeclaredMethod("field", Class.forName("java.lang.String"), char.class);

            }
            if("java.lang.String".equals(type)){
                return xContentBuilderClass.getDeclaredMethod("field",String.class,String.class);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
