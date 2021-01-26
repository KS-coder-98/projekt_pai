package common;


import common.Message;


public class RegistrationMessage extends Message {


    public RegistrationMessage(String login, String Password, String name, String surname, String mail, String controlQuestion, String answerControlQuestion, String searchedPhrase, Sender sender, Status status) {
        super(login, Password, name, surname, mail, controlQuestion, answerControlQuestion, searchedPhrase, sender, status);
    }

    @Override
    public void processing() {
        System.out.printf("procesing register message");
    }
}
