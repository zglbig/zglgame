package org.zgl.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.zgl.message.IoMessage;
import org.zgl.message.RequestIoMessage;
import org.zgl.message.ResultIoMessage;
import org.zgl.message.ServerIoMessage;
import org.zgl.proxy.RpcHandlerImpl;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：
 */
public class CSimpleChannelInboundHandler extends SimpleChannelInboundHandler<IoMessage> {
    private final RpcHandlerImpl rpc = RpcHandlerImpl.getInstance();
    private ServerIoMessage syncResult;
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
        if(o instanceof RequestIoMessage){
            rpc.handler((RequestIoMessage) o);
        }else if(o instanceof ServerIoMessage){
            syncResult = (ServerIoMessage) o;
        }
        //如果是请求
        //如果是应答
    }

    public ServerIoMessage getSyncResult() {
        return syncResult;
    }
}
