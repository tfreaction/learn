package demo06tcp4;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author : [wswen]
 * @description : [一句话描述该类的功能]
 */
public class TCPSeverDemo {
    public static void main(String[] args) throws Exception {
        // 目标：BS架构的原理理解
        System.out.println("服务器启动了...");
        // 1. 创建服务端的ServerSocket对象
        ServerSocket ss = new ServerSocket(8080);//建议使用8080端口

        // 创建线程池
        ExecutorService pool = new ThreadPoolExecutor(3,10,10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        while (true) {
            // 2. 获取客户端的Socket对象
            Socket socket = ss.accept();
            // 3. 把客户端管道包装成一个任务，交给线程池处理
            pool.execute(new ServerReaderRunnable(socket));
        }
    }
}
