package com.chow.arch.concurrent.base.sync004;

/**
 * Created by shelvin chow on 2017/5/6.
 */
public class DirtyRead
{
    private String username = "shelvin";
    private String password = "1234";

    public synchronized void setValue(String username, String password)
    {
        this.username = username;
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        this.password = password;
        System.out.println("set value final result: username = "+ username+", password = "+password);
    }

    public void getValue()
    {
        System.out.println("get value final result: username = " + username + ", password = " + password);
    }

    public static void main(String[] args) throws InterruptedException
    {
        final DirtyRead dirtyRead = new DirtyRead();
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                dirtyRead.setValue("chow", "4321");
            }
        });

        thread.start();
        Thread.sleep(1000);
        dirtyRead.getValue();
    }
}
