package org.zgl.proxy;


/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：
 */
public abstract class OperationAbstract {
    private long uid;
    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void submin(Runnable runnable) {
        new Thread(runnable).start();
    }
}
