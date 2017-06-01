package com.chow.arch.concurrent.base.sync002;

/**
 * Created by shelvin chow on 2017/5/5.
 */
public class MultiThread
{
    private static int num = 0;
//    private static int num = 0;

    public synchronized void printNum(String tag)
    {
        if ("a".equals(tag))
        {
            num = 100;
            System.out.println("tag a, set num over");
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        } else
        {
            num = 200;
            System.out.println("tag b, set num over");
        }

        System.out.println("tag " + tag + ", num = " + num);
    }

    public static void main(String[] args)
    {
        final MultiThread multiThread1 = new MultiThread();
        final MultiThread multiThread2 = new MultiThread();

        Thread thread1 = new Thread(new Runnable()
        {
            public void run()
            {
                multiThread1.printNum("a");
            }
        });

        Thread thread2 = new Thread(new Runnable()
        {
            public void run()
            {
                multiThread2.printNum("b");
            }
        });

        thread1.start();
        thread2.start();
    }
}
