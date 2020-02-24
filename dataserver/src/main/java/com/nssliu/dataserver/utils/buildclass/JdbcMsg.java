package com.nssliu.dataserver.utils.buildclass;


import com.nssliu.dataserver.entity.Table;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2019/11/11 18:36
 * @describe:
 * {
 *     "className":{"类名":"public(null)"},
 *     "propertyName":{"属性名":"String"
 *                     "属性名":"Integer"},
 *     "packageName":{"包名":"包路径（null）"}
 * }
 */
public class JdbcMsg {
    private static OrmEntity ormEntity = new OrmEntity();
    private static Map<String,Map<String,String>> classMap = new HashMap();
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/esdb",
                "postgres", "123456");
        String m_TableName = "smdtv_1";


        DatabaseMetaData m_DBMetaData = conn.getMetaData();
        ResultSet tableRet = m_DBMetaData.getTables(null, "%",m_TableName,new String[]{"TABLE"});

        ormEntity.setPackageName("com.nssliu.dataserver.entity");
        while(tableRet.next()){
            String tableName = tableRet.getString("TABLE_NAME");
            ormEntity.setClassType("public");
            ormEntity.setClassName(tableName);

        }

        String columnName;
        String columnType;
        ResultSet colRet = m_DBMetaData.getColumns(null,"%", m_TableName,"%");
        while(colRet.next()) {
            columnName = colRet.getString("COLUMN_NAME");
            columnType = colRet.getString("TYPE_NAME");
            ormEntity.getPropertyMap().put(columnName,columnType);//此时我对put进行了复写
        }

        ProduceClassV2.produceClass(ormEntity);


    }

    //根据类名，包名，属性表导出类
    public static void createClass(String tableName,String packageName,List<Table> tableList) throws SQLException, IOException, ClassNotFoundException {
        ormEntity.setPackageName(packageName);
        ormEntity.setClassName(tableName);
        ormEntity.setClassType("public");
        Iterator<Table> iterator = tableList.iterator();
        while (iterator.hasNext()){
            Table next = iterator.next();
            ormEntity.getPropertyMap().put(next.getColumn_name(),next.getType_name());
        }
        ProduceClassV2.produceClass(ormEntity);
    }
}

