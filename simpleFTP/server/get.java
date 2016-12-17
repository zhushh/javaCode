/*************************************************************************
 > File Name: get.java
 > Author: zhushh
 > Mail: 
 > Created Time: Sun 18 Dec 2016 12:55:12 AM CST
 ************************************************************************/

package server;

import java.io.*;
import java.net.*;

public class get extends ServerCmd {
    private Socket connectToClient;
    private DataOutputStream sendData;
    private DataInputStream receiveData;

    public String func(Socket s, String[] cmd) {
        connectToClient = s;
        String back = getFileContent("/home/zhushh/code/" + cmd[1]);
        return back;
    }

    public String getFileContent(String filename) {
        String str = filename;
        try {
            sendData = new DataOutputStream(connectToClient.getOutputStream());
            receiveData = new DataInputStream(connectToClient.getInputStream());

            File file = new File(filename);
            InputStream in = new FileInputStream(file);
            int datasize = in.available();
            byte[] data = new byte[datasize];
            in.read(data);

            sendData.writeUTF(""+datasize);
            String response = receiveData.readUTF();
            sendData.write(data, 0, data.length);
            sendData.flush();

            in.close();
            str += " send success!";
        } catch (Exception e) {
            e.printStackTrace();
            str += " send fail!";
        }
        return str;
    }
}

