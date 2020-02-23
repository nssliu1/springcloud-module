package com.nssliu.dataserver.controller;

import com.nssliu.dataserver.entity.Table;
import com.nssliu.dataserver.service.JdbcGetTable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/2/23 22:58
 * @describe:
 */
@RestController
@RequestMapping("/tableController")
public class TableController {
    static ThreadLocal<List<Table>> localVar = new ThreadLocal<>();

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String [] columns = new String[]{"fillbackcolor","fillforecolor"};
        List<String> removeColumns = Arrays.asList(columns);
        /*int i = removeColumns.indexOf("fillbackcolor");
        removeColumns.remove(0);
        System.out.println(removeColumns);*/
        List<Table> end = getEnd(removeColumns);
        System.out.println(end);
    }

    public static List<Table> getEnd(List<String> removeColumns) throws SQLException, ClassNotFoundException {
        List<Table> smdtv_1 = JdbcGetTable.getTableDetailList("smdtv_1");
        localVar.set(smdtv_1);
        List<Table> tables = localVar.get();


        Iterator<Table> iterator = tables.iterator();
        while (iterator.hasNext()) {
            Table table = iterator.next();
            if (removeColumns.indexOf(table.getColumn_name()) != -1){
                iterator.remove();
            }
        }

        localVar.set(tables);
        return tables;
    }
}
