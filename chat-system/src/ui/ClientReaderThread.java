package ui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author : [wswen]
 * @description : [线程类，准备读取服务端的消息]
 */
public class ClientReaderThread extends Thread{
    private Socket socket;
    public ClientReaderThread(Socket socket, ClientChatFrame win) {
        this.socket = socket;
        this.win = win;
    }
    private DataInputStream dis;
    private ClientChatFrame win;

    @Override
    public void run() {

        //读管道的消息
        try {
            //发1，在线群聊的更新消息，然后发2，群聊的消息
            dis = new DataInputStream(socket.getInputStream());
            while (true) {
                int type = dis.readInt();//读取消息类型编号
                switch (type) {
                    case 1:
                        //接收服务端发来的在线人数更新消息
                        updateClientOnLineList();
                        break;
                    case 2:
                        //接收服务端发来的群聊消息
                        getMsgToWin();
                        break;

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getMsgToWin() throws Exception {
        String s = dis.readUTF();
        //把消息展示在窗口中
        win.setMsgToWin(s);
    }

    private void updateClientOnLineList() throws Exception {
        //1、先读取在线人数有多少个
        int count = dis.readInt();
        //2、再循环读取读取在线人的昵称
        String[] onLineNames = new String[count];//存储在线人的昵称
        for (int i = 0; i < count; i++) {
            String nickName = dis.readUTF();
            onLineNames[i] = nickName;
        }
        //3、再把昵称展示在窗口中的在线人数列表中
        win.updateOnlineUsers(onLineNames);

    }


}

