package demo05tcp3;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author : [wswen]
 * @description : [一句话描述该类的功能]
 */
public class ServerReader extends Thread{
    private Socket socket;
    public ServerReader(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {

        //读管道的消息
        try {
            while (true) {

                    // 3. 获取输入流，接收客户端发送的数据
                    InputStream is = socket.getInputStream();
                    // 4. 把字节输入流转换为特殊数据输入流
                    DataInputStream dis = new DataInputStream(is);

                    String s = dis.readUTF();
                    System.out.println("收到的消息: " + s);
                    // 5. 输出客户端和端口号
                    System.out.println(socket.getInetAddress().getHostAddress());
                    System.out.println(socket.getPort());


                }
        } catch (Exception e) {
            System.out.println("客户端退出了..." + socket.getInetAddress().getHostAddress());
        }
    }
    }

