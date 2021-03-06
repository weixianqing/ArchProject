package com.chow.arch.communication.fakeaio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by shelvin chow on 2017/5/23.
 */
public class Client
{
    final static int PORT = 8765;
    final static String IP = "127.0.0.1";

    public static void main(String[] args)
    {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try
        {
            socket = new Socket(IP, PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("received message from client");
            String response = in.readLine();
            System.out.println("client:" + response);
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
