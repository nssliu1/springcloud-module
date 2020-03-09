package com.nssliu.dataserver;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/9 17:13
 * @describe:
 */

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.io.IOException;

/**
 * @author 刘
 * @create 2019-06-08 11:30
 */
@Configuration
public class AutomaticAccess {
    @EventListener({ApplicationReadyEvent.class})
    void applicationReadyEvent() {
        System.out.println("应用已经准备就绪 ... 启动浏览器");
        String url = "http://127.0.0.1:8766";
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
