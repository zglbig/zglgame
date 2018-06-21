package org.zgl;

import java.util.Map;

/**
 * @作者： big
 * @创建时间： 2018/6/21
 * @文件描述：
 */
public enum RoomEnum {
    GATE(-100),
    ROOM_1(-7);
    private int id;

    RoomEnum(int id) {
        this.id = id;
    }
    private static Map<Integer,RoomEnum> map;
    static {
        for(RoomEnum r: RoomEnum.values()){
            map.put(r.id,r);
        }
    }
    public static RoomEnum getRoomEnum(int id){
        return map.get(id);
    }

    public int getId() {
        return id;
    }
}
