package org.zgl.register;

import java.util.Map;

/**
 * @作者： big
 * @创建时间： 2018/6/30
 * @文件描述：单个服务器所有的接口
 */
public class RegisterModel {
    Map<Short,ServiceInterfaceModel> map;//接口码对应的所有接口

    public Map<Short, ServiceInterfaceModel> getMap() {
        return map;
    }
    public void setMap(Map<Short, ServiceInterfaceModel> map) {
        this.map = map;
    }
    public ServiceInterfaceModel getServiceInterfaceModel(short interfaceCode){
        ServiceInterfaceModel s = map.get(interfaceCode);
        if(s == null)
            throw new NullPointerException("没有:"+interfaceCode+"-->这个接口吗对应的服务类");
        return s;
    }
}
