package com.wzl.goshare.utils;

/**
 * 作者：Create on 2019/9/11 13:03  by  wzl
 * 描述：
 * 最近修改：2019/9/11 13:03 modify by wzl
 */
public class StringUtils {

    private StringUtils() {
        throw new AssertionError();
    }

    public static boolean isBlank(CharSequence str) {
        return (isEmpty(str) || str.toString().trim().length()==0);
    }


    public static boolean isEmpty(CharSequence str) {
        return (str == null || str.length() == 0);
    }

    public static int length(CharSequence str) {
        return str == null ? 0 : str.length();
    }


    public static boolean isNotBlank(Object str){
        if(str == null){
            return false;
        }else{
            if(String.valueOf(str).trim().length() == 0){
                return false;
            }
        }
        return true;
    }

    public static boolean equals(String str1,String str2){

        return str1 != null ? str1.equals(str2) : str2 == null;

    }

}
