package com.nssliu.dataserver.service;

import com.nssliu.dataserver.entity.Table;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/2/24 17:35
 * @describe:
 */
public class ListTableExecve {
    //根据删除的TableColumnNames删除库中的内容
    public static List<Table> getEnd(List<String> removeColumns) throws SQLException, ClassNotFoundException {
        List<Table> tables = JdbcGetTable.getTableDetailList("smdtv_1");



        Iterator<Table> iterator = tables.iterator();
        while (iterator.hasNext()) {
            Table table = iterator.next();
            if (removeColumns.indexOf(table.getColumn_name()) != -1){
                iterator.remove();
            }
        }
        return tables;
    }

    //根据删除的TableColumnNames删除库中的内容
    public static List<Table> getEnd(List<Table> tables,List<String> removeColumns) throws SQLException, ClassNotFoundException {
        List<Table> newTables = new ArrayList<>();
        Iterator<Table> iterator = tables.iterator();
        while (iterator.hasNext()) {
            Table table = iterator.next();
            if (removeColumns.indexOf(table.getColumn_name()) == -1){
                newTables.add(table);
            }
        }
        return newTables;
    }
}
