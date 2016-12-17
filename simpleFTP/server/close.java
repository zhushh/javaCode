/*************************************************************************
 > File Name: close.java
 > Author: zhushh
 > Mail: 
 > Created Time: Sat 17 Dec 2016 05:21:37 PM CST
 ************************************************************************/

package server;

import java.io.*;
import java.net.Socket;

public class close extends ServerCmd {
    Socket s;
    private DataOutputStream sendData;
    private DataInputStream receiveData;

    public String func(Socket s, String[] cmd) {
        this.s = s;
        String back = close();
        return back;
    }

    public String close() {
        String str = "socket close success!";
        try {
            sendData = new DataOutputStream(s.getOutputStream());
            sendData.writeUTF("200 command complete!");
            try {
                s.close();
            } catch (Exception e) {
                str = "socket close fail!";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}

