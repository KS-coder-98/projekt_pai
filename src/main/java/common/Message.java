package common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public abstract class Message implements Serializable {
    private String login;
    private String password;
    // todo

    public abstract void processing();
}
