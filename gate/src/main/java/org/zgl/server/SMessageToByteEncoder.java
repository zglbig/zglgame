package org.zgl.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.zgl.MutualEnum;
import org.zgl.ProtostuffUtils;
import org.zgl.message.*;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：
 */
public class SMessageToByteEncoder extends MessageToByteEncoder<IoMessage> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, IoMessage o, ByteBuf byteBuf) throws Exception {
        //服务器间交互
        //返回客户端消息
        if(o == null){

        }
        byteBuf.writeInt(-777888);
//        if(o instanceof GateIoMessage){
//            byteBuf.writeShort(MutualEnum.SERVER_TO_CLIENT_PB.id());
//        }
        byte[] buf = ProtostuffUtils.serializer(o);
        byteBuf.writeShort(buf.length);
        byteBuf.writeBytes(buf);
    }
    /**
     * 包头
     * 数据源
     * 数据长度
     * 数据
     *  服务器地址
     *  接口名
     *  方法名
     *  参数类型
     *  参数
     */
}
