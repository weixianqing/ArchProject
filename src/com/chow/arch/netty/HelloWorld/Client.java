package com.chow.arch.netty.HelloWorld;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by shelvin chow on 2017/5/28.
 */
public class Client
{
    public static void main(String[] args) throws InterruptedException
    {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>()
        {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception
            {
                ch.pipeline().addLast(new ClientHandler());
            }
        });

        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8765).sync();

        System.out.println("Client start ...");

        channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer("hello,netty".getBytes()));
        Thread.sleep(1000);
        channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer("hello,netty".getBytes()));
        Thread.sleep(1000);
        channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer("hello,netty".getBytes()));

        channelFuture.channel().closeFuture().sync();

        group.shutdownGracefully();
    }
}
