package common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import server.service.communication.Send;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public abstract class Message implements Serializable {

    private String login;
    private String Password;
    private String name;
    private String surname;
    private String mail;
    private String controlQuestion;
    private String answerControlQuestion;
    private String searchedPhrase;
    private Sender sender;
    private Status status;
    private String newPassword;
    private List<String> bookLists;
    //todo lista ksiazek

    public abstract void processing(Send send);
}
