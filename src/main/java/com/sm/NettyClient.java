package com.sm;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //Client use Bootstrap Server use ServerBootstrap
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //加入处理器
                            ch.pipeline().addLast((ChannelHandler) new NettyClientHandler());
                        }
                    });

            System.out.println("netty client start。。");

            ChannelFuture cf = bootstrap.connect("127.0.0.1", 9000).sync();

            cf.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
