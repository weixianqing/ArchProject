package com.chow.arch.concurrent.mid.design014;

/**
 * Created by shelvin chow on 2017/5/19.
 */
public class Main
{
    public static void main(String[] args)
    {
        FutureClient futureClient = new FutureClient();
        Data data = futureClient.request("query para");
        System.out.println("send query");
        System.out.println("do other work");
        String res = data.getRequest();
        System.out.println(res);
    }
}
