package com.nssliu.dataserver.trueversion.jsonDispose;

import com.nssliu.dataserver.entity.Fj;
import com.nssliu.dataserver.entity.ScenePoint;
import com.nssliu.dataserver.trueversion.entity.CallBackEntity;
import com.nssliu.dataserver.utils.HttpRequests;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/17 17:35
 * @describe:
 */
public class GetListForHttpScenePoint implements GetListForHttp {
    static String indexName = "new";
    static String indexType = "cc";
    static Class clazz = ScenePoint.class;
    @Override
    public CallBackEntity getCreatinformation() {
        return new CallBackEntity(indexName,indexType,"",clazz,"",null);
    }

    @Override
    public CallBackEntity getList() throws IllegalAccessException, InstantiationException {
        String s1 = HttpRequests.sendGet("https://www.amap.com/service/poiInfo?query_type=TQUERY&pagesize=20&pagenum=1&qii=true&cluster_state=5&need_utd=true&utd_sceneid=1000&div=PC1000&addr_poi_merge=true&is_classify=true&zoom=11&city=220100&keywords=%E6%99%AF%E7%82%B9");
        System.out.println(s1);
        return null;
    }
}
