package demo05tcp3;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author : [wswen]
 * @description : [一句话描述该类的功能]
 */
public class TCPClientDemo1 {
    public static void main(String[] args) throws Exception {
        // 目标：支持多个客户端开发，客户端多发多收，实现基本的TCP通信
        System.out.println("客户端启动了...");
        // 1. 创建客户端的Socket对象
        Socket socket = new Socket("127.0.0.1", 8888);
        // 2. 获取输出流，向服务器发送数据
        OutputStream os = socket.getOutputStream();
        // 3. 创建特殊数据流，方便发送数据
        DataOutputStream dos = new DataOutputStream(os);
        // 4. 发送数据
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入要发送的数据：");
            String msg = sc.nextLine();
            if ("exit".equals(msg)) {
                System.out.println("客户端退出了...");
                dos.close();
                socket.close();
                break;
            }

            dos.writeUTF(msg);
            // 刷新
            dos.flush();
        }
    }
}
