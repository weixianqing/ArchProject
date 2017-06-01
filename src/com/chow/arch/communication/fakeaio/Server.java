package com.chow.arch.communication.fakeaio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by shelvin chow on 2017/5/23.
 */
public class Server
{
    final static int PORT = 8765;

    public static void main(String[] args)
    {
        ServerSocket server = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try
        {
            server = new ServerSocket(PORT);
            System.out.println("server start...");
            Socket socket = null;
            HandlerExecutorPool handlerExecutorPool = new HandlerExecutorPool(10, 1000);
            while (true)
            {
                socket = server.accept();
                handlerExecutorPool.execute(new ServerHandler(socket));
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            if (in!=null)
            {
                try
                {
                    in.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            if (out!=null)
            {
                out.close();
            }

            if (server!=null)
            {
                try
                {
                    server.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
