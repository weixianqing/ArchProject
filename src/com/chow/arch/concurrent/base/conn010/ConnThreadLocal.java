package com.chow.arch.concurrent.base.conn010;

/**
 * Created by shelvin chow on 2017/5/11.
 */
public class ConnThreadLocal
{
    public static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public void getThreadLocal()
    {
        System.out.println(Thread.currentThread().getName()+":" + threadLocal.get());
    }

    public void setThreadLocal(String value)
    {
        threadLocal.set(value);
    }

    public static void main(String[] args)
    {
        final ConnThreadLocal connThreadLocal = new ConnThreadLocal();

        Thread thread1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                connThreadLocal.setThreadLocal("zhang san");
                connThreadLocal.getThreadLocal();
            }
        }, "t1");

        Thread thread2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                connThreadLocal.getThreadLocal();
            }
        }, "t2");

        thread1.start();
        thread2.start();
    }
}
