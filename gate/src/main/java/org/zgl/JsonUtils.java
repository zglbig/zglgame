package org.zgl;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class JsonUtils {
    /**
     * 序列化：对象转成字符串
     */
    public static String jsonSerialize(Object obj){
        String msg = JSON.toJSONString(obj);
        return msg;
    }

    /**
     * 反序列化：字符串转成对象
     * @param body
     */
    public static <T> T jsonDeserialization(String body,Class<T> clazz){
        T obj = JSON.parseObject(body,clazz);
        return obj;
    }
    public static <T> List<T> jsonParseList(String body, Class<T> clazz) {
        if (body == null || body == ""){
            return null;
        }
        List<T> obj = JSON.parseArray(body, clazz);
        return obj;
    }

}
