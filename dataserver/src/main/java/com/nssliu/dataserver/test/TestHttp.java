package com.nssliu.dataserver.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nssliu.dataserver.utils.HttpRequests;
import com.nssliu.dataserver.utils.time.TimeUtil;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/18 18:10
 * @describe:
 */
public class TestHttp {
    public static void main(String[] args) {
        String s = HttpRequests.sendGet("https://restapi.amap.com/v3/traffic/status/road?&key=2598b89dcd9b01d20053e76bff151a00&name=人民大街&adcode=220100&extensions=all");
        JSONObject jsonObject = JSON.parseObject(s);
        String root = jsonObject.get("trafficinfo").toString();
        String description = JSON.parseObject(root).get("description").toString();
        String evaluation = JSON.parseObject(root).get("evaluation").toString();
        String roads = JSON.parseObject(root).get("roads").toString();
         /*if(roads.size()>0){
            JSONObject jsonObject1 = JSON.parseObject(roads.get(0).toString());

        }*/
        System.out.println(description);
        System.out.println(evaluation);
        System.out.println(roads);
        System.out.println("人民大街");
        System.out.println(new Date());
    }

    @Test
    public void testTime(){
        String s = TimeUtil.nowToString("yyyy-MM-dd");
        System.out.println(s);
    }
}
