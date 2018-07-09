package org.zgl.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.zgl.message.IoMessage;
import org.zgl.message.ServerCommunicationIoMessage;
import org.zgl.proxy.TcpProxyInboundHandler;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：
 */
public class CSimpleChannelInboundHandler extends SimpleChannelInboundHandler<IoMessage> {
    private final TcpProxyInboundHandler rpc = TcpProxyInboundHandler.getInstance();
    private ServerCommunicationIoMessage syncResult;
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
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, IoMessage o) throws Exception {
        //这里就要做动态代理机制处理
        //如果是请求
        //如果是应答
    }

    public ServerCommunicationIoMessage getSyncResult() {
        return syncResult;
    }
}
