/*************************************************************************
 > File Name: ServerThread.java
 > Author: zhushh
 > Mail: 
 > Created Time: Sat 17 Dec 2016 02:05:12 PM CST
 ************************************************************************/

package server;

import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
    private Socket connectToClient;

    public ServerThread(Socket client) {
        connectToClient = client;
        System.out.println("Receive connect from " + client.getInetAddress().getHostAddress() + ":" + client.getPort());
    }

    public void run() {
        while (!connectToClient.isClosed()) {
            try {
                DataInputStream receiveData = new DataInputStream(connectToClient.getInputStream());
                String str = receiveData.readUTF();
                String[] cmd = str.split(" ");
                System.out.println("Receive request: " + str);
                try {
                    ServerCmd cm = (ServerCmd)Class.forName("server."+cmd[0]).newInstance();
                    String result = cm.func(connectToClient, cmd);
                    // System.out.println("Excute result:\n" + result);
                } catch (InstantiationException e) {
                    System.out.println("Instantiation!");
                } catch (IllegalAccessException e) {
                    System.out.println("Illegal access!");
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                socketClosing();
                break;
            }
        }
    }

    public void socketClosing() {
        try {
            connectToClient.close();
        } catch (Exception e) {
            System.out.println("close socket fail!");
        }
    }
}

