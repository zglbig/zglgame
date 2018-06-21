package org.zgl.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：网关服务端启动类
 */
public class SServerApplication {
    public void run(){
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup work = new NioEventLoopGroup();
        //配置服务器端的nio
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,100)
                    .option(ChannelOption.TCP_NODELAY,true)//通过NoDelay禁用Nagle,使消息立即发出去，不用等待到一定的数据量才发出去
                    .childOption(ChannelOption.SO_KEEPALIVE, false)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new SMessageToByteEncoder());
                            ch.pipeline().addLast(new SByteToMessageDecoder());
                            ch.pipeline().addLast(new IdleStateHandler(60,60,60, TimeUnit.SECONDS));
                            ch.pipeline().addLast(new SSimpleChannelInboundHandler());
                        }
                    });
            //绑定端口
            ChannelFuture f = b.bind(3030).sync();
            if(f.isSuccess()){
                System.out.println("启动成功");
//                LoggerUtils.getPlatformLog().warn("----------------->>房间服务器启动成功<<------------------");
//                LoggerUtils.getPlatformLog().warn("---------------->><<"+7788+">><<--端口绑定------------------");
            }
            f.channel().closeFuture().sync();//等待服务端监听关闭
        }catch (Exception e){
//            LoggerUtils.getPlatformLog().error("--------------->>服务器启动失败<<------------------",e);
        }finally {
            //优雅退出线程
            boss.shutdownGracefully();
            work.shutdownGracefully();
//            LoggerUtils.getPlatformLog().warn("---------------服务器关闭------------------");
        }

    }
}
