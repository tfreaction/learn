import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : [wswen]
 * @description : [服务端启动类]
 */
public class Server {
    //定义一个集合容器，存放所有的客户端Socket对象
    //定义一个MAp集合，他的键存储客户端的socket对象，值存储客户端的昵称
    public static final Map<Socket, String> onLineSockets = new HashMap<>();
    public static void main(String[] args) {
        System.out.println("服务器启动了...");
        // 1. 注册端口
        try {
            ServerSocket serverSocket = new ServerSocket(Constant.PORT);
            // 2. 循环监听客户端的连接
            while (true) {
                //3.调用accept方法，获取客户端的Socket对象
                System.out.println("等待客户端连接...");

                Socket socket = serverSocket.accept();
                new ServerReaderThread(socket).start();// 调用thread类的start方法

                System.out.println("一个客户端成功连接...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
