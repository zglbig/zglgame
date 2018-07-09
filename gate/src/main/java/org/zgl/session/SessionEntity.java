package org.zgl.session;

import java.util.List;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：
 */
public class SessionEntity {
    private long uid;
    private List<Integer> gameIds;//所在的房间、可能会在多个房间
    private long lastEditTime;//最后有操作的时间

    public SessionEntity() {
    }

    public SessionEntity(long uid, long lastEditTime) {
        this.uid = uid;
        this.lastEditTime = lastEditTime;
    }

    public List<Integer> getGameIds() {
        return gameIds;
    }

    public void setGameIds(List<Integer> gameIds) {
        this.gameIds = gameIds;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(long lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}
