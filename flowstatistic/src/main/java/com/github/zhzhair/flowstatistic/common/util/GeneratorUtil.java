package com.github.zhzhair.flowstatistic.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GeneratorUtil {
    /**
     * 模拟手机号
     */
    public static String getMobileStr(int userId){
        String[] strings = {"13","15","16","18"};
        String beginString = strings[new Random().nextInt(4)];
        String endString = String.valueOf(userId);
        int length = 9 - endString.length();
        StringBuilder stringBuilder = new StringBuilder(beginString);
        for (int i = 0; i < length; i++) {
            stringBuilder.append("0");
        }
        return stringBuilder.append(endString).toString();
    }

    /**
     * 模拟手机号
     */
    public static String getMobileStr(){
        return getMobileStr(new Random().nextInt(10_0000_0000));
    }

    /**
     * 模拟用户信息
     */
    public static List<HashMap<String,Object>> getList() {
        int size = 100;
        List<HashMap<String,Object>> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            HashMap<String,Object> map = new HashMap<>();
            map.put("name","用户" + i);
            map.put("sex",new Random().nextInt(2));
            map.put("income",i*1000);
            list.add(map);
        }
        return list;
    }

    /**
     * 随机获取字符串数组的一个元素
     */
    public static String getRandomStr(String[] str){
        int n = new Random().nextInt(str.length);
        return str[n];
    }
}
