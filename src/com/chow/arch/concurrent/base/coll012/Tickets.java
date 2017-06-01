package com.chow.arch.concurrent.base.coll012;

import java.util.*;

/**
 * Created by shelvin chow on 2017/5/17.
 */
public class Tickets
{
    public static void main(String[] args)
    {
        final Vector<String> tickets = new Vector<String>();

        for (int i = 0; i < 1000; i++)
        {
            tickets.add("train ticket " + i);
        }

//        Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());

        for (int i = 0; i < 10; i++)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    while (true)
                    {
                        if (tickets.isEmpty())
                        {
                            break;
                        }
                        System.out.println(Thread.currentThread().getName()+"----"+tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
