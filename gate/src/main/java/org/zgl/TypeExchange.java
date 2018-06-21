package org.zgl;

import java.io.Serializable;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：
 */
public class TypeExchange {
    public static Object paramValueType(String type,String value){
        switch (type){
            case "str":
                return value;
            case "b":
                return Byte.parseByte(value);
            case "sh":
                return Short.parseShort(value);
//            case "c":
//                return
            case "i":
                return Integer.parseInt(value);
            case "l":
                return Long.parseLong(value);
            case "f":
                return Float.parseFloat(value);
            case "d":
                return Double.parseDouble(value);
            case "bl":
                return Boolean.parseBoolean(value);
                default:
                    return null;
        }
    }
    public static Class<?> paramObjType(String type){
        switch (type){
            case "str":
                return String.class;
            case "b":
                return byte.class;
            case "sh":
                return short.class;
//            case "c":
//                return
            case "i":
                return int.class;
            case "l":
                return long.class;
            case "f":
                return float.class;
            case "d":
                return double.class;
            case "bl":
                return boolean.class;
            default:
                return null;
        }
    }
    public static String[] split(String str){
        String[] s = str.split("#");
        return s;
    }
}
