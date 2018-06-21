package org.zgl.message;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：
 */
public class RegistIoMessage implements IoMessage {
    private int uid;

    public RegistIoMessage() {
    }

    public RegistIoMessage(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
