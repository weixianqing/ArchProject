package com.chow.arch.netty.TcpPackageDeal;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by shelvin chow on 2017/5/31.
 */
public class Client
{
    public static void main(String[] args) throws InterruptedException
    {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>()
                {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception
                    {
                        ByteBuf buf = Unpooled.copiedBuffer("S_".getBytes());
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });

        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8765).sync();
        channelFuture.channel().writeAndFlush("aa$_".getBytes());
        channelFuture.channel().writeAndFlush("bbbb$_".getBytes());
        channelFuture.channel().writeAndFlush("ccccccccc$_".getBytes());

        channelFuture.channel().closeFuture().sync();
        group.shutdownGracefully();
    }
}
