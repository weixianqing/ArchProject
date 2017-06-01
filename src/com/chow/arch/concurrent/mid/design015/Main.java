package com.chow.arch.concurrent.mid.design015;

import java.util.Random;

/**
 * Created by shelvin chow on 2017/5/21.
 */
public class Main
{
    public static void main(String[] args)
    {
        Master master = new Master(new Worker(), 20);
        Random random = new Random();

        for (int i = 0; i < 100; i++)
        {
            Task task = new Task();
            task.setId(i);
            task.setPrice(random.nextInt(1000));
            master.submit(task);
        }

        master.execute();

        long start = System.currentTimeMillis();

        while (true)
        {
            if (master.isComplete())
            {
                long end = System.currentTimeMillis() - start;
                int result = master.getReault();
                System.out.println("final result is " + result + ", time is " + end);
                break;
            }
        }
    }
}
