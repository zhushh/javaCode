/*************************************************************************
 > File Name: put.java
 > Author: zhushh
 > Mail: 
 > Created Time: Sun 18 Dec 2016 02:39:43 AM CST
 ************************************************************************/

package client;

import java.io.*;
import java.net.*;

public class put extends ClientCmd {
    private static Socket connectToServer;
    private static DataOutputStream sendData;
    private static DataInputStream receiveData;

    public String func(Socket s, String[] cmd) {
        connectToServer = s;
        String str = cmd[1];
        try {
            receiveData = new DataInputStream(connectToServer.getInputStream());
            sendData = new DataOutputStream(connectToServer.getOutputStream());

            InputStream in = new FileInputStream(new File(cmd[1]));
            int datasize = in.available();
            byte[] data = new byte[datasize];
            in.read(data);

            sendData.writeUTF(cmd[0] + " " + cmd[1]);

            System.out.println("Start upload ...");

            String response;
            response = receiveData.readUTF();
            
            // System.out.println(response);

            sendData.writeUTF(""+datasize);
            response = receiveData.readUTF();

            // System.out.println(response);
            
            sendData.write(data, 0, data.length);
            sendData.flush();
            response = receiveData.readUTF();
            
            System.out.println(response);

            in.close();
            str += " upload success!";
        } catch (Exception e) {
            e.printStackTrace();
            str += " upload fail!";
        }
        return str;
    }
}

