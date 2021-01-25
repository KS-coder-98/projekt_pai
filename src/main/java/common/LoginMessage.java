package common;

public class LoginMessage extends Message{
    public LoginMessage(String login, String password) {
        super(login, password);
    }

    @Override
    public void processing() {
        System.out.printf("procesing login message");
    }
}
