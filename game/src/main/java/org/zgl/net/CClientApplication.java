package org.zgl.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.zgl.message.IoMessage;
import org.zgl.message.RegistIoMessage;
import org.zgl.message.ServerIoMessage;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：
 */
public class CClientApplication {
    private static Channel ctx;
    private final CSimpleChannelInboundHandler cs = new CSimpleChannelInboundHandler();
    public void run(){
        final Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .option(ChannelOption.TCP_NODELAY,true)//通过NoDelay禁用Nagle,使消息立即发出去，不用等待到一定的数据量才发出去
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new IdleStateHandler(0, 0, 5));
                            ch.pipeline().addLast(new CByteToMessageDecoder());
                            ch.pipeline().addLast(new CMessageToByteEncoder());
                            ch.pipeline().addLast(cs);
                        }
                    });
            //注册到网关
            ChannelFuture future = bootstrap.connect("",3030).sync();
            if(future.isSuccess()){
                ctx = future.channel();
                asyncWrite(new RegistIoMessage(-7));//注册链接到网关
                System.out.println("注册网关");
//                LoggerUtils.getPlatformLog().warn("-------------------->>RPC客户端启动成功ip<<"+host+">>：端口<<"+port+">><<---------------");
            }
        } catch (InterruptedException e) {
//            LoggerUtils.getPlatformLog().error("--------------->>网络冲断异常<<-----------------",e);
        }
    }
    public void asyncWrite(IoMessage ioMessage){
        ctx.writeAndFlush(ioMessage);
    }
    public ServerIoMessage syncWrite(IoMessage ioMessage){
        try {
            ChannelFuture future = ctx.writeAndFlush(ioMessage);
            future.await(3000);
            if(future.isSuccess()){
                return cs.getSyncResult();
            }
            Throwable cause = future.cause();
            if(cause != null){

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
