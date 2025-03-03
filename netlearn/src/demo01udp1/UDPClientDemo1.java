package demo01udp1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author : [wswen]
 * @description : [一句话描述该类的功能]
 */
public class UDPClientDemo1 {
    public static void main(String[] args) throws Exception {
        System.out.println("===客户端启动了===");
        //目标：完成UDP通信一发一收，客户端开发
//        1.创建发送端对象
        DatagramSocket socket = new DatagramSocket();
        //2.创建数据包对象封装要发送的数据。
        byte[] bytes = "我爱学习，我能学习一整天，我越学越有劲。".getBytes();
        /**
         * 参数1：发送的数据，字节数组
         * 参数2：发送的字节长度。
         * 参数3：目的地的IP地址。
         * 参数4：服务端程序的端口号。
         */
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 8080);

//        3.让发送端对象发送准备好的数据
        socket.send(packet);
    }
}
