package com.chow.arch.netty.marshallingserial;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by shelvin chow on 2017/6/1.
 */
public class Client
{
    public static void main(String[] args) throws InterruptedException
    {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap client = new Bootstrap();

        client.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>()
                {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception
                    {
                        ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });

        ChannelFuture channelFuture = client.connect("127.0.0.1",8765).sync();

        for (int i = 0; i < 5; i++)
        {
            Req req = new Req();
            req.setId("" + i);
            req.setName("pro" + i);
            req.setRequestMessage("data msg " + i);
            channelFuture.channel().writeAndFlush(req);
        }
        channelFuture.channel().closeFuture().sync();
        group.shutdownGracefully();
    }
}
