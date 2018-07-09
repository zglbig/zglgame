package org.zgl;

import java.util.HashMap;
import java.util.Map;

/**
 * @作者： big
 * @创建时间： 2018/6/21
 * @文件描述：
 */
public enum MutualEnum {
    CLIENT_TO_SERVER(-10),//客户端请求服务器
    SERVER_TO_SERVER(-20),//一个服务器请求另外一个服务器 网关到服务器也算
    SERVER_TO_CLIENT(-40),//服务发送数据到客户端 网关也算
    REGIST(-100);//注册链接
    private int id;

    MutualEnum(int id) {
        this.id = id;
    }
    private static final Map<Integer,MutualEnum> map;
    static {
        map = new HashMap<>();
        for(MutualEnum m : MutualEnum.values()){
            map.put(m.id,m);
        }
    }
    public int id(){
        return id;
    }
    public static MutualEnum getMutualEnum(int id){
        return map.get(id);
    }
}
