package com.sm;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        StringBuffer sb = new StringBuffer();
        for(int i = 0 ;i < 1023;i ++){
            sb.append("a");
        }
        sb.append("中");
        sb.append("bbbb");
        String sbString = sb.toString();
        byte[] midbytes = sbString.getBytes(StandardCharsets.UTF_8);

        System.out.println("midbytes   = " + midbytes.length);
        ByteBuf buf = Unpooled.copiedBuffer("", CharsetUtil.UTF_8);
        buf.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(buf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(" Get server msg：  " + buf.toString(CharsetUtil.UTF_8));
        System.out.println("Sever address：" + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
