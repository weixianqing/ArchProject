package com.chow.arch.communication.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by shelvin chow on 2017/5/23.
 */
public class Server
{
    public static int PORT = 8756;

    public static void main(String[] args)
    {
        ServerSocket server = null;

        try
        {
            server = new ServerSocket(PORT);
            System.out.println("server start...");
            Socket socket = server.accept();
            new Thread(new ServerHandler(socket)).start();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (server != null)
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
