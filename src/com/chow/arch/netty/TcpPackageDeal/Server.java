package com.chow.arch.netty.TcpPackageDeal;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by shelvin chow on 2017/5/31.
 */
public class Server
{
    public static void main(String[] args) throws InterruptedException
    {
        EventLoopGroup pGroup = new NioEventLoopGroup();
        EventLoopGroup cGroup = new NioEventLoopGroup();

        ServerBootstrap server = new ServerBootstrap();

        server.group(pGroup, cGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                .childHandler(new ChannelInitializer<SocketChannel>()
                {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception
                    {
                        ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new ServerHandler());
                    }
                });

        ChannelFuture channelFuture = server.bind(8765).sync();

        channelFuture.channel().closeFuture().sync();
        pGroup.shutdownGracefully();
        cGroup.shutdownGracefully();
    }
}
