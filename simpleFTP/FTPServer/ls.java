/*************************************************************************
 > File Name: ls.java
 > Author: zhushh
 > Mail: 
 > Created Time: Sat 17 Dec 2016 12:18:50 AM CST
 ************************************************************************/

package FTPServer;

import java.io.*;
import java.net.Socket;

public class ls extends ServerCmd {
    Socket s;
    private DataOutputStream sendData;
    private DataInputStream receiveData;

    public String func(Socket s, String[] cmd) {
        this.s = s;
        String back = out("/home/zhushh/code");
        return back;
    }

    public String out(String path) {
        String str = "";
        try {
            sendData = new DataOutputStream(s.getOutputStream());
            File file = new File(path);
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    str += files[i].getName() + "\n";
                }
                // System.out.println(str);
            }
            sendData.writeUTF(str+"200 command complete!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}

