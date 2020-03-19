package com.nssliu.dataserver.utils.time;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/12 15:06
 * @describe:
 */
public class TimeUtil {
    @Test
    public void testTime() throws ParseException {
        String time = "2018.3";
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy.MM");
        Date parse = dateFormat1.parse(time);
        System.out.println(parse.getYear());
    }
    public static Date getDate(String date) {
        String time = "2018.3";
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy.MM");
        Date parse = null;
        try {
            parse = dateFormat1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    @Test
    public void testTimeStamp(){
        String str= "1583683200000";
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM");
        long lt = new Long(str);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        System.out.println(res.toString());
    }
    public static String testTimeStamp(String timeStamp){
        String str= timeStamp;
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM");
        long lt = new Long(str);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res.toString();
    }

    public static String nowToString(String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);//设置日期格式
        Date now = new Date();
        return sdf.format(now);
    }
}
