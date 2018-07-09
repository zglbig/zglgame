package org.zgl.register;

import java.util.Map;

/**
 * @作者： big
 * @创建时间： 2018/6/30
 * @文件描述：注册中心
 */
public class Register {
    /**所有服务器对应的接口*/
    private static Map<Short,RegisterModel> registerMap;
    public static void putAll(Short gameId,RegisterModel model){
        registerMap.put(gameId,model);
    }
    public static Map<Short,RegisterModel> getMap(){
        return registerMap;
    }
    public static RegisterModel getRegisterModel(short gameId){
        RegisterModel r = registerMap.get(gameId);
        if(r == null){
            throw new RuntimeException("没有："+gameId+"：这个服务器码对应的服务器");
        }
        return r;
    }

    /**
     * 获取某个服务器某个接口
     * @param gameId 服务器id
     * @param interfaceCode 接口码
     * @return
     */
    public static ServiceInterfaceModel getServiceInterfaceModel(short gameId,short interfaceCode){
        return getRegisterModel(gameId).getServiceInterfaceModel(interfaceCode);
    }

    /**
     *
     * @param gameId 服务器id
     * @param interfaceCode 接口码
     * @param methodCode 方法码
     * @return
     */
    public static MethodModel getMethodModel(short gameId,short interfaceCode,short methodCode){
        ServiceInterfaceModel s = getServiceInterfaceModel(gameId,interfaceCode);
        return s.getMethodModel(methodCode);
    }
}
