package com.chow.arch.netty.marshallingserial;

import com.chow.arch.utils.GzipUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by shelvin chow on 2017/6/1.
 */
public class Client
{
    public static void main(String[] args) throws InterruptedException, IOException
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

        ChannelFuture channelFuture = client.connect("127.0.0.1", 8765).sync();

        for (int i = 0; i < 2; i++)
        {
            Req req = new Req();
            req.setId("" + i);
            req.setName("pro" + i);
            req.setRequestMessage("data msg " + i);


            String readPath = System.getProperty("user.dir") + File.separatorChar
                    + "sources" + File.separatorChar+ "00" + i + ".jpg";
            File file = new File(readPath);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[fis.available()];
            fis.read(data);
            fis.close();
            req.setAttachment(GzipUtils.gzip(data));
            channelFuture.channel().writeAndFlush(req);
        }
        channelFuture.channel().closeFuture().sync();
        group.shutdownGracefully();
    }
}
