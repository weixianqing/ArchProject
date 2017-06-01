package com.chow.arch.concurrent.base.conn011;

/**
 * Created by shelvin chow on 2017/5/11.
 */
public class Singleton
{
    private static class InnerSingleton
    {
        private static Singleton singleton = new Singleton();
    }

    public static Singleton getInstance()
    {
        return InnerSingleton.singleton;
    }
}
