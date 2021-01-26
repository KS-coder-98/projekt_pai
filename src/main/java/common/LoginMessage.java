package common;

import common.Message;

public class LoginMessage extends Message {
    public LoginMessage(String login, String password, String name, String surname, String mail, String controlQuestion, String answerControlQuestion, String searchedPhrase, Sender sender, Status status) {
        super(login, password, name, surname, mail, controlQuestion, answerControlQuestion, searchedPhrase, sender, status);
    }

    @Override
    public void processing() {
        if (getSender() == Sender.Client) {
            System.out.println(getLogin() + " " + getPassword());
        }
    }
}
