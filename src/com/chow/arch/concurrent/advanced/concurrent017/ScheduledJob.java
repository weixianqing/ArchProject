package com.chow.arch.concurrent.advanced.concurrent017;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by shelvin chow on 2017/5/22.
 */
public class ScheduledJob
{
    public static void main(String[] args)
    {
        Temp temp = new Temp();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        ScheduledFuture scheduledFuture = scheduler.scheduleWithFixedDelay(temp, 5, 1, TimeUnit.SECONDS);
    }
}

class Temp extends Thread
{
    @Override
    public void run()
    {
        System.out.println("run...");
    }
}
