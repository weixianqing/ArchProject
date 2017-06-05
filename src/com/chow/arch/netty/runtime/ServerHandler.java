package com.chow.arch.netty.runtime;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by shelvin chow on 2017/6/1.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        Request req = (Request) msg;
        System.out.println("Server get msg from Client:" + req.getId() + ", " + req.getName() + ", " + req.getRequestMessage());

        Response resp = new Response();
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
