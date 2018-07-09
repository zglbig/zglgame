package org.zgl.handle;
import org.zgl.JsonUtils;
import org.zgl.MutualEnum;
import org.zgl.ProtostuffUtils;
import org.zgl.RoomEnum;
import org.zgl.message.ClientRequsetIoMessage;
import org.zgl.message.GateIoMessage;
import org.zgl.message.ServerCommunicationIoMessage;
import org.zgl.register.MethodModel;
import org.zgl.register.Register;
import org.zgl.register.ServiceInterfaceModel;
import org.zgl.session.ISession;
import org.zgl.session.SessionEntity;
import org.zgl.session.SessionManager;
import java.util.ArrayList;
import java.util.List;
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
    public void recieveMessage(ISession session, GateIoMessage ioMessage){
            MutualEnum mutualEnum = MutualEnum.getMutualEnum(ioMessage.getCmd());
            if(mutualEnum == null){

            }
            //这里以后只有客户端请求服务器的时候才反序列化 减少反序列化次数
            ISession game = SessionManager.getSession(ioMessage.getGameId());
            switch (mutualEnum) {
                case CLIENT_TO_SERVER:
                    //客户端请求服务器
                    ioMessage = exchangeIoMessage(session,ioMessage.getCmd(),ioMessage.getGameId(), ioMessage.getMessage());
                    game.write(ioMessage);
                    break;
                case SERVER_TO_SERVER:
                    //服务器之间通讯
                    ioMessage = new GateIoMessage(ioMessage.getCmd(),ioMessage.getGameId(),ioMessage.getMessage());
                    game.write(ioMessage);
                    break;
                case SERVER_TO_CLIENT:
                    //服务器返送数据都客户端
                    ServerCommunicationIoMessage s = ProtostuffUtils.deserializer(ioMessage.getMessage(),ServerCommunicationIoMessage.class);
                    ClientRequsetIoMessage c = new ClientRequsetIoMessage();
                    c.setInterfaceCode(s.getInterfaceCode());
                    c.setMethodCode(s.getMethodCode());
                    List<String> params = new ArrayList<>();
                    //解析参数回传
                    for(int i = 0;i<s.getArgs().length;i++){
                        if(isPrimit(s.getParamsType()[i])){
                            params.add(s.getArgs()[i]+"");
                        }else {
                            params.add(JsonUtils.jsonSerialize(s.getArgs()[i]));
                        }
                    }
                    c.setArgs(params);
                    for(long uid : s.getUids()){
                        ISession user = SessionManager.getSession(uid);
                        if(user != null && user.isConnected()){
                            user.write(c);
                        }
                    }
                    break;
                case REGIST:
                    //注册链接
                    ClientRequsetIoMessage registerMsg = ProtostuffUtils.deserializer(ioMessage.getMessage(), ClientRequsetIoMessage.class);
                    List<String> args = registerMsg.getArgs();
                    if(args.size() != 1)
                        throw new RuntimeException("注册链接的uid参数异常");
                    long uid = 0;
                    try {
                        uid = Long.parseLong(args.get(0));
                    }catch (NumberFormatException e){
                        e.printStackTrace();
                        throw new RuntimeException("注册链接的uid解析异常");
                    }
                    registSession(session,uid);
                    break;
            }
    }
    private GateIoMessage exchangeIoMessage(ISession session,short cmd, short gameId, byte[] buf) {
        ClientRequsetIoMessage ioMessage = ProtostuffUtils.deserializer(buf, ClientRequsetIoMessage.class);
        ServerCommunicationIoMessage server = new ServerCommunicationIoMessage();
        try {
            //这里需要将各个服务器提供的接口服务注册到gate服中
            ServiceInterfaceModel sim = Register.getServiceInterfaceModel(gameId, ioMessage.getInterfaceCode());
            MethodModel methodModel = sim.getMethodModel(ioMessage.getMethodCode());
            SessionEntity sessionEntity = (SessionEntity)session.getAttachment();
            if(sessionEntity == null || sessionEntity.getUid()== 0){
                //链接尚未注册
                throw new RuntimeException("链接尚未注册"+session.channel().remoteAddress());
            }
            List<Long> uids = new ArrayList<>();
            uids.add(sessionEntity.getUid());
            server.setUids(uids);//这里根据链接来取
            server.setInterfaceName(sim.getInterfaceName());
            server.setMethodName(methodModel.getMethodName());
            server.setInterfaceCode(ioMessage.getInterfaceCode());
            server.setMethodCode(ioMessage.getMethodCode());
            Class<?>[] parameterTypes = methodModel.getParamentTypes();
            if (parameterTypes.length != ioMessage.getArgs().size())
                throw new NullPointerException("参数不正确");
            server.setParamsType(parameterTypes);
            Object[] params = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                params[i] = exchangeParams(ioMessage.getArgs().get(i), parameterTypes[i]);
            }
            server.setArgs(params);
            //转换参数
            byte[] buffer = ProtostuffUtils.serializer(server);
            return new GateIoMessage(cmd,gameId, buffer);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("数据异常");
        }
    }
    private boolean isPrimit(Class<?> clazz){
        if (clazz.equals(int.class) || clazz.equals(Integer.class)) {
            return true;
        } else if (clazz.equals(short.class) || clazz.equals(Short.class))
            return true;
        else if (clazz.equals(byte.class) || clazz.equals(Byte.class))
            return true;
        else if (clazz.equals(boolean.class) || clazz.equals(Boolean.class))
            return true;
        else if (clazz.equals(long.class) || clazz.equals(Long.class))
            return true;
        else if (clazz.equals(float.class) || clazz.equals(Float.class))
            return true;
        else if (clazz.equals(double.class) || clazz.equals(Double.class))
            return true;
        else if (clazz.equals(String.class))
            return true;
        else {
            return false;
        }
    }
    private Object exchangeParams(String params, Class<?> clazz) {
        if (clazz.equals(int.class) || clazz.equals(Integer.class)) {
            return Integer.parseInt(params);
        } else if (clazz.equals(short.class) || clazz.equals(Short.class))
            return Short.parseShort(params);
        else if (clazz.equals(byte.class) || clazz.equals(Byte.class))
            return Byte.parseByte(params);
        else if (clazz.equals(boolean.class) || clazz.equals(Boolean.class))
            return Boolean.parseBoolean(params);
        else if (clazz.equals(long.class) || clazz.equals(Long.class))
            return Long.parseLong(params);
        else if (clazz.equals(float.class) || clazz.equals(Float.class))
            return Float.parseFloat(params);
        else if (clazz.equals(double.class) || clazz.equals(Double.class))
            return Double.parseDouble(params);
        else if (clazz.equals(String.class))
            return params;
        else {
            throw new NullPointerException("没有:" + clazz.getName() + "->这个参数类型");
        }
    }
    private void registSession(ISession session,long uid){
        //用户注册链接
        SessionEntity s = new SessionEntity(uid,System.currentTimeMillis());
        List<Integer> roomIds = new ArrayList<>();
        roomIds.add(RoomEnum.GATE.getId());
        s.setGameIds(roomIds);
        session.setAttachment(s);//绑定消息
    }
    //登陆 退出
}
