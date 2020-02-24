package com.nssliu.dataserver.service;

import com.nssliu.dataserver.entity.Table;
import com.nssliu.dataserver.utils.MyDataSourcePool;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/2/23 22:20
 * @describe:
 */
public class JdbcGetData {
    public static void main(String[] args) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //getTableData("smdtv_1");
        getTableData2("smdtv_1");
    }

    public static void getTableData(String tableName) throws SQLException {
        MyDataSourcePool msp = new MyDataSourcePool();
        Connection conn = msp.getConnection();
        //建立操作对象
        Statement stmt= conn.createStatement();
        //结果集
        ResultSet rs = stmt.executeQuery("select * from "+tableName);
        //依次输出结果集内容
        while(rs.next()){
            System.out.println(
                    rs.getInt(1) + "\t"
                            +rs.getDouble(2)+"\t"
                            +rs.getFloat(3)+"\t"
                            +rs.getInt(4)+"\t"
                            +rs.getString(5)+"\t");

        }
        //依次关闭结果集，操作对象，数据库对象
        if(rs!=null){
            rs.close();
        }
        if(stmt!=null){
            stmt.close();
        }
        if(conn!=null){
            conn.close();
        }
    }
    public static void getTableData2(String tableName) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MyDataSourcePool msp = new MyDataSourcePool();
        Connection conn = msp.getConnection();
        //建立操作对象
        Statement stmt= conn.createStatement();
        //结果集
        ResultSet rs = stmt.executeQuery("select * from "+tableName);


        Class rsc = ResultSet.class;
        Method getSmx = rsc.getDeclaredMethod("getDouble", int.class);
        Method getSmy = rsc.getDeclaredMethod("getDouble", int.class);



        //依次输出结果集内容
        while(rs.next()){
            /*System.out.println(
                    rs.getInt(1) + "\t"
                            +rs.getString(2)+"\t"
                            +rs.getString(3)+"\t"
                            +rs.getInt(4)+"\t"
                            +rs.getString(5)+"\t");*/
            Object invoke = getSmx.invoke(rs,2);
            Object invoke1 = getSmy.invoke(rs, 3);
            System.out.println(invoke);
            System.out.println(invoke1);
        }
        //依次关闭结果集，操作对象，数据库对象
        if(rs!=null){
            rs.close();
        }
        if(stmt!=null){
            stmt.close();
        }
        if(conn!=null){
            conn.close();
        }
    }
    public static List getTableData(String tableName, List<Table> tables1,String fullClassName) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchFieldException {
        List<Table> tables = processMethodName(tables1);
        MyDataSourcePool msp = new MyDataSourcePool();
        Connection conn = msp.getConnection();
        //建立操作对象
        Statement stmt= conn.createStatement();
        //结果集
        ResultSet rs = stmt.executeQuery("select * from "+tableName);


        Class rsc = ResultSet.class;
        Class cs[] = new Class[]{int.class};
        List datas = new ArrayList();
        Class<?> aClass1 = Class.forName(fullClassName);
        //依次输出结果集内容
        while(rs.next()){
            Iterator<Table> iterator = tables.iterator();
            Object o1 = aClass1.newInstance();
            while (iterator.hasNext()) {
                Table table = iterator.next();
                Method method = rsc.getDeclaredMethod(table.getType_name(), int.class);
                Object invoke = method.invoke(rs, table.getIndex());//获取本字段返回值
                Field declaredField = aClass1.getDeclaredField(table.getColumn_name());
                declaredField.setAccessible(true);
                declaredField.set(o1,invoke);

            }
            datas.add(o1);

        }
        //依次关闭结果集，操作对象，数据库对象
        if(rs!=null){
            rs.close();
        }
        if(stmt!=null){
            stmt.close();
        }
        if(conn!=null){
            conn.close();
        }
        return datas;
    }

    public void test(List<Table> tables){
        System.out.println(processMethodName(tables));
    }
    private static List<Table> processMethodName(List<Table> tables){
        Map<String,String> columnTypeMapping = new HashMap<String,String>();
        columnTypeMapping.put("int4","getInt");
        columnTypeMapping.put("int8","getLong");
        columnTypeMapping.put("serial","getInt");
        columnTypeMapping.put("float8","getDouble");
        columnTypeMapping.put("varchar","getString");
        Iterator<Table> iterator = tables.iterator();
        while (iterator.hasNext()) {
            Table table = iterator.next();
            table.setType_name(columnTypeMapping.get(table.getType_name()));
        }
        return tables;
    }
}
