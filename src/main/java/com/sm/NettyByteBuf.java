package com.sm;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class NettyByteBuf {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.buffer(1);
        System.out.println("byteBuf=" + byteBuf);

        for (int i = 0; i < 8; i++) {
            byteBuf.writeByte(i);
        }
        System.out.println("byteBuf=" + byteBuf);

        for (int i = 0; i < 5; i++) {
            System.out.println(byteBuf.getByte(i));
        }
        System.out.println("byteBuf=" + byteBuf);

        for (int i = 0; i < 5; i++) {
            System.out.println(byteBuf.readByte());
        }
        System.out.println("byteBuf=" + byteBuf);


        //Unpooled create ByteBuf
        ByteBuf byteBuf2 = Unpooled.copiedBuffer("hello,zhuge!", CharsetUtil.UTF_8);
        if (byteBuf2.hasArray()) {
            byte[] content = byteBuf2.array();
            //将 content 转成字符串
            System.out.println(new String(content, CharsetUtil.UTF_8));
            System.out.println("byteBuf2=" + byteBuf2);

            System.out.println(byteBuf2.getByte(0)); // 获取数组0这个位置的字符h的ascii码，h=104

            int len = byteBuf2.readableBytes(); //可读的字节数  12
            System.out.println("len=" + len);

            //get all byte using for loop
            for (int i = 0; i < len; i++) {
                System.out.println((char) byteBuf2.getByte(i));
            }

            System.out.println(byteBuf2.getCharSequence(0, 6, CharsetUtil.UTF_8));
            System.out.println(byteBuf2.getCharSequence(6, 6, CharsetUtil.UTF_8));
        }
    }
}
