/*************************************************************************
 > File Name: close.java
 > Author: zhushh
 > Mail: 
 > Created Time: Sat 17 Dec 2016 05:21:30 PM CST
 ************************************************************************/

package client;

import java.io.*;
import java.net.Socket;

public class close extends ClientCmd {
    private static Socket connectToServer;
    private static DataOutputStream sendData;
    private static DataInputStream receiveData;

    public String func(Socket s, String[] cmd) {
        connectToServer = s;
        String str = null;
        try {
            receiveData = new DataInputStream(connectToServer.getInputStream());
            sendData = new DataOutputStream(connectToServer.getOutputStream());
            sendData.writeUTF(cmd[0]);
            str = receiveData.readUTF();
            try {
                connectToServer.close();
            } catch (Exception e) {
                System.out.println("Cmd: " + cmd[0]);
                System.out.println("close socket fail!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}

