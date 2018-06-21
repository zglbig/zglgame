package org.zgl.message;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：服务器之间和网关之间的通讯消息类
 */
public class CIoMessage implements IoMessage{
    private int uid;//用户的uid
    private int address;
    private String interfaceName;
    private String methodMane;
    private Class<?>[] paramsType;
    private Object[] params;
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodMane() {
        return methodMane;
    }

    public void setMethodMane(String methodMane) {
        this.methodMane = methodMane;
    }

    public Class<?>[] getParamsType() {
        return paramsType;
    }

    public void setParamsType(Class<?>[] paramsType) {
        this.paramsType = paramsType;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }
}
