package org.zgl.message;

import java.util.List;

/**
 * @作者： big
 * @创建时间： 2018/6/29
 * @文件描述：
 */
public class ClientRequsetIoMessage implements IoMessage {
    private short interfaceCode;//接口名
    private short methodCode;//方法名
    private List<String> args;//参数

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

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }
}
