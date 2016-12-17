/*************************************************************************
 > File Name: ClientThread.java
 > Author: zhushh
 > Mail: 
 > Created Time: Fri 16 Dec 2016 11:28:53 PM CST
 *************************************************************************/

package client;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ClientThread extends Thread {
    private static Socket connectToServer;
    private Thread thread;

    public boolean testConnection(InetAddress add, int port) {
        try {
            connectToServer = new Socket(add, port);
            System.out.println("200 connect to " 
                + connectToServer.getInetAddress() + " server success!");
        } catch (IOException e) {
            System.out.println("connection incorrect!");
            return false;
        }
        thread = new Thread(this);
        thread.start();
        return true;
    }

    public void run() {
        while (!connectToServer.isClosed()) {
            try {
                System.out.print("ftp> ");
                Scanner i = new Scanner(System.in);
                String temp = i.nextLine();
                String[] cmd = temp.split(" ");
                try {
                    ClientCmd cm = (ClientCmd)Class.forName("client."+cmd[0]).newInstance();
                    String str = cm.func(connectToServer, cmd);
                    System.out.println(str);
                } catch (InstantiationException e) {
                    System.out.println("Instantiation!");
                } catch (IllegalAccessException e) {
                    System.out.println("Illegal access!");
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found!");
                }
            } catch (Exception e) {
                socketClosing();
                break;
            }
        }
    }

    public static void socketClosing() {
        try {
            connectToServer.close();
        } catch (Exception e) {
            System.out.println("close socket fail!");
        }
    }
}

