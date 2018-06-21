package org.zgl.session;


/**
 * 作者： 白泽
 * 时间： 2017/12/1.
 * 描述：
 */
public interface ISession {
    /**
     * 会话绑定对象
     * @return
     */
    Object getAttachment();

    /**
     * 绑定对象
     * @return
     */
    void setAttachment(Object attachment);

    /**
     * 移除绑定对象
     * @return
     */
    void removeAttachment();

    /**
     * 向会话中写入消息
     * @param response
     */
    void write(Object response);

    /**
     * 判断会话是否在连接中
     * @return
     */
    boolean isConnected();

    /**
     * 关闭
     * @return
     */
    void close();
}
