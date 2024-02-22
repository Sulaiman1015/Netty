package com.sm.bio;

import java.io.IOException;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws IOException, IOException {
        Socket socket = new Socket("127.0.0.1", 9000);
        //send msg to server
        socket.getOutputStream().write("HelloServer".getBytes());
        socket.getOutputStream().flush();
        System.out.println("send msg to server");
        byte[] bytes = new byte[1024];
        //get return
        socket.getInputStream().read(bytes);
        System.out.println("get msg from server：" + new String(bytes));
        socket.close();
    }
}
