package com.nssliu.dataserver.controller;
import com.nssliu.dataserver.entity.Msg;
import com.nssliu.dataserver.entity.Table;
import com.nssliu.dataserver.service.JdbcGetData;
import com.nssliu.dataserver.service.JdbcGetTable;
import com.nssliu.dataserver.service.ListTableExecve;
import com.nssliu.dataserver.service.esinterface.EsService;
import com.nssliu.dataserver.utils.buildclass.JdbcMsg;
import com.nssliu.dataserver.utils.es.EsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/2/23 22:58
 * @describe:
 */
@RestController
@RequestMapping(value = "/EsForHttpController",produces = "application/json;charset=UTF-8")
@CrossOrigin(origins="*")
public class EsForHttpController {
    @Autowired
    private EsService esService;
    //获取所有tablecolumn并存全局变量
    @RequestMapping("/javaCreateEsIndex")
    public Object javaCreateEsIndex(@RequestParam String indexName,@RequestParam String type,@RequestParam String javaName) throws SQLException, ClassNotFoundException {
        esService.createEsIndex(indexName,type,javaName);
        return new Msg(200,"成功");
    }
    //删除某些字段
    //@RequestMapping("/removeTableColumn")
    @RequestMapping(value = "/removeTableColumn")
    public Object removeTableColumn(@RequestBody List<String> rtcs) throws SQLException, ClassNotFoundException {

        return new Msg(200,"成功");
    }

    //导出java类
    @RequestMapping("/downTableEntity")
    public Object downTableEntity() throws SQLException, IOException, ClassNotFoundException {


        return new Msg(200,"导出成功，请到配置目录查看默认D:\\0liuzh\\0study\\0githubs\\allproject\\0createEntity");
    }
    //获取删除字段后的数据并同步es
    @RequestMapping(value = "/getdbdata")
    //@CrossOrigin(origins = "http://127.0.0.1:8080")
    public Object getdbdata(@RequestParam String pullClassNme) throws Exception{

        return new Msg(200,"导出成功");
    }




}
