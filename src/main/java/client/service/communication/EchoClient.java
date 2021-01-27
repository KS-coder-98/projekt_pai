package client.service.communication;

import common.LoginMessage;
import common.Message;
import lombok.Getter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class EchoClient extends Thread{
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Send send;
    private List<Message> msgList;

    public void startConnection(String ip, int port) {
        msgList = Collections.synchronizedList(new ArrayList<>());
        try {
            clientSocket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in = new ObjectInputStream(clientSocket.getInputStream());
            new Receive(in).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            send = new Send(out, msgList);
            send.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
