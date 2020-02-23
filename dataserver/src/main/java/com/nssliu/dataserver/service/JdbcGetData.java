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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/2/23 22:20
 * @describe:
 */
public class JdbcGetData {
    public static void main(String[] args) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        getTableData("smdtv_1");
        //getTableData2("smdtv_1");
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
                            +rs.getString(2)+"\t"
                            +rs.getString(3)+"\t"
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
        Class cs[] = new Class[]{int.class};
        Method getInt = rsc.getDeclaredMethod("getInt", cs);



        //依次输出结果集内容
        while(rs.next()){
            /*System.out.println(
                    rs.getInt(1) + "\t"
                            +rs.getString(2)+"\t"
                            +rs.getString(3)+"\t"
                            +rs.getInt(4)+"\t"
                            +rs.getString(5)+"\t");*/
            Integer invoke = (Integer) getInt.invoke(rs,1);
            System.out.println(invoke);
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
    public static void getTableData(String tableName, List<Table> tables) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchFieldException {
        MyDataSourcePool msp = new MyDataSourcePool();
        Connection conn = msp.getConnection();
        //建立操作对象
        Statement stmt= conn.createStatement();
        //结果集
        ResultSet rs = stmt.executeQuery("select * from "+tableName);


        Class rsc = ResultSet.class;
        Class cs[] = new Class[]{int.class};

        Method getInt = rsc.getDeclaredMethod("getInt", cs);



        //依次输出结果集内容
        while(rs.next()){
            /*System.out.println(
                    rs.getInt(1) + "\t"
                            +rs.getString(2)+"\t"
                            +rs.getString(3)+"\t"
                            +rs.getInt(4)+"\t"
                            +rs.getString(5)+"\t");*/
            Integer smid = (Integer) getInt.invoke(rs,1);
            System.out.println(smid);
            Class<?> aClass = Class.forName("");
            Object o = aClass.newInstance();
            Field declaredField = aClass.getDeclaredField("");
            declaredField.setAccessible(true);
            declaredField.set(o,1);
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

    public void test(List<Table> tables){
        System.out.println(processMethodName(tables));
    }
    private List<Table> processMethodName(List<Table> tables){
        Map<String,String> columnTypeMapping = new HashMap<String,String>();
        columnTypeMapping.put("int4","getInt");
        columnTypeMapping.put("serial","getInt");
        columnTypeMapping.put("float8","getFloat");
        columnTypeMapping.put("varchar","getString");
        Iterator<Table> iterator = tables.iterator();
        while (iterator.hasNext()) {
            Table table = iterator.next();
            table.setType_name(columnTypeMapping.get(table.getType_name()));
        }
        return tables;
    }
}
