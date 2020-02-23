package com.nssliu.dataserver.utils;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2019/11/11 14:57
 * @describe:
 */
public class ProduceClassV2 {
    private static OutputStreamWriter write;
    private static StringBuilder stringBuilder = new StringBuilder();
    private static String className="";
    private static String filePath = "E:\\supermap\\work\\code\\gq\\java\\src\\main\\java\\com\\nss\\java1\\reflect_jdbc6\\orm\\ormv2\\";
    private static String packageName = ProduceClassV2.class.getPackage().getName();


    public static void produceClass(Map<String,Map<String,String>> clazzMap) throws SQLException, ClassNotFoundException, IOException {



        //创建包
        stringBuilder.append("package "+packageName+";\n");


        //创建类
        Map<String, String> classNameMsg = clazzMap.get("className");
        Set<String> keys = classNameMsg.keySet();
        for(String key: keys){
            className = key;
            stringBuilder.append(classNameMsg.get(key)+" class "+key+"{\n");
        }

        //创建静态代码块
        stringBuilder.append("\tstatic{\n");
        stringBuilder.append("\t\tSystem.out.println(\"nssliu\");\n");
        stringBuilder.append("\t}\n");

        String columnName;
        String columnType;
        //创建属性
        Map<String, String> propertys = clazzMap.get("propertyName");
        Set<String> propertyKeys = propertys.keySet();
        for(String key: propertyKeys){
            columnType = propertys.get(key);
            columnName = key;
            stringBuilder.append("\tprivate "+columnType+" "+columnName+";\n");
            //创建get
            stringBuilder.append("\tpublic "+columnType+" "+"get"+columnName+"(){\n");
            stringBuilder.append("\t\treturn this."+columnName+";\n");
            stringBuilder.append("\t}\n");
            //创建set
            stringBuilder.append("\tpublic void set"+columnName+"("+columnType+" "+columnName+"){\n");
            stringBuilder.append("\t\tthis."+columnName+"="+columnName+";\n");
            stringBuilder.append("\t}\n");
        }


        //类结尾符
        stringBuilder.append("}");
        //保存文件位置
        String filePath1 = filePath+className +".java";
        write = StreamUtil.getWrite(filePath1);
        write.write(stringBuilder.toString());
        StreamUtil.closeWriter(write);
        Class.forName(packageName+"."+ className);

    }
}
