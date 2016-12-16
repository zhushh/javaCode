/*************************************************************************
 > File Name: HttpThread.java
 > Author: zhushh
 > Mail: 
 > Created Time: Fri 16 Dec 2016 04:53:40 PM CST
 ************************************************************************/

import java.net.*;
import java.io.*;
import java.util.*;

public class HttpThread extends Thread {
    Socket socket;
    HttpThread(Socket s) {
        socket = s;
    }

    public void run() {
        try {
            // open connection input and output stream
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "GBK"));
            OutputStream out = socket.getOutputStream();
            // read request
            String request = in.readLine();
            System.out.println("Receive request: " + request);
            // Deal with Get method
            StringTokenizer st = new StringTokenizer(request);
            if ((st.countTokens() >= 2) && st.nextToken().equals("GET")) {
                String filename = st.nextToken();
                if (filename.startsWith("/")) filename = filename.substring(1);
                if (filename.endsWith("/")) filename += "index.html";
                if (filename.equals("")) filename += "index.html";
                try {
                    // read file data and write to client
                    InputStream file = new FileInputStream(filename);
                    byte[] data = new byte[file.available()];
                    file.read(data);
                    out.write(data);
                    out.flush();
                } catch (FileNotFoundException ex) {
                    PrintWriter pout = new PrintWriter(new OutputStreamWriter(out, "GBK"), true);
                    pout.println("Error code 404: not found!");
                }
            } else {
                PrintWriter pout = new PrintWriter(new OutputStreamWriter(out, "GBK"), true);
                pout.println("Error code 404: wrong request!");
            }
            socket.close();
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex);
        }
    }
}

