package com.chow.arch.communication.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by shelvin chow on 2017/5/23.
 */
public class ServerHandler implements Runnable
{
    private Socket socket;

    public ServerHandler(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        BufferedReader in = null;
        PrintWriter out = null;

        try
        {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String body = null;
            while (true)
            {
                body = in.readLine();
                if (body == null)
                {
                    break;
                }
                System.out.println("server:" + body);
                out.println("server gives back data.");
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            if (out != null)
            {
                out.close();
            }

            if (socket != null)
            {
                try
                {
                    socket.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
