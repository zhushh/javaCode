/*************************************************************************
 > File Name: SimpleFTPClient.java
 > Author: zhushh
 > Mail: 
 > Created Time: Fri 16 Dec 2016 11:40:50 PM CST
 ************************************************************************/

package client;

import java.net.*;
import java.util.Scanner;

public class SimpleFTPClient {
    public static void main(String[] args) {
        System.out.println("Welcome to use FTP client");
        while (true) {
            System.out.print("ftp> ");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] cmd = input.split(" ");
            if (cmd[0].equalsIgnoreCase("open")) {
                try {
                    InetAddress addr = InetAddress.getByName(new String(cmd[1]));
                    int num = Integer.parseInt(cmd[2]);
                    ClientThread ct = new ClientThread();
                    if (ct.testConnection(addr, num)) {
                        break;
                    }
                    // break;
                } catch (UnknownHostException e) {
                    System.out.println("500 can not found the host");
                } catch (Exception e) {
                    System.out.println("500 incorrect command!");
                    continue;
                }
            } else if (cmd[0].equalsIgnoreCase("exit")) {
                break;
            } else {
                System.out.println("you should open the host first!");
                System.out.println("open [host address] [port]");
            }
        }
    }
}

