package server.service.communication;

import common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class implements handle for a client and it is extended by Thread interface. This class stores data about client
 */
public class EchoClientHandler extends Thread {
    private Receive receive;
    private Send send;
    private List<Message> msgList;
    private List<Message> msgListReceive;

    /**
     * Created the handle client and it is bound with client with specified socket for metadata and specified socket for the transfer
     * files
     *
     * @param socket socket is responsible for exchanging metadata between the client and the server
     */
    public EchoClientHandler(Socket socket) {
        try {
            msgList = Collections.synchronizedList(new ArrayList<>());
            msgListReceive = Collections.synchronizedList(new ArrayList<>());
            send = new Send(new ObjectOutputStream(socket.getOutputStream()), msgList);
            receive = new Receive(new ObjectInputStream(socket.getInputStream()), msgListReceive, send);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function turns on main thread responsible for sending and receiving data in relation between server-client
     */
    @Override
    public void run() {
        receive.start();
        send.start();
    }
}
