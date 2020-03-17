package com.nssliu.dataserver.trueversion.jsonDispose;

import com.nssliu.dataserver.entity.Fj;
import com.nssliu.dataserver.trueversion.entity.CallBackEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/17 10:53
 * @describe:
 */
public class GetListForHttpAqi implements  GetListForHttp{
    @Override
    public CallBackEntity getCreatinformation() {
        return new CallBackEntity("fj2","cc","",Fj.class,"",null);
    }

    @Override
    public CallBackEntity getList() throws IllegalAccessException, InstantiationException {
        CallBackEntity callBackEntity = new CallBackEntity();
        callBackEntity.setIndexName("fj2");
        callBackEntity.setType("cc");
        callBackEntity.setClazz(Fj.class);
        List list = new ArrayList();
        Fj fj = new Fj();

        fj.setArea_code("01");
        fj.setArea_name("长春");
        fj.setDate_date("2020.05");
        fj.setPrice(new Double(2000000));

        list.add(fj);

        /*Class<AqiData> aqiDataClass = AqiData.class;
        List<AqiData> list = new ArrayList<>();

        AqiData aqiData = aqiDataClass.newInstance();
        aqiData.setAqi(1.11);

        aqiData.setSmx(1);
        aqiData.setSmy(1);
        aqiData.setSmx1(1);
        aqiData.setSmy1(1);
        aqiData.setUpdateTime("2020-11-01 15:13:32");
        aqiData.setAqi(1);
        list.add(aqiData);*/
        callBackEntity.setList(list);
        return callBackEntity;
    }
}
