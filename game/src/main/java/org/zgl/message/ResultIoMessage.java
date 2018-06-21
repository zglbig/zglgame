package org.zgl.message;

import java.util.List;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：最终返回客户端的数据
 */
public class ResultIoMessage implements IoMessage {
    private List<Integer> uid;
    private int resultType;//返回类型 1,基础数据类型 或者 2,PB类型
    private IoMessage result;

    public List<Integer> getUid() {
        return uid;
    }

    public void setUid(List<Integer> uid) {
        this.uid = uid;
    }

    public int getResultType() {
        return resultType;
    }

    public void setResultType(int resultType) {
        this.resultType = resultType;
    }

    public IoMessage getResult() {
        return result;
    }

    public void setResult(IoMessage result) {
        this.result = result;
    }
}
