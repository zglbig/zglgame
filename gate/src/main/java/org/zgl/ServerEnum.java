package org.zgl;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：
 */
public enum ServerEnum {
    DB_SERVER(1),
    HALL(2),
    GAME_1(3);
    private int id;

    ServerEnum(int id) {
        this.id = id;
    }
    public int id(){
        return id;
    }
}
