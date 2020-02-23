package com.nssliu.dataserver.service;

import com.nssliu.dataserver.entity.Table;
import com.nssliu.dataserver.utils.MyDataSourcePool;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2019/11/11 14:57
 * @describe:
 */
public class JdbcGetTable {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Map<String, String> smdtv_1 = getTableDetail("smdtv_1");
        System.out.println(smdtv_1);
        List<Table> smdtv_11 = getTableDetailList("smdtv_1");
        System.out.println(smdtv_11);
        new JdbcGetData().test(smdtv_11);
    }
    public static Map<String,String> getTableDetail(String m_TableName) throws SQLException, ClassNotFoundException {
        MyDataSourcePool msp = new MyDataSourcePool();
        Connection conn = msp.getConnection();
        //String m_TableName = "smdtv_1";
        DatabaseMetaData m_DBMetaData = conn.getMetaData();
        ResultSet tableRet = m_DBMetaData.getTables(null, "%", m_TableName, new String[]{"TABLE"});
/*其中"%"就是表示*的意思，也就是任意所有的意思。其中m_TableName就是要获取的数据表的名字，如果想获取所有的表的名字，就可以使用"%"来作为参数了。*/

//3. 提取表的名字。
        while (tableRet.next()) {
            System.out.println(tableRet.getString("TABLE_NAME"));
        }

/*通过getString("TABLE_NAME")，就可以获取表的名字了。
从这里可以看出，前面通过getTables的接口的返回，JDBC是将其所有的结果，保存在一个类似table的内存结构中，而其中TABLE_NAME这个名字的字段就是每个表的名字。*/

        //4. 提取表内的字段的名字和类型
        String columnName;
        String columnType;
        ResultSet colRet = m_DBMetaData.getColumns(null, "%", m_TableName, "%");
        Map tableColumnAndType = new HashMap<String,String>();
        List list = new ArrayList<Table>();
        while (colRet.next()) {
            columnName = colRet.getString("COLUMN_NAME");
            columnType = colRet.getString("TYPE_NAME");
            /*int datasize = colRet.getInt("COLUMN_SIZE");
            int digits = colRet.getInt("DECIMAL_DIGITS");
            int nullable = colRet.getInt("NULLABLE");*/
            //System.out.println(columnName+" "+columnType+" "+datasize+" "+digits+" "+ nullable);
            System.out.println(columnName + " " + columnType);
            tableColumnAndType.put(columnName,columnType);

        }
        return tableColumnAndType;
    }

    public static List<Table> getTableDetailList(String m_TableName) throws SQLException, ClassNotFoundException {
        MyDataSourcePool msp = new MyDataSourcePool();
        Connection conn = msp.getConnection();
        //String m_TableName = "smdtv_1";
        DatabaseMetaData m_DBMetaData = conn.getMetaData();
        ResultSet tableRet = m_DBMetaData.getTables(null, "%", m_TableName, new String[]{"TABLE"});
/*其中"%"就是表示*的意思，也就是任意所有的意思。其中m_TableName就是要获取的数据表的名字，如果想获取所有的表的名字，就可以使用"%"来作为参数了。*/

//3. 提取表的名字。
        while (tableRet.next()) {
            System.out.println(tableRet.getString("TABLE_NAME"));
        }

/*通过getString("TABLE_NAME")，就可以获取表的名字了。
从这里可以看出，前面通过getTables的接口的返回，JDBC是将其所有的结果，保存在一个类似table的内存结构中，而其中TABLE_NAME这个名字的字段就是每个表的名字。*/

        //4. 提取表内的字段的名字和类型
        String columnName;
        String columnType;
        ResultSet colRet = m_DBMetaData.getColumns(null, "%", m_TableName, "%");
        Map tableColumnAndType = new HashMap<String,String>();
        List list = new ArrayList<Table>();
        Integer index = 0;
        while (colRet.next()) {
            index++;
            columnName = colRet.getString("COLUMN_NAME");
            columnType = colRet.getString("TYPE_NAME");
            Table table = new Table(columnName, columnType,index);
            /*int datasize = colRet.getInt("COLUMN_SIZE");
            int digits = colRet.getInt("DECIMAL_DIGITS");
            int nullable = colRet.getInt("NULLABLE");*/
            //System.out.println(columnName+" "+columnType+" "+datasize+" "+digits+" "+ nullable);
            //System.out.println(columnName + " " + columnType);
            //tableColumnAndType.put(columnName,columnType);
            list.add(table);

        }
        return list;
    }
}
