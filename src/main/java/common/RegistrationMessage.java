package common;


import server.AppServer;
import server.model.User;
import server.service.communication.Send;
import settings.Setting;

import java.util.UUID;


public class RegistrationMessage extends Message {


    public RegistrationMessage(String login, String Password, String name, String surname, String mail, String controlQuestion, String answerControlQuestion, String searchedPhrase, Sender sender, Status status) {
        super(login, Password, name, surname, mail, controlQuestion, answerControlQuestion, searchedPhrase, sender, status);
    }

    @Override
    public void processing(Send send) {
        if ( getSender() == Sender.Client ){
            Boolean userPresent = AppServer.list.stream()
                    .anyMatch(user -> user.getLogin().equals(getLogin()));
            RegistrationMessage registrationMessage;
            if ( userPresent ){
                registrationMessage = new RegistrationMessage(null, null, null, null, null, null, null, null, Sender.Server, Status.ERROR_USER_ALREADY_EXIST);
                //todo user already exist
            }else{
                registrationMessage = new RegistrationMessage(null, null, null, null, null, null, null, null, Sender.Server, Status.OK);
                UUID id =  UUID.randomUUID();
                AppServer.list.add(new User(id, getLogin(), getName(), getSurname(), getMail(), getPassword(), getControlQuestion(), getAnswerControlQuestion()));
                send.addMessageToQueue(registrationMessage);
                try {
                    AppServer.userParser.saveDatabase(AppServer.list, Setting.databaseName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            System.out.println(getStatus());
        }
    }
}
