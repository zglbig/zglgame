package org.zgl.message;

import org.zgl.TypeExchange;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：
 */
public class SIoMessageHandler {
    public static IoMessage sIoMessageRead(int uid,SIoMessage ioMessage){
        String[] paramTypes = TypeExchange.split(ioMessage.getParamsType());
        String[] params = TypeExchange.split(ioMessage.getParams());
        if(paramTypes.length != params.length){
            //数据参数不能为空
        }
        Class<?>[] paramTypeC = new Class[paramTypes.length];
        Object[] paramsC = new Object[params.length];
        for(int i = 0;i<paramTypeC.length;i++){
            paramTypeC[i] = TypeExchange.paramObjType(paramTypes[i]);
            paramsC[i] = TypeExchange.paramValueType(paramTypes[i],params[i]);
        }
        CIoMessage cIoMessage = new CIoMessage();
        cIoMessage.setUid(uid);
        cIoMessage.setAddress(ioMessage.getAddress());
        cIoMessage.setInterfaceName(ioMessage.getInterfaceName());
        cIoMessage.setMethodMane(ioMessage.getMethodName());
        cIoMessage.setParamsType(paramTypeC);
        cIoMessage.setParams(paramsC);
        return cIoMessage;
    }
    public void cIoMessageRead(IoMessage ioMessage){}
}
