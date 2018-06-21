package org.zgl.handle;
import org.zgl.RoomEnum;
import org.zgl.message.*;
import org.zgl.session.ISession;
import org.zgl.session.SessionEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：
 */
public class IoMessageReceive {
    private static IoMessageReceive instance;
    public static IoMessageReceive getInstance() {
        if(instance == null)
            instance = new IoMessageReceive();
        return instance;
    }
    private final Map<Integer,ISession> sessionMap;
    private IoMessageReceive(){
        sessionMap = new ConcurrentHashMap<>();
        //初始化
    }

    /**
     * 这里只处理客户端发来的消息
     * @param ioMessage
     */
    public void clientMessageRecieve(CIoMessage ioMessage){
        ISession cc = sessionMap.getOrDefault(ioMessage.getAddress(),null);
        if(cc == null){
            return;
            //没有找到src对应的逻辑服务器
        }
        cc.write(ioMessage);//将请求发到对应逻辑服务器
    }

    /**
     * 接受到逻辑服务器返回的最终消息发往客户端
     */
    public void clienResponesRecieve(ResultIoMessage ioMessage){
        if(ioMessage == null){
            //数据异常
        }
        //通知这些人
        for(int uid : ioMessage.getUid()) {
            ISession session = sessionMap.getOrDefault(uid, null);
            if (session == null || !session.isConnected()) {
                //链接已经清理关闭
            } else {
                session.write(ioMessage.getResult());
            }
        }
    }
    /**
     * 服务器之间相互请求
     */
    public void gameServerRecieve(CIoMessage ioMessage){
        ISession session = sessionMap.getOrDefault(ioMessage.getAddress(),null);
        if(session == null){

        }
        session.write(ioMessage);
    }
    /**
     * 服务器之间相互响应
     */
    public void gameServerRespones(ServerIoMessage ioMessage){
        ISession session = sessionMap.getOrDefault(ioMessage.getAddress(),null);
        if(session == null){

        }
        session.write(ioMessage);
    }
    public void registSession(ISession session,int uid){
        //用户链接进来
        sessionMap.put(uid,session);
        SessionEntity s = new SessionEntity(uid,System.currentTimeMillis());
        List<Integer> roomIds = new ArrayList<>();
        roomIds.add(RoomEnum.GATE.getId());
        s.setRoomIds(roomIds);
        session.setAttachment(s);//绑定消息
    }
}
