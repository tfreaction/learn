package demo03tcp1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : [wswen]
 * @description : [一句话描述该类的功能]
 */
public class TCPSeverDemo1 {
    public static void main(String[] args) throws Exception {
        // 目标：实现服务端一发一收，实现基本的TCP通信
        System.out.println("服务器启动了...");
        // 1. 创建服务端的ServerSocket对象
        ServerSocket ss = new ServerSocket(8888);
        // 2. 获取客户端的Socket对象
        Socket socket = ss.accept();
        // 3. 获取输入流，接收客户端发送的数据
        InputStream is = socket.getInputStream();
        // 4. 把字节输入流转换为特殊数据输入流
        DataInputStream dis = new DataInputStream(is);
        int id = dis.readInt();
        String s = dis.readUTF();
        System.out.println("id: " + id + ",收到的消息: " + s);
        // 5. 输出客户端和端口号
        System.out.println(socket.getInetAddress().getHostAddress());
        System.out.println(socket.getPort());

    }
}
