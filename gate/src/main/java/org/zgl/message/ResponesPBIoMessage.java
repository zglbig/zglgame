package org.zgl.message;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：网关应答客户端PB类型的消息
 */
public class ResponesPBIoMessage implements IoMessage {
    private String interfaceName;
    private String methodName;
    private byte[] param;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public byte[] getParam() {
        return param;
    }

    public void setParam(byte[] param) {
        this.param = param;
    }
}
