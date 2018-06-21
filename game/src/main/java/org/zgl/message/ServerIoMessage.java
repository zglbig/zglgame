package org.zgl.message;

/**
 * @作者： big
 * @创建时间： 2018/6/21
 * @文件描述：服务器之间的响应
 */
public class ServerIoMessage implements IoMessage{
    private int uid;//用户uid
    private int address;
    private int resultCode;//结果码 200成功 404失败
    private Object obj;//最终结果

    public ServerIoMessage() {
    }

    public ServerIoMessage(int uid, int address, int resultCode, Object obj) {
        this.uid = uid;
        this.address = address;
        this.resultCode = resultCode;
        this.obj = obj;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
