package com.nssliu.dataserver.controller;
import com.nssliu.dataserver.entity.Msg;
import com.nssliu.dataserver.entity.Table;
import com.nssliu.dataserver.service.JdbcGetData;
import com.nssliu.dataserver.service.JdbcGetTable;
import com.nssliu.dataserver.service.ListTableExecve;
import com.nssliu.dataserver.utils.buildclass.JdbcMsg;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
@RequestMapping(value = "/tableController",produces = "application/json;charset=UTF-8")
@CrossOrigin(origins="*")
public class TableController {
    static List<Table> constTabls;
    static List<Table> newTables;
    static String tableName = "smdtv_1";
    static String packageName = "com.nssliu.dataserver.entity.Smdtv_1";

    public static void main(String[] args) throws SQLException, ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String [] columns = new String[]{};//{"fillbackcolor","fillforecolor"};
        List<String> removeColumns = Arrays.asList(columns);
        /*int i = removeColumns.indexOf("fillbackcolor");
        removeColumns.remove(0);
        System.out.println(removeColumns);*/
        List<Table> end = ListTableExecve.getEnd(removeColumns);
        List smdtv_1 = JdbcGetData.getTableData(tableName, end ,packageName);
        System.out.println(smdtv_1);
    }
    //获取所有tablecolumn并存全局变量
    @RequestMapping("/getAllTableColumn")
    public Object getAllTableColumn(@RequestParam String tableName) throws SQLException, ClassNotFoundException {
        System.out.println(tableName);
        constTabls = JdbcGetTable.getTableDetailList(tableName);

        return constTabls;

    }
    //删除某些字段
    //@RequestMapping("/removeTableColumn")
    @RequestMapping(value = "/removeTableColumn",method = RequestMethod.POST)
    public Object removeTableColumn(String[] rtcs) throws SQLException, ClassNotFoundException {
        List<String> rc = Arrays.asList(rtcs);
        System.out.println(rc);
        newTables = ListTableExecve.getEnd(constTabls, rc);
        return new Msg(200,"成功");
    }
    //获取删除后的Tables
    @RequestMapping("/getAfterRemove")
    public Object getAfterRemove(){
        return new Msg(200,newTables);
    }
    //导出java类
    @RequestMapping("/downTableEntity")
    public Object downTableEntity() throws SQLException, IOException, ClassNotFoundException {
        //通过配置或者用户给的地址将内容加载进自定义的类加载器中进行反射创建对象进行是被内容
        JdbcMsg.createClass(tableName,packageName,newTables);
        return new Msg(200,"success");
    }
    //获取删除字段后的数据
    @GetMapping(value = "/getdbdata")
    public Object getdbdata(@RequestParam String pullClassNme) throws Exception{
        System.out.println(pullClassNme);

        List smdtv_1 = JdbcGetData.getTableData(tableName, newTables ,packageName);
        return new Msg(200,smdtv_1);
    }




}
