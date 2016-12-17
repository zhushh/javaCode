/*************************************************************************
 > File Name: ServerCmd.java
 > Author: zhushh
 > Mail: 
 > Created Time: Sat 17 Dec 2016 12:36:04 AM CST
 ************************************************************************/

package server;

import java.net.Socket;

public abstract class ServerCmd {
    public abstract String func(Socket s, String[] cmd);
}

