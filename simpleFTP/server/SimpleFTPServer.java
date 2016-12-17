/*************************************************************************
 > File Name: SimpleFTPServer.java
 > Author: zhushh
 > Mail: 
 > Created Time: Sat 17 Dec 2016 02:19:50 PM CST
 ************************************************************************/

package server;

import java.net.*;
import java.io.*;
import java.util.*;

public class SimpleFTPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket((args.length==0)?20:Integer.parseInt(args[0]));
        System.out.println("FTP server is ready ...");
        while (true) {
            new ServerThread(server.accept()).start();
        }
    }
}
