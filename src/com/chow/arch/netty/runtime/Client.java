package com.chow.arch.netty.runtime;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by shelvin chow on 2017/6/4.
 */
public class Client
{
    private static class SingletonHolder
    {
        static final Client instance = new Client();
    }

    public static Client getInstance()
    {
        return SingletonHolder.instance;
    }

    private EventLoopGroup group;
    private Bootstrap bootstrap;
    private ChannelFuture channelFuture;

    private Client()
    {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .handler(new ChannelInitializer<SocketChannel>()
                {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception
                    {
                        ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                        ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new ReadTimeoutHandler(5));
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });
    }

    public void connect()
    {
        try
        {
            this.channelFuture = bootstrap.connect("127.0.0.1", 8765).sync();
            System.out.println("client has connected to remote server, ready to communicate.");
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public ChannelFuture getChannelFuture()
    {
        if (this.channelFuture == null)
        {
            this.connect();
        }

        if (!this.channelFuture.channel().isActive())
        {
            this.connect();
        }

        return this.channelFuture;
    }

    public static void main(String[] args) throws InterruptedException
    {
        final Client client = Client.getInstance();
        ChannelFuture channelFuture = client.getChannelFuture();

        for (int i = 1; i <= 3; i++)
        {
            Request request = new Request();
            request.setId("" + i);
            request.setName("pro" + i);
            request.setRequestMessage("msg" + i);
            channelFuture.channel().writeAndFlush(request);
            TimeUnit.SECONDS.sleep(4);
        }

        channelFuture.channel().closeFuture().sync();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("enter sub thread...");
                ChannelFuture channelFuture = client.getChannelFuture();
                System.out.println(channelFuture.channel().isActive());
                System.out.println(channelFuture.channel().isOpen());

                Request request = new Request();
                request.setId("" + 4);
                request.setName("pro" + 4);
                request.setRequestMessage("msg" + 4);

                channelFuture.channel().writeAndFlush(request);
                try
                {
                    channelFuture.channel().closeFuture().sync();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println("sub thread ends.");
            }
        }).start();
    }
}