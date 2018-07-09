package org.zgl.message;

import java.util.List;

/**
 * @作者： big
 * @创建时间： 2018/6/29
 * @文件描述：服务间通讯都用这个消息类 不管是请求还是响应
 */
public class ServerCommunicationIoMessage implements IoMessage {
    private List<Long> uids;
    private String interfaceName;
    private String methodName;
    private short interfaceCode;
    private short methodCode;
    private Class<?>[] paramsType;
    private Object[] args;

    public List<Long> getUids() {
        return uids;
    }

    public void setUids(List<Long> uids) {
        this.uids = uids;
    }

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

    public short getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceCode(short interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public short getMethodCode() {
        return methodCode;
    }

    public void setMethodCode(short methodCode) {
        this.methodCode = methodCode;
    }

    public Class<?>[] getParamsType() {
        return paramsType;
    }

    public void setParamsType(Class<?>[] paramsType) {
        this.paramsType = paramsType;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
