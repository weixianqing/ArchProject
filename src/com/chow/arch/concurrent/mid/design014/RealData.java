package com.chow.arch.concurrent.mid.design014;

/**
 * Created by shelvin chow on 2017/5/19.
 */
public class RealData implements Data
{
    private String result;

    public RealData(String qryString)
    {
        System.out.println("query result based on qryString, and this is a long time process.");
        try
        {
            Thread.sleep(5000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("process finish, and result returned.");
        result = "result";
    }

    @Override
    public String getRequest()
    {
        return result;
    }
}
