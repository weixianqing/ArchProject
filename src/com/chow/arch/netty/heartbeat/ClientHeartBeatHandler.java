package com.chow.arch.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetAddress;

/**
 * Created by shelvin chow on 2017/6/5.
 */
public class ClientHeartBeatHandler extends ChannelInboundHandlerAdapter
{
    private InetAddress address;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
    {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        super.exceptionCaught(ctx, cause);
    }
}
