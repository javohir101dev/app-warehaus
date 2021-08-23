package com.appwarehaus.helper;

import java.util.List;

public class Utils {
    public static boolean isEmptry(String string){
        return string==null || string.isEmpty();
    }

    public static boolean isEmptry(Object object){
        return object==null ;
    }

    public static boolean isEmptry(List<?> list ){
        return list==null || list.isEmpty();
    }
    public static boolean isEmptry(Integer integer){
        return integer==null || integer==0 ;
    }
}
