package com.nssliu.dataserver.controller;
import com.nssliu.dataserver.entity.Msg;
import com.nssliu.dataserver.entity.Table;
import com.nssliu.dataserver.service.JdbcGetData;
import com.nssliu.dataserver.service.JdbcGetTable;
import com.nssliu.dataserver.service.ListTableExecve;
import com.nssliu.dataserver.service.esinterface.EsService;
import com.nssliu.dataserver.trueversion.NewJavaToEsPoint;
import com.nssliu.dataserver.trueversion.entity.CallBackEntity;
import com.nssliu.dataserver.trueversion.factory.LoopTask;
import com.nssliu.dataserver.trueversion.factory.LoopTaskRunnable;
import com.nssliu.dataserver.trueversion.jsonDispose.GetListForHttp;
import com.nssliu.dataserver.trueversion.threadobserver.Observer;
import com.nssliu.dataserver.trueversion.threadobserver.ObserverThread;
import com.nssliu.dataserver.trueversion.threadobserver.TheObserver;
import com.nssliu.dataserver.trueversion.threadobserver.TheObserverThread;
import com.nssliu.dataserver.utils.buildclass.JdbcMsg;
import com.nssliu.dataserver.utils.es.EsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private static final TheObserver<Observer> theObserver = new TheObserverThread<>();//维护一个全局变量
    private static final Observer observer1 = new ObserverThread();
    static {
        theObserver.regist(observer1);
    }


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

    //按照某个实现的类进行创建索引，索引名和type均已写在类中，或者配置在配置文件中
    @CrossOrigin(origins="*")
    @RequestMapping("/creatIndex")
    public Object creatIndex(@RequestParam String classFullName) {
        try {
            NewJavaToEsPoint.testCreateIndex(classFullName);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Msg(200,"索引已创建");
    }
    //注册任务
    @CrossOrigin(origins="*")
    @RequestMapping("/registTask")
    public Object regist(@RequestParam String taskName,@RequestParam String classFullName,@RequestParam long time,@RequestParam boolean isloop){
        //创建索引
        try {
            NewJavaToEsPoint.testCreateIndex(classFullName);
        } catch (IllegalAccessException e) {
            System.out.println("创建索引异常");
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.out.println("创建索引异常");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("创建索引异常");
            e.printStackTrace();
        }
        //同步数据
        theObserver.addTask(taskName, new Runnable() {
            @Override
            public void run() {
                try {
                    if(!isloop){
                        //执行逻辑
                        GetListForHttp getListForHttp = new LoopTaskRunnable().getTask(classFullName);
                        CallBackEntity callBackEntity = getListForHttp.getList();
                        if(callBackEntity==null){
                            System.out.println("CallBackEntity不可为空");
                            return;
                        }
                        NewJavaToEsPoint.syncData(callBackEntity.getIndexName(),callBackEntity.getType(),callBackEntity.getClazz(),callBackEntity.getList());
                    }else {
                        while (isloop){
                            //执行逻辑
                            GetListForHttp getListForHttp = new LoopTaskRunnable().getTask(classFullName);
                            CallBackEntity callBackEntity = getListForHttp.getList();
                            if(callBackEntity==null){
                                System.out.println("CallBackEntity不可为空");
                                return;
                            }
                            NewJavaToEsPoint.syncData(callBackEntity.getIndexName(),callBackEntity.getType(),callBackEntity.getClazz(),callBackEntity.getList());
                            TimeUnit.SECONDS.sleep(time);

                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println(taskName+"任务子程序被打断");
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    System.out.println("同步数据异常");
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    System.out.println("同步数据异常");
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    System.out.println("同步数据异常");
                    e.printStackTrace();
                }
            }
        });
        return new Msg(200,"注册成功,已启动定时同步");

    }

    @RequestMapping("/cancelTask")
    public Object cancelTask(@RequestParam String taskName){
        theObserver.removeTask(taskName);
        return new Msg(200,"任务："+taskName+"已注销");
    }




}
