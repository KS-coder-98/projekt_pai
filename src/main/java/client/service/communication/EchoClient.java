package client.service.communication;

import common.LoginMessage;
import common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EchoClient {
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Send send;
    private List<Message> msgList;
    private List<Message> msgListReceive;

    public void startConnection(String ip, int port) {
        msgList = Collections.synchronizedList(new ArrayList<>());
        msgListReceive = Collections.synchronizedList(new ArrayList<>());
        try {
            clientSocket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            //todo this add message to queue
//            msgList.add(new LoginMessage("test", "password"));
            send = new Send(out, msgList);
            send.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in = new ObjectInputStream(clientSocket.getInputStream());
            new Receive(in).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
