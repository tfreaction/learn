package demo06tcp4;



import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author : [wswen]
 * @description : [一句话描述该类的功能]
 */
public class ServerReaderRunnable implements Runnable{
    private Socket socket;
    public ServerReaderRunnable(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {

        //读管道的消息
        try {

                //给当前对应的浏览器管道返回一个网页数据
                OutputStream os = socket.getOutputStream();
                //通过字节流包装写出去的数据给浏览器，注意数据格式（HTTP协议的数据格式）
                //把字节输出流包装成打印流
                PrintStream ps = new PrintStream(os);
                //返回给浏览器的数据
                ps.println("HTTP/1.1 200 OK");
                ps.println("Content-Type:text/html;charset=utf-8");
                ps.println();//必须换行，否则浏览器不解析
                ps.println("<html>");
                ps.println("<head>");
                ps.println("<meta charset='UTF-8'>");
                ps.println("<title>这是网页的标题</title>");
                ps.println("</head>");
                ps.println("<body>");
                ps.println("<h1 style= 'color:blue;font-size=20px'>这是网页的内容</h1>");
                ps.println("</body>");
                ps.println("</html>");
                ps.close();
                socket.close();
        } catch (Exception e) {
            System.out.println("客户端退出了..." + socket.getInetAddress().getHostAddress());
        }
    }
    }

