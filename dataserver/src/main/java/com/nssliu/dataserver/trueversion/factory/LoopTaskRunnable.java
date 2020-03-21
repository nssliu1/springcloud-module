package com.nssliu.dataserver.trueversion.factory;

import com.nssliu.dataserver.trueversion.jsonDispose.GetListForHttp;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/19 13:14
 * @describe:
 */
public class LoopTaskRunnable implements LoopTask<GetListForHttp> {
    @Override
    public GetListForHttp getTask(String classFullName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        GetListForHttp getListForHttp = (GetListForHttp)Class.forName(classFullName).newInstance();
        //GetListForHttp getListForHttp = (GetListForHttp)Class.forName(classFullName).newInstance();
        return getListForHttp;
    }
}
