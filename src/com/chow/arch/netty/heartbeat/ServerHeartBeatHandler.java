package com.chow.arch.netty.heartbeat;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.HashMap;

/**
 * Created by shelvin chow on 2017/6/5.
 */
public class ServerHeartBeatHandler extends ChannelInboundHandlerAdapter
{
    private static HashMap<String, String> AUTH_IP_MAP = new HashMap<String, String>();
    private static final String SUCCESS_KEY = "auth_success_key";

    static
    {
        AUTH_IP_MAP.put("172.18.2.163", "1234");
    }

    private boolean auth(ChannelHandlerContext ctx, Object msg)
    {
        String[] ret = ((String) msg).split(",");
        String auth = AUTH_IP_MAP.get(ret[0]);

        if (auth != null && auth.equals(ret[1]))
        {
            ctx.writeAndFlush(SUCCESS_KEY);
            return true;
        } else
        {
            ctx.writeAndFlush("auth failed").addListener(ChannelFutureListener.CLOSE);
            return false;
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        if (msg instanceof String)
        {
            auth(ctx, msg);
        } else if (msg instanceof RequestInfo)
        {
            RequestInfo requestInfo = (RequestInfo) msg;

            System.out.println("--------------------------------");


            System.out.println("current host ip:" + requestInfo.getIp());
            System.out.println("current host cpu:");
            HashMap<String, Object> cpu = requestInfo.getCpuPercMap();
            System.out.println("total usage percent:" + cpu.get("combined"));
            System.out.println("user usage percent:" + cpu.get("user"));
            System.out.println("system usage percent:" + cpu.get("sys"));
            System.out.println("wait percent:" + cpu.get("wait"));
            System.out.println("idle percent:" + cpu.get("idle"));

            System.out.println("current host memory:");
            HashMap<String, Object> memory = requestInfo.getMemoryMap();
            System.out.println("total memory:" + memory.get("total"));
            System.out.println("used memory:" + memory.get("used"));
            System.out.println("free memory" + memory.get("free"));

            ctx.writeAndFlush("info received");
        }else
        {
            ctx.writeAndFlush("connection failed").addListener(ChannelFutureListener.CLOSE);
        }
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
