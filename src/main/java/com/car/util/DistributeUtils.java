package com.car.util;

public class DistributeUtils {
    public static String[] distributeText(String text,String split){
        if(text == null || text.length() == 0)return null;
        String modifiedString = text.replace(split, " ");
        // 使用空格分割字符串，并转换为数组
        String[] modifiedArray = modifiedString.split(" ");
        return  modifiedArray;
    }
}
