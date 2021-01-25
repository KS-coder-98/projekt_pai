package common;


public class RegisterMessage extends Message{


    public RegisterMessage(String login, String password) {
        super(login, password);
    }

    @Override
    public void processing() {
        System.out.printf("procesing register message");
    }
}
