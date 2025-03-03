package demo02udp2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * @author : [wswen]
 * @description : [一句话描述该类的功能]
 */
public class UDPClientDemo1 {

    public static void main(String[] args) throws Exception {
        System.out.println("===客户端启动了===");
        //目标：完成UDP通信多发多收，客户端开发
//        1.创建发送端对象
        DatagramSocket socket = new DatagramSocket();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入要发送的数据：");
            String msg = sc.nextLine();//可以避免空格的影响

            //如果用户输入exit，就退出
            if ("exit".equals(msg)) {
                System.out.println("===客户端退出===");
                socket.close();
                break;
            }
            //2.创建数据包对象封装要发送的数据。
            byte[] bytes = msg.getBytes();
            /**
             * 参数1：发送的数据，字节数组
             * 参数2：发送的字节长度。
             * 参数3：目的地的IP地址。
             * 参数4：服务端程序的端口号。
             */
            DatagramPacket packet = new DatagramPacket(bytes,
                    bytes.length, InetAddress.getLocalHost(), 8080);

//        3.让发送端对象发送准备好的数据
            socket.send(packet);
        }
    }
}
