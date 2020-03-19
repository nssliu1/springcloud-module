package com.nssliu.dataserver.trueversion.jsonDispose;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nssliu.dataserver.entity.AqiData;
import com.nssliu.dataserver.entity.Bus;
import com.nssliu.dataserver.trueversion.entity.CallBackEntity;
import com.nssliu.dataserver.utils.HttpRequests;
import com.nssliu.dataserver.utils.time.TimeUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/18 18:28
 * @describe:
 */
public class GetListForHttpBus implements GetListForHttp {
    static String indexName = "bus";
    static String indexType = "cc";
    static Class clazz = Bus.class;
    @Override
    public CallBackEntity getCreatinformation() {
        return new CallBackEntity(indexName,indexType,"",clazz,"",null);
    }

    @Override
    public CallBackEntity getList() throws IllegalAccessException, InstantiationException {
        List<String> roadNames = new ArrayList<>();
        String []rodsList = new String[]{"人民大街","解放大路","自由大路","前进大街","上海路"};
        List<String> strings = Arrays.asList(rodsList);

        CallBackEntity callBackEntity = new CallBackEntity();
        callBackEntity.setIndexName(indexName);
        callBackEntity.setType(indexType);
        callBackEntity.setClazz(clazz);
        List list = new ArrayList();

        for (String busName:strings){
            Bus bus = new Bus();
            String s = HttpRequests.sendGet("https://restapi.amap.com/v3/traffic/status/road?&key=2598b89dcd9b01d20053e76bff151a00&name="+busName+"&adcode=220100");//&extensions=all
            JSONObject jsonObject = JSON.parseObject(s);
            String root = jsonObject.get("trafficinfo").toString();
            String description = JSON.parseObject(root).get("description").toString();
            String evaluation = JSON.parseObject(root).get("evaluation").toString();
            //String roads = JSON.parseObject(root).get("roads").toString();
            String status = JSON.parseObject(evaluation).get("status").toString();
            bus.setDateTime(TimeUtil.nowToString("yyyy-MM-dd"));
            bus.setDescription(description);
            bus.setEvaluation(evaluation);
            bus.setStatus(status);
            //bus.setRoads(roads);
            bus.setRoadName(busName);
            list.add(bus);
        }
        callBackEntity.setList(list);
        return callBackEntity;
    }
}
