/*************************************************************************
 > File Name: get.java
 > Author: zhushh
 > Mail: 
 > Created Time: Sun 18 Dec 2016 12:59:44 AM CST
 ************************************************************************/

package client;

import java.io.*;
import java.net.*;

public class get extends ClientCmd {
    private static Socket connectToServer;
    private static DataOutputStream sendData;
    private static DataInputStream receiveData;

    public String func(Socket s, String[] cmd) {
        connectToServer = s;
        String str = cmd[1];
        try {
            receiveData = new DataInputStream(connectToServer.getInputStream());
            sendData = new DataOutputStream(connectToServer.getOutputStream());

            FileOutputStream out = new FileOutputStream(cmd[1]);
            sendData.writeUTF(cmd[0] + " " + cmd[1]);
            int datasize = Integer.parseInt(receiveData.readUTF());
            byte[] data = new byte[datasize];
            sendData.writeUTF("OK");
            receiveData.read(data, 0, datasize);
            out.write(data);

            out.close();
            str += " download success!";
        } catch (Exception e) {
            e.printStackTrace();
            str += " download fail!";
        }
        return str;
    }
}

