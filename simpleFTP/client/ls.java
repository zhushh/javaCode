/*************************************************************************
 > File Name: ls.java
 > Author: zhushh
 > Mail: 
 > Created Time: Fri 16 Dec 2016 11:53:46 PM CST
 ************************************************************************/

package client;

import java.io.*;
import java.net.Socket;

public class ls extends ClientCmd {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}

