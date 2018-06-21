package org.zgl;

import org.zgl.message.IoMessage;
import org.zgl.message.ResponesBaseIoMessage;
import org.zgl.message.ResultIoMessage;
import org.zgl.proxy.OperationAbstract;

import java.util.ArrayList;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：
 */
public class Test extends OperationAbstract {
    public IoMessage test(String s, int i){
        System.out.println(getUid());
        System.out.println(s+" "+i);
        ResultIoMessage resultIoMessage = new ResultIoMessage();
        resultIoMessage.setUid(null);
        resultIoMessage.setResultType(1);
        resultIoMessage.setResult(new ResponesBaseIoMessage());
        return resultIoMessage;
    }
}
