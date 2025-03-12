package demo06tcp4;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : [wswen]
 * @description : [一句话描述该类的功能]
 */
public class TCPSeverDemo1 {
    public static void main(String[] args) throws Exception {
        // 目标：通过多线程实现多个和多个客户端通信，服务端多发多收，实现基本的TCP通信
        System.out.println("服务器启动了...");
        // 1. 创建服务端的ServerSocket对象
        ServerSocket ss = new ServerSocket(8888);
        while (true) {
            // 2. 获取客户端的Socket对象
            Socket socket = ss.accept();
            // 3. 创建线程对象
            ServerReader serverReader = new ServerReader(socket);
            // 4. 启动线程
            serverReader.start();
            System.out.println("客户端连接了..." + socket.getInetAddress().getHostAddress());
        }


    }
}
