package com.chow.arch.netty.marshallingserial;

import com.chow.arch.utils.GzipUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shelvin chow on 2017/6/1.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {

    }

    private AtomicInteger index = new AtomicInteger(0);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        Req req = (Req) msg;
        System.out.println("Server get msg from Client:" + req.getId() + ", " + req.getName() + ", " + req.getRequestMessage());

        String path = System.getProperty("user.dir") +
                File.separatorChar+ "receive" +
                File.separatorChar+ "00" +
                index.getAndIncrement()+
                ".jpg";
        FileOutputStream fos = new FileOutputStream(path);

        byte[] attachment = GzipUtils.ungizp(req.getAttachment());
        fos.write(attachment);
        fos.close();

        Resp resp = new Resp();
        resp.setId(req.getId());
        resp.setName("resp "+req.getName());
        resp.setResponseMessage("resp msg " + req.getRequestMessage());
        ctx.writeAndFlush(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
    {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        ctx.close();
    }
}
