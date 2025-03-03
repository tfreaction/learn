package demo01udp1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author : [wswen]
 * @description : [一句话描述该类的功能]
 */
public class UDPSeverDemo2 {
    public static void main(String[] args) throws Exception {
        System.out.println("===服务器启动了===");
        //目标：完成UDP通信一发一收，服务端开发
        //1.创建服务端对象，注册端口
        DatagramSocket socket = new DatagramSocket(8080);

        //2.创建数据包对象，接收数据
        byte[] bytes = new byte[1024 * 64];
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length);

        //3.让服务端对象接收数据,将数据封装到数据包对象的字节数组中
        socket.receive(packet);

        //4.看数据包是否收到
        int len = packet.getLength();
        String data = new String(bytes, 0 ,len);
        System.out.println("服务器收到了" + data);

        //5.获取对方的ip地址
        String ip = packet.getAddress().getHostAddress();
        int port = packet.getPort();
        System.out.println("对方的ip地址是：" + ip + "，端口号是：" + port);
    }

}
