package org.zgl.message;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：
 */
public class IoMessagePackage {
    private int addressId;
    private IoMessage ioMessage;

    public IoMessagePackage() {
    }

    public IoMessagePackage(int addressId, IoMessage ioMessage) {
        this.addressId = addressId;
        this.ioMessage = ioMessage;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public IoMessage getIoMessage() {
        return ioMessage;
    }

    public void setIoMessage(IoMessage ioMessage) {
        this.ioMessage = ioMessage;
    }
}
