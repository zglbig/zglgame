package org.zgl.net;

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
public class CMessageToByteEncoder extends MessageToByteEncoder<IoMessage> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, IoMessage o, ByteBuf byteBuf) throws Exception {
        byte[] buf = ProtostuffUtils.serializer(o);
        if(buf == null)
            return;
        byteBuf.writeInt(-777888);
        if(o instanceof RequestIoMessage){
            byteBuf.writeShort(MutualEnum.SERVER_TO_SERVER_REQUEST.id());//请求别的服务器
        }else if(o instanceof ResultIoMessage){
            byteBuf.writeShort(MutualEnum.SERVER_TO_CLIENT.id());//返回客户端消息
        }else if(o instanceof RegistIoMessage){
            byteBuf.writeShort(MutualEnum.REGIST.id());//注册链接
        }
        byteBuf.writeShort(buf.length);
        byteBuf.writeBytes(buf);
    }

}
