package com.chow.arch.concurrent.base.sync005;

/**
 * Created by shelvin chow on 2017/5/7.
 */
public class SyncDubbo2
{
    static class Super
    {
        public int i = 10;

        public synchronized void operationSup()
        {
            i--;
            System.out.println("thread " + Thread.currentThread().getName() + ", Sup print i = " + i);
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    static class Son extends Super
    {
        public synchronized void operationSub()
        {
            while (i > 0)
            {
                i--;
                System.out.println("thread "+Thread.currentThread().getName()+", Son print i = " + i);
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                this.operationSup();
            }
        }
    }

    public static void main(String[] args)
    {
        final Son son = new Son();
        Thread thread1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                son.operationSub();
            }
        },"t1");

        thread1.start();
    }
}
