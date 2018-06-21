package org.zgl.proxy;

import org.zgl.Application;
import org.zgl.GetFileAllInit;
import org.zgl.Test;
import org.zgl.desc.IProxy;
import org.zgl.message.IoMessage;
import org.zgl.message.RequestIoMessage;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @作者： big
 * @创建时间： 2018/6/15
 * @文件描述：
 */
public class RpcHandlerImpl {
    private static RpcHandlerImpl instance;

    public static RpcHandlerImpl getInstance() {
        if (instance == null)
            instance = new RpcHandlerImpl();
        return instance;
    }

    private final Map<String, Class<?>> registMap;

    private RpcHandlerImpl() {
        registMap = new HashMap<>();
        registMap.put(Test.class.getName(), Test.class);
        scan("org.zgl");
    }

    private void scan(String path) {
        List<Class> clazzList = GetFileAllInit.getClasssFromPackage(path);
        if (clazzList.size() <= 0)
            return;
        for (Class c : clazzList) {
            Annotation proxy = c.getAnnotation(IProxy.class);
            if (proxy instanceof IProxy) {
                Class i = c.getInterfaces()[0];
                registMap.put(i.getName(), c);
            }
        }
    }

    public void handler(RequestIoMessage ioMessage) {
        try {
            Class<?> clazz = registMap.get(ioMessage.getInterfaceName());
            if (clazz == null) {

            }
            Method method = null;
            method = clazz.getDeclaredMethod(ioMessage.getMethodName(), ioMessage.getParamsType());
            Object o = clazz.getDeclaredConstructor().newInstance();
            OperationAbstract op = (OperationAbstract) o;
            op.setUid(ioMessage.getUid());
            op.submin(new MyRunnable(method,o,ioMessage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void write(Object o) {
        if (o == null) {
        }
        Application.getCc().asyncWrite((IoMessage) o);//发送这个消息
    }
    private class MyRunnable implements Runnable{
        private Method method;
        private Object obj;
        private RequestIoMessage ioMessage;
        public MyRunnable(Method method, Object obj, RequestIoMessage ioMessage) {
            this.method = method;
            this.obj = obj;
            this.ioMessage = ioMessage;
        }
        @Override
        public void run() {
            try {
                if (method.getReturnType().equals(void.class)) {
                    //不返回
                    method.invoke(obj, ioMessage.getParams());
                } else {
                    Object result = method.invoke(obj, ioMessage.getParams());
                    //返回
                    write(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
