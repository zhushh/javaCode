/*************************************************************************
 > File Name: ClientCmd.java
 > Author: zhushh
 > Mail: 
 > Created Time: Fri 16 Dec 2016 11:26:28 PM CST
 ************************************************************************/

package client;

import java.net.Socket;

public abstract class ClientCmd {
    public abstract String func(Socket s, String[] cmd);
}

