package com.chow.arch.concurrent.base.sync007;

/**
 * Created by shelvin chow on 2017/5/7.
 */
public class VolatileDemo extends Thread
{
    private volatile boolean isRunning = true;

    public void setRunning(boolean isRunning)
    {
        this.isRunning = isRunning;
    }

    @Override
    public void run()
    {
        System.out.println("enter run method");
        while (isRunning)
        {

        }
        System.out.println("exit run method");
    }

    public static void main(String[] args) throws InterruptedException
    {
        VolatileDemo volatileDemo = new VolatileDemo();
        volatileDemo.start();
        Thread.sleep(3000);
        volatileDemo.setRunning(false);
        System.out.println("isRunning is set to false");
        Thread.sleep(1000);
        System.out.println(volatileDemo.isRunning
        );
    }
}
