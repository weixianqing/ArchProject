package com.chow.arch.concurrent.mid.design014;

/**
 * Created by shelvin chow on 2017/5/19.
 */
public class FutureClient
{
    public Data request(final String qryString)
    {
        final FutureData futureData = new FutureData();

        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                RealData realData = new RealData(qryString);
                futureData.setRealData(realData);
            }
        });

        thread.start();

        return futureData;
    }
}
