package com.chow.arch.concurrent.base.sync006;

/**
 * Created by shelvin chow on 2017/5/11.
 */
public class ModifyLock
{
    private String name;
    private int age;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public synchronized void changeAttribute(String name, int age)
    {
        System.out.println("current thread " + Thread.currentThread().getName() + " begins.");
        this.setAge(age);
        this.setName(name);
        System.out.println("current thread " + Thread.currentThread().getName() + " modifies object: " + this.getName() + "," + this.getAge());
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("current thread " + Thread.currentThread().getName() + " ends.");
    }

    public static void main(String[] args)
    {
        final ModifyLock modifyLock = new ModifyLock();

        Thread thread1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                modifyLock.changeAttribute("zhang san", 19);
            }
        }, "t1");

        Thread thread2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                modifyLock.changeAttribute("li si", 20);
            }
        }, "t2");

        thread1.start();
        try
        {
            Thread.sleep(200);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        thread2.start();
    }
}
