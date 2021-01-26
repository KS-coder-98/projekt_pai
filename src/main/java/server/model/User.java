package server.model;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private UUID id;
    private String login;
    private String name;
    private String surname;
    private String mail;
    private String password;
    private String controlQuestion;
    private String answerControlQuestion;
}
