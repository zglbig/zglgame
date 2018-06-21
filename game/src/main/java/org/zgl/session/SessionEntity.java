package org.zgl.session;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：
 */
public class SessionEntity {
    private int uid;
    private long lastEditTime;//最后有操作的时间

    public SessionEntity() {
    }

    public SessionEntity(int uid, long lastEditTime) {
        this.uid = uid;
        this.lastEditTime = lastEditTime;
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
