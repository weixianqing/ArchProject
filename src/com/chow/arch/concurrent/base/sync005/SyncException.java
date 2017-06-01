package com.chow.arch.concurrent.base.sync005;

/**
 * Created by shelvin chow on 2017/5/7.
 */
public class SyncException
{
    private int i = 0;

    public synchronized void operation()
    {
        while (true)
        {
            try
            {
                i++;
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName() + ", i = " + i);
                if (10 == i)
                {
                    Integer.parseInt("a");
                }
            } catch (Exception e)
            {
                e.printStackTrace();
                System.out.println("log info i = " + i);
                continue;
            }
        }
    }

    public static void main(String[] args)
    {
        final SyncException syncException = new SyncException();
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                syncException.operation();
            }
        });
        thread.start();
    }
}
