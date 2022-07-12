package com.example.mybatispluslearn01.utils;

/**
 * @author malguy-wang sir
 * @create ---
 */
public class iUtils {
    //判断字符串是不是数字
    public static boolean isNumber(String s){
        for (int i = 0; i < s.length(); i++) {
            if(!Character.isDigit(s.charAt(i))){
                return false;
            }
        }return true;
    }
    //传入来自源根的路径,返回全类名
    public static String getClassName(String path){
        path=path.replace("/", ".");
        path=path.replace("\\", ".");
        path=path.replace(".java", "");
//        System.out.println(path);
        return path;
    }
}
