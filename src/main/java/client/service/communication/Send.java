package client.service.communication;

import common.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class Send extends Thread{
    private ObjectOutputStream out;
    List<Message> msgList;

    /**
     * Create object with capabilities to sending object
     *
     * @param out object represents stream to sending serialisation object
     * @param msgList list is implemented as queue with message to send.
     *
     */
    public Send(ObjectOutputStream out, List<Message> msgList) {
        this.out = out;
        this.msgList = msgList;
    }

    /**
     * Function send message to the right client
     *
     * @param msg message with metadata which is sending
     */
    public void sendMessage(Message msg) {
        try {
            out.writeObject(msg);
            out.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * The main thread in function send. This function reads message from list and sends to client.
     * After sending message the message is being deleted from the list
     */
    public void run(){
        while (true){
            if ( !msgList.isEmpty() ){
                var msg = msgList.get(0);
                sendMessage(msg);
                msgList.remove(0);
            }else{
//                User.setEventName("");
                try {
                    Thread.sleep(6000);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
            }

        }
    }

    public void addMessageToQueue(Message msg){
        msgList.add(msg);
    }
}
