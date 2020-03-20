package com.nssliu.dataserver.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nssliu.dataserver.entity.ScenePoint;
import com.nssliu.dataserver.utils.HttpRequests;
import com.nssliu.dataserver.utils.time.TimeUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/18 18:10
 * @describe:
 */
public class TestHttp {
    public static void main(String[] args) {
        List list = new ArrayList();
        String dateTime = TimeUtil.nowToString("yyyy-MM-dd");
        try {

            String sTotal = HttpRequests.sendGet("http://api.map.baidu.com/place/v2/search?&ak=Hg7H5dcSZXpkGcq09sk7v8b6xTk9VQGK&query=%E6%99%AF%E7%82%B9&region=%E9%95%BF%E6%98%A5&output=json&scope=2&filter=total_score&coord_type=1&page_size=20&page_num=1");
            JSONObject rootTotal = JSON.parseObject(sTotal);
            Integer total = new Integer(rootTotal.get("total").toString());
            int allPage = total/20;
            if(total%20>0){
                allPage++;
            }

            for (int j = 0;j<allPage;j++){
                String s = HttpRequests.sendGet("http://api.map.baidu.com/place/v2/search?&ak=Hg7H5dcSZXpkGcq09sk7v8b6xTk9VQGK&query=%E6%99%AF%E7%82%B9&region=%E9%95%BF%E6%98%A5&output=json&scope=2&filter=total_score&coord_type=1&page_size=20&page_num="+j);
                JSONObject root = JSON.parseObject(s);
                JSONArray results = JSON.parseArray(root.get("results").toString());
                for (int i = 0;i<results.size();i++){
                    JSONObject someOne = JSON.parseObject(results.get(i).toString());
                    JSONObject location = null;
                    double lat = 0;
                    double lng = 0;
                    if(someOne.get("location")!=null && !"".equals(someOne.get("location"))){

                        location = JSON.parseObject(someOne.get("location").toString());
                        lat = new Double(location.get("lat").toString());
                        lng = new Double(location.get("lng").toString());
                    }
                    JSONObject detail_info = null;
                    if(someOne.get("detail_info")!=null && !"".equals(someOne.get("detail_info"))){

                        detail_info = JSON.parseObject(someOne.get("detail_info").toString());
                    }
                    double overall_rating = 0;
                    if(detail_info.get("overall_rating")!=null && !"".equals(detail_info.get("overall_rating"))){

                        overall_rating  = new Double(detail_info.get("overall_rating").toString());
                    }
                    String name = null;
                    if(someOne.get("name")!=null && !"".equals(someOne.get("name"))){

                        name = someOne.get("name").toString();
                    }
                    String address = null;
                    if(someOne.get("address")!=null && !"".equals(someOne.get("address"))){

                        address = someOne.get("address").toString();
                    }
                    String province = null;
                    if(someOne.get("province")!=null && !"".equals(someOne.get("province"))){

                        province = someOne.get("province").toString();
                    }
                    String city = null;
                    if(someOne.get("city")!=null && !"".equals(someOne.get("city"))){

                        city = someOne.get("city").toString();
                    }
                    String area = null;
                    if(someOne.get("area")!=null && !"".equals(someOne.get("area"))){

                        area = someOne.get("area").toString();
                    }
                    String telephone = null;
                    if(someOne.get("telephone")!=null && !"".equals(someOne.get("telephone"))){

                        telephone = someOne.get("telephone").toString();
                    }
                    Integer detail = null;
                    if(someOne.get("detail")!=null && !"".equals(someOne.get("detail"))){

                        detail = new Integer(someOne.get("detail").toString());
                    }
                    String uid = null;
                    if(someOne.get("uid")!=null && !"".equals(someOne.get("uid"))){

                        uid = someOne.get("uid").toString();
                    }
                    String detail_infoStr = null;
                    if(someOne.get("detail_info")!=null && !"".equals(someOne.get("detail_info"))){

                        detail_infoStr = someOne.get("detail_info").toString();
                    }
                    ScenePoint scenePoint = new ScenePoint(dateTime,name,lng,lat,address,province,city,area,telephone,detail,uid,detail_infoStr,overall_rating);
                    list.add(scenePoint);

                }
            }




        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(list);
    }

    @Test
    public void testTime(){
        String s = TimeUtil.nowToString("yyyy-MM-dd");
        System.out.println(s);
    }
}
