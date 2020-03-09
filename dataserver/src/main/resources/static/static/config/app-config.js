/**
 * 智慧平顶山空间信息子系统全局配置
 * @type {[type]}
 */
var APPCONFIG = APPCONFIG || {};
(function() {

    var location = window.location,host = location.port == '3000' ? 'http://127.0.0.1:8080' : location.protocol + '//' + location.hostname + ":" + location.port;
    APPCONFIG = {
      tableName:"smdtv_1",
      url:"http://127.0.0.1:8186/apps/start",
      oneUrl:"http://127.0.0.1:8186/appOne/start",
      apps:[
        {
          //"name":"activeMq",
          "basePath":"E:/supermap/softbag/supermap_platform/快捷启动/",
          "name":"1-Activemq.bat",
          "ip":"127.0.0.1",
          "port":"8161",
          "attemptLimit":"10"
        },
        {
          //"name":"redis",
          "basePath":"E:/supermap/softbag/supermap_platform/快捷启动/",
          "name":"2-Mongodb.bat",
          "ip":"127.0.0.1",
          "port":"38624",
          "attemptLimit":"10"

        },
        {
          //"name":"mongodb",
          "basePath":"E:/supermap/softbag/supermap_platform/快捷启动/",
          "name":"3-Redis.bat",
          "ip":"127.0.0.1",
          "port":"6380",
          "attemptLimit":"10"

        },
        {
          //"name":"mule",
          "basePath":"E:/supermap/softbag/supermap_platform/快捷启动/",
          "name":"6-Mule.bat",
          "ip":"127.0.0.1",
          "port":"8081",
          "attemptLimit":"20"

        },
        {
          //"name":"sgs",
          "basePath":"E:/supermap/softbag/supermap_platform/快捷启动/",
          "name":"8-SGS.bat",
          "ip":"127.0.0.1",
          "port":"8080",
          "attemptLimit":"50"

        }

      ],
      muleFileBak:{
        "url":"http://127.0.0.1:8186/fileOperate/copyFile",
        "sourceFilePath":"E:\\supermap\\softbag\\supermap_platform\\mule-standalone-3.4.0\\bak\\geoesb",
        "newFilePath":"E:\\supermap\\softbag\\supermap_platform\\mule-standalone-3.4.0\\apps"
      },
      iserverLog:{
        "url":"http://127.0.0.1:8186/fileOperate/deleteFile",
        "paths":"E:\\supermap\\softbag\\supermap_platform\\iServer1\\support\\objectsjava\\bin\\log"
      },
      iserverLogLoop:{
        "url":"http://127.0.0.1:8186/fileOperate/deleteFileTimeLoop",
        "paths":"E:\\supermap\\softbag\\supermap_platform\\iServer1\\support\\objectsjava\\bin\\log"
      },
      iserverLogLoopCancel:{
        "url":"http://127.0.0.1:8186/fileOperate/deleteFileTimeLoopCancel"
      },
      appVT:{
        addFlowUrl:"http://127.0.0.1:8186/propertiesAction/addFlow",
        addAppUrl:"http://127.0.0.1:8186/propertiesAction/addApp",
        queryAllFlowUrl:"http://127.0.0.1:8186/propertiesAction/queryAllFlow",
        queryAllBatAppUrl:"http://127.0.0.1:8186/propertiesAction/queryAllBatApp",
        deleteOne:"http://127.0.0.1:8186/propertiesAction/deleteOne",
        queryOneFlowUrl:"http://127.0.0.1:8186/propertiesAction/queryOneFlow",
        queryOneBatUrl:"http://127.0.0.1:8186/propertiesAction/queryOneBat",
        addRecoverUrl:"http://127.0.0.1:8186/propertiesAction/addRecover",
        queryAllRecover:"http://127.0.0.1:8186/propertiesAction/queryAllRecover",
        queryOneRecover:"http://127.0.0.1:8186/propertiesAction/queryOneRecover",
        addLogUrl:"http://127.0.0.1:8186/propertiesAction/addLogDetail",
        queryAllLog:"http://127.0.0.1:8186/propertiesAction/queryAllLog",
        queryOneLog:"http://127.0.0.1:8186/propertiesAction/queryOneLog",
        addTimerUrl:"http://127.0.0.1:8186/propertiesAction/addTimer",
        cancelTimer:"http://127.0.0.1:8186/propertiesAction/cancelTimer"
      }
    }
}());
