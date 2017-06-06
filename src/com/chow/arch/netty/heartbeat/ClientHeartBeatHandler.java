package com.chow.arch.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by shelvin chow on 2017/6/5.
 */
public class ClientHeartBeatHandler extends ChannelInboundHandlerAdapter
{
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?> heartBeat;

    private InetAddress address;

    private static final String SUCCESS_KEY = "auth_success_key";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        address = InetAddress.getLocalHost();
        String ip = address.getHostAddress();
        System.out.println(ip);
        String key = "1234";
        String auth = ip + "," + key;
        ctx.writeAndFlush(auth);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        try
        {
            if (msg instanceof String)
            {
                String ret = (String) msg;
                if (SUCCESS_KEY.equals(ret))
                {
                    this.heartBeat = this.scheduler.scheduleWithFixedDelay(new HeartBeatTask(ctx),
                            5, 1, TimeUnit.SECONDS);
                    System.out.println(msg);
                } else
                {
                    System.out.println(msg);
                }
            }
        } finally
        {
            ReferenceCountUtil.release(msg);
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
        if (heartBeat != null)
        {
            heartBeat.cancel(true);
            heartBeat = null;
        }

        ctx.fireExceptionCaught(cause);
    }

    private class HeartBeatTask implements Runnable
    {
        private final ChannelHandlerContext ctx;

        private HeartBeatTask(final ChannelHandlerContext ctx)
        {
            this.ctx = ctx;
        }

        @Override
        public void run()
        {
            RequestInfo requestInfo = new RequestInfo();
            requestInfo.setIp(address.getHostAddress());

            Sigar sigar = new Sigar();
            try
            {
                CpuPerc cpuPerc = sigar.getCpuPerc();
                HashMap<String, Object> cpuPercMap = new HashMap<String, Object>();
                cpuPercMap.put("combined", cpuPerc.getCombined());
                cpuPercMap.put("user", cpuPerc.getUser());
                cpuPercMap.put("sys", cpuPerc.getSys());
                cpuPercMap.put("wait", cpuPerc.getWait());
                cpuPercMap.put("idle", cpuPerc.getIdle());

                Mem mem = sigar.getMem();
                HashMap<String, Object> memoryMap = new HashMap<String, Object>();
                memoryMap.put("total", mem.getTotal() / 1024L);
                memoryMap.put("used", mem.getUsed() / 1024L);
                memoryMap.put("free", mem.getFree() / 1024L);

                requestInfo.setCpuPercMap(cpuPercMap);
                requestInfo.setMemoryMap(memoryMap);

                ctx.writeAndFlush(requestInfo);
            } catch (SigarException e)
            {
                e.printStackTrace();
            }
        }
    }
}