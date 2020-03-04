package com.nssliu.dataserver.service;

import com.nssliu.dataserver.entity.Table;
import com.nssliu.dataserver.utils.datasourcepoll.MyDataSourcePool;
import com.nssliu.dataserver.utils.classloader.MyClassLoader1;

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
    static MyDataSourcePool msp = new MyDataSourcePool();
    public static void main(String[] args) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //getTableData("smdtv_1");
        //getTableData2("smdtv_1");
        Long smdtv_3 = getAllNum("smdtv_3");
        System.out.println(smdtv_3);
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
            //rs.close();
        }
        if(stmt!=null){
            //stmt.close();
        }
        if(conn!=null){
            msp.returnConn(conn);
        }
    }
    public static Long getAllNum(String tableName) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Connection conn = msp.getConnection();
        //建立操作对象
        Statement stmt= conn.createStatement();
        //结果集
        ResultSet rs = stmt.executeQuery("SELECT count(*) from "+tableName);

        Class rsc = ResultSet.class;
        Method countMet = rsc.getDeclaredMethod("getLong", int.class);
        Long allNum = 0L;
        //依次输出结果集内容
        while(rs.next()){
            Object invoke = countMet.invoke(rs,1);
            allNum = Long.valueOf(String.valueOf(invoke));
        }
        //依次关闭结果集，操作对象，数据库对象
        if(rs!=null){
            //rs.close();
        }
        if(stmt!=null){
            //stmt.close();
        }
        if(conn!=null){
            msp.returnConn(conn);
        }
        return allNum;
    }
    public static void getTableData2(String tableName) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
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
            //rs.close();
        }
        if(stmt!=null){
            //stmt.close();
        }
        if(conn!=null){
            msp.returnConn(conn);
        }
    }
    public static List getTableData(String tableName, List<Table> tables1,String fullClassName) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchFieldException {
        List<Table> tables = processMethodName(tables1);
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
            //rs.close();
        }
        if(stmt!=null){
            //stmt.close();
        }
        if(conn!=null){
            msp.returnConn(conn);
        }
        return datas;
    }
    public static List getTableDataClassLoader(String tableName, List<Table> tables1,String classFilePath) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchFieldException {
        List<Table> tables = processMethodName(tables1);
        Connection conn = msp.getConnection();
        //建立操作对象
        Statement stmt= conn.createStatement();
        //结果集
        ResultSet rs = stmt.executeQuery("select * from "+tableName);


        Class rsc = ResultSet.class;
        Class cs[] = new Class[]{int.class};
        List datas = new ArrayList();
        //Class<?> aClass1 = Class.forName(fullClassName);
        MyClassLoader1 classloader = new MyClassLoader1(classFilePath, "myClassloader");
        Class aClass1 = classloader.loadClass(tableName.substring(0, 1).toUpperCase() + tableName.substring(1));
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
                //rs.getTimestamp()

            }
            datas.add(o1);

        }
        //依次关闭结果集，操作对象，数据库对象
        if(rs!=null){
            //rs.close();
        }
        if(stmt!=null){
            //stmt.close();
        }
        if(conn!=null){
            msp.returnConn(conn);
        }
        return datas;
    }
    public static List getTableDataClassLoader(Integer pageNum,Integer currPage,String tableName, List<Table> tables1,String classFilePath) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchFieldException {
        List<Table> tables = processMethodName(tables1);
        Connection conn = msp.getConnection();
        //建立操作对象
        Statement stmt= conn.createStatement();
        //结果集
        //ResultSet rs = stmt.executeQuery("select * from "+tableName);
        ResultSet rs = stmt.executeQuery("select * from "+tableName+" LIMIT "+pageNum+" OFFSET "+currPage*pageNum);



        Class rsc = ResultSet.class;
        Class cs[] = new Class[]{int.class};
        List datas = new ArrayList();
        //Class<?> aClass1 = Class.forName(fullClassName);
        MyClassLoader1 classloader = new MyClassLoader1(classFilePath, "myClassloader");
        Class aClass1 = classloader.loadClass(tableName.substring(0, 1).toUpperCase() + tableName.substring(1));
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
                //rs.getTimestamp()

            }
            datas.add(o1);

        }
        //依次关闭结果集，操作对象，数据库对象
        if(rs!=null){
            //rs.close();
        }
        if(stmt!=null){
            //stmt.close();
        }
        if(conn!=null){
            msp.returnConn(conn);
        }
        return datas;
    }

    public void test(List<Table> tables){
        System.out.println(processMethodName(tables));
    }
    private static List<Table> processMethodName(List<Table> tables){
        List<Table> typeToMethodNames = new ArrayList<>();
        Map<String,String> columnTypeMapping = new HashMap<String,String>();
        columnTypeMapping.put("int4","getInt");
        columnTypeMapping.put("int8","getLong");
        columnTypeMapping.put("serial","getInt");
        columnTypeMapping.put("float8","getDouble");
        columnTypeMapping.put("varchar","getString");
        columnTypeMapping.put("timestamptz","getDate");
        Iterator<Table> iterator = tables.iterator();
        while (iterator.hasNext()) {
            Table table = iterator.next();
            typeToMethodNames.add(new Table(table.getColumn_name(),columnTypeMapping.get(table.getType_name()),table.getIndex()));
            //table.setType_name(columnTypeMapping.get(table.getType_name()));
        }
        //return tables;
        return typeToMethodNames;
    }
}
