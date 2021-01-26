package common;

import common.Message;

public class RemindMessage extends Message {
    public RemindMessage(String login, String name, String surname, String mail, String controlQuestion, String answerControlQuestion, String searchedPhrase, Sender sender, Status status) {
        super(login, name, surname, mail, controlQuestion, answerControlQuestion, searchedPhrase, sender, status);
    }

    @Override
    public void processing() {
        System.out.print("procesing login message");
    }
}
