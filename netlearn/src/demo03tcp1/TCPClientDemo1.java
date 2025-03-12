package demo03tcp1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author : [wswen]
 * @description : [一句话描述该类的功能]
 */
public class TCPClientDemo1 {
    public static void main(String[] args) throws Exception {
        // 目标：实现客户端一发一收，实现基本的TCP通信
        System.out.println("客户端启动了...");
        // 1. 创建客户端的Socket对象
        Socket socket = new Socket("127.0.0.1", 8888);
        // 2. 获取输出流，向服务器发送数据
        OutputStream os = socket.getOutputStream();
        // 3. 创建特殊数据流，方便发送数据
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeInt(1);
        dos.writeUTF("你好");
        // 4. 关闭资源
        socket.close();
    }
}
