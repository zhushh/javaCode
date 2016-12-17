/*************************************************************************
 > File Name: put.java
 > Author: zhushh
 > Mail: 
 > Created Time: Sun 18 Dec 2016 02:30:24 AM CST
 ************************************************************************/

package server;

import java.net.*;
import java.io.*;

public class put extends ServerCmd {
    private Socket connectToClient;
    private DataOutputStream sendData;
    private DataInputStream receiveData;

    public String func(Socket s, String[] cmd) {
        connectToClient = s;
        String back = putFile("/home/zhushh/code/" + cmd[1]);
        return back;
    }

    public String putFile(String filename) {
        String str = "Put " + filename;
        try {
            sendData = new DataOutputStream(connectToClient.getOutputStream());
            receiveData = new DataInputStream(connectToClient.getInputStream());

            System.out.println("Start upload ...");

            FileOutputStream out = new FileOutputStream(filename);
            sendData.writeUTF("Datasize");

            // System.out.println("Send datasize success.");

            int datasize = Integer.parseInt(receiveData.readUTF());
            byte[] data = new byte[datasize];

            // System.out.println("Get datasize: " + datasize);

            sendData.writeUTF("Start upload");
            receiveData.read(data, 0, datasize);
            sendData.writeUTF("Finish!");
            out.write(data);

            System.out.println("finish!!");

            out.close();
            str += " success!";
        } catch (Exception e) {
            e.printStackTrace();
            str += " fail!";
        }
        return str;
    }
}

