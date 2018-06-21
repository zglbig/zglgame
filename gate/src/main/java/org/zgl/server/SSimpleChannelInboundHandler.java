package org.zgl.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.zgl.MutualEnum;
import org.zgl.handle.IoMessageReceive;
import org.zgl.message.*;
import org.zgl.session.ISession;
import org.zgl.session.SessionEntity;
import org.zgl.session.SessionImpl;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：
 */
public class SSimpleChannelInboundHandler extends SimpleChannelInboundHandler<IoMessagePackage> {
    private IoMessageReceive receive =  IoMessageReceive.getInstance();
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, IoMessagePackage o) throws Exception {
        MutualEnum mutualEnum = MutualEnum.getMutualEnum(o.getAddressId());
        switch (mutualEnum){
            case SERVER_TO_CLIENT:
                //数据返回客户端
                receive.clienResponesRecieve((ResultIoMessage) o.getIoMessage());
                break;
            case SERVER_TO_SERVER_REQUEST:
                //服务器之间请求
                receive.gameServerRecieve((CIoMessage) o.getIoMessage());
                break;
            case SERVER_TO_SERVER_RESPONES:
                //服务器之间响应
                receive.gameServerRespones((ServerIoMessage) o.getIoMessage());
                break;
            case CLIENT_TO_SERVER:
                ISession session = new SessionImpl(channelHandlerContext.channel());//重session中获取
                SessionEntity entity = (SessionEntity) session.getAttachment();
                int uid =0;
                if(entity != null){
                    uid = entity.getUid();
                    entity.setLastEditTime(System.currentTimeMillis());
                }
                //客户端发来的消息
                CIoMessage ioMessage = (CIoMessage) SIoMessageHandler.sIoMessageRead(uid, (SIoMessage) o.getIoMessage());
                receive.clientMessageRecieve(ioMessage);
                break;
            case REGIST:
                //注册链接
                ISession session1 = new SessionImpl(channelHandlerContext.channel());//重session中获取
                RegistIoMessage registIoMessage = (RegistIoMessage) o.getIoMessage();
                receive.registSession(session1,registIoMessage.getUid());
                break;
        }
    }
}
