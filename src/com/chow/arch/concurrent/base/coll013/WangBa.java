package com.chow.arch.concurrent.base.coll013;

import java.util.concurrent.DelayQueue;

/**
 * Created by shelvin chow on 2017/5/18.
 */
public class WangBa implements Runnable
{
    private DelayQueue<WangMin> delayQueue = new DelayQueue();
    private boolean yinye = true;

    public void shangji(String name, String id, int money)
    {
        WangMin wangMin = new WangMin(name, id, 1000 * money + System.currentTimeMillis());
        System.out.println(wangMin.getName() + ", id is " + wangMin.getId() + ", money " + money + ", begin to login.");
        this.delayQueue.add(wangMin);
    }

    public void xiaji(WangMin wangMin)
    {
        System.out.println(wangMin.getName() + ", id is " + wangMin.getId() + ", timeout, logout.");
    }

    @Override
    public void run()
    {
        while (yinye)
        {
            try
            {
                WangMin wangMin = delayQueue.take();
                xiaji(wangMin);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        System.out.println("wangba begins.");
        WangBa siyu = new WangBa();
        Thread thread = new Thread(siyu);
        thread.start();

        siyu.shangji("jia", "123", 2);
        siyu.shangji("yi", "234", 5);
        siyu.shangji("bing", "456", 10);
    }
}
