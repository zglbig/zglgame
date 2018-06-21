package org.zgl.session;

import java.util.List;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：
 */
public class SessionEntity {
    private int uid;
    private List<Integer> roomIds;//所在的房间、可能会在多个房间
    private long lastEditTime;//最后有操作的时间

    public SessionEntity() {
    }

    public SessionEntity(int uid, long lastEditTime) {
        this.uid = uid;
        this.lastEditTime = lastEditTime;
    }

    public List<Integer> getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(List<Integer> roomIds) {
        this.roomIds = roomIds;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public long getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(long lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}
