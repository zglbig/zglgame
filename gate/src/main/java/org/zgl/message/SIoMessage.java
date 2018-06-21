package org.zgl.message;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：客户端和网关之间的通讯消息类
 */
public class SIoMessage implements IoMessage{
    private int address;
    private String interfaceName;
    private String methodName;
    private String paramsType;//参数类型 以$分割:str->String,i->int,b->byte,sh->short,c->char,l->long,f->float,d->double,bl->boolean
    private String params;

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

    public String getParamsType() {
        return paramsType;
    }

    public void setParamsType(String paramsType) {
        this.paramsType = paramsType;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }
}
