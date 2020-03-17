package com.nssliu.dataserver.trueversion.jsonDispose;

import com.nssliu.dataserver.trueversion.entity.CallBackEntity;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/17 10:52
 * @describe:
 */
public interface GetListForHttp {
    public CallBackEntity getCreatinformation();
    public CallBackEntity getList() throws IllegalAccessException, InstantiationException;
}
