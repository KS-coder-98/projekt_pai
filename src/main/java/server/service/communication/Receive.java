package server.service.communication;

import common.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

@Getter
@Setter
public class Receive extends Thread {
    private ObjectInputStream in;
    private Send send;
    List<Message> msgList;


    /**
     * Created object is responsible for receiving ObjectInputStream, and binds specified ObjectInputStream, and binds list for message
     *
     * @param in      It's object stream input  for specific client. This parameter is responsible for receiving metadata message from client
     * @param msgList List which stores necessary metadata message from client
     */
    public Receive(ObjectInputStream in, List<Message> msgList, Send send) {
        this.in = in;
        this.msgList = msgList;
        this.send = send;
    }


    /**
     * The main thread class Receive. It must start to correct work with receiving metadata message
     */
    public void run() {
        Message inputObject;
        try {
            while ((inputObject = (Message) in.readObject()) != null) {
                inputObject.processing(send);
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
