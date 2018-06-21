package org.zgl.proxy;

import org.zgl.Application;
import org.zgl.MutualEnum;
import org.zgl.ProtostuffUtils;
import org.zgl.message.*;
import org.zgl.session.ISession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @作者： big
 * @创建时间： 2018/6/8
 * @文件描述：下发通知（远程调用客户端接口） 也算回调
 */
public class TcpProxyOutboundHandler {
    @SuppressWarnings("unchecked")
    public static <T> T getRemoteProxyObj(final Class serviceInterFace, final ISession session, final List<Integer> uids, final Short address){
        return (T) Proxy.newProxyInstance(serviceInterFace.getClassLoader(), new Class<?>[]{serviceInterFace}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(uids == null || uids.size() <= 0 || address == null){
                    new RuntimeException("远程调用异常");
                }
                Class<?>[] parameterTypes = method.getParameterTypes();
                boolean isPrimitive = true;
                for(Class c : parameterTypes){
                    if(!c.isPrimitive()){
                        isPrimitive = false;
                        break;
                    }
                }
                //远程调用 客户端 、别的服务器
                ServerIoMessage s = null;
                if(address != MutualEnum.SERVER_TO_CLIENT.id()) {
                    //请求别的服务器
                    RequestIoMessage ioMessage = new RequestIoMessage();
                    ioMessage.setAddress(address);
                    ioMessage.setUid(uids.get(0));
                    ioMessage.setInterfaceName(serviceInterFace.getName());
                    ioMessage.setMethodName(method.getName());
                    ioMessage.setParamsType(parameterTypes);
                    ioMessage.setParams(args);
                    if (method.getReturnType().equals(void.class)) {
                        Application.getCc().asyncWrite(ioMessage);
                    } else {
                        s = Application.getCc().syncWrite(ioMessage);
                    }
                }else {
                    //通知客户端
                    ResultIoMessage ioMessage = new ResultIoMessage();
                    ioMessage.setUid(uids);
                    if(isPrimitive){
                        //参数都是基础数据类型
                        ioMessage.setResultType(MutualEnum.SERVER_TO_CLIENT_BASE.id());
                        ResponesBaseIoMessage result = new ResponesBaseIoMessage();
                        result.setInterfaceName(serviceInterFace.getName());
                        result.setMethodName(method.getName());
                        String requestParams = "";
                        for(int i = 0,j = args.length;i<j;i++){
                            if(i < j - 1){
                                requestParams += args[i]+"#";
                            }else {
                                requestParams += args[i];
                            }
                        }
                        result.setParams(requestParams);
                        ioMessage.setResult(result);
                    }else {
                        if(args.length <= 0)
                            throw new RuntimeException("参数不能为空");
                        ioMessage.setResultType(MutualEnum.SERVER_TO_CLIENT_BASE.id());
                        ResponesPBIoMessage result = new ResponesPBIoMessage();
                        result.setInterfaceName(serviceInterFace.getName());
                        result.setMethodName(method.getName());
                        byte[] buf = ProtostuffUtils.serializer(args[0]);
                        result.setParam(buf);
                        ioMessage.setResult(result);
                    }
                    Application.getCc().asyncWrite(ioMessage);
                }
                return s;
            }
        });
    }
}
