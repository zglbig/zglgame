package org.zgl.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.zgl.handle.IoMessageReceive;
import org.zgl.message.*;
import org.zgl.session.ISession;
import org.zgl.session.SessionImpl;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：
 */
public class SSimpleChannelInboundHandler extends SimpleChannelInboundHandler<GateIoMessage> {
    private IoMessageReceive receive =  IoMessageReceive.getInstance();
    private ISession session = null;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        session = new SessionImpl(ctx.channel());
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
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GateIoMessage o) throws Exception {
        receive.recieveMessage(session,o);
        //获取相关服务器链接
        //发送数据
    }
}
