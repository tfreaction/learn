import com.sun.nio.sctp.SendFailedNotification;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.zip.DataFormatException;

/**
 * @author : [wswen]
 * @description : [线程类，准备读取客户端的消息]
 */
public class ServerReaderThread extends Thread{
    private Socket socket;
    public ServerReaderThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {

        //读管道的消息
        try {
            //接登录的消息、昵称、群聊的消息、私聊的消息
            //接受的消息类型很多，所以需要规定协议，规定消息的格式
            //客户端先发1，服务端接收登陆的消息，然后客户端发2，服务端接收群聊的消息
            //先从socket管道中接受，消息类型编号。再从socket管道中接受消息的内容
            //所以需要将socket管道中传来的消息包装成特殊数据输入流
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            while (true) {
                int type = dis.readInt();//读取消息类型编号
                switch (type) {
                    case 1:
                        //接收登录的消息，接下来要接受昵称数据，再更新全部客户端的在线人数列表
                        String nickName = dis.readUTF();
                        //将昵称和socket对象存储到集合中
                        Server.onLineSockets.put(socket, nickName);
                        //将所有的在线人数列表发送给所有的客户端
                        updateClientOnLineList();

                        break;
                    case 2:
                        //接收群聊的消息，接下来要接受消息内容，再将消息发送给所有的客户端
                        String msg = dis.readUTF();
                        //将消息发送给所有的客户端
                        sendMsgToAll(msg);

                        break;
                    case 3:
                        //接收私聊的消息，接下来要接受消息内容，再将消息发送给指定的客户端

                        break;
                }
            }

        } catch (Exception e) {
            System.out.println("客户端退出了..." + socket.getInetAddress().getHostAddress());
            //将客户端从集合中移除
            Server.onLineSockets.remove(socket);
            //将所有的在线人数列表发送给所有的客户端
            updateClientOnLineList();
        }
    }

    private void sendMsgToAll(String msg) {
        //将消息发送给所有在线的客户端
        //将消息的来源（昵称）、时间、消息内容拼装好发送给所有的客户端
        //利用字节输出流拼装消息
        StringBuilder sb = new StringBuilder();
        String name = Server.onLineSockets.get(socket);

        //获取当前标准时间
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss EEE");
        String time = dtf.format(now);

        StringBuilder msgResult = sb.append(name).append(" ").append(time).append("\r\n").append(msg).append("\r\n");

        //遍历所有的socket对象，将消息发送给所有的客户端
        for (Socket socket : Server.onLineSockets.keySet()) {
            try {
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeInt(2);//消息类型编号，1代表在线人数列表，2代表群聊消息，3代表私聊消息
                dos.writeUTF(msgResult.toString());
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void updateClientOnLineList() {
        //将所有的在线人数列表发送给所有的客户端
        //获取所有的在线名称，并将所有的在线名称发送给所有在线的socket管道
        //获取所有的在线名称
        Collection<String> onLineUsers = Server.onLineSockets.values();
        //将这个集合中的所有用户发送给所有的客户端，需要遍历所有的socket对象
        for (Socket socket : Server.onLineSockets.keySet()) {
            try {
                //将所有的在线名称发送给所有的客户端
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeInt(1);//消息类型编号，1代表在线人数列表，2代表群聊消息，3代表私聊消息
                dos.writeInt(onLineUsers.size());//在线人数
                for (String onLineUser : onLineUsers) {
                    dos.writeUTF(onLineUser);
                }
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

