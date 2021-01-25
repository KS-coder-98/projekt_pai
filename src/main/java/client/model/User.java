package client.model;

import client.service.communication.EchoClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private static EchoClient echoClient;

    public static EchoClient getEchoClient() {
        return echoClient;
    }

    public static void setEchoClient(EchoClient echoClient) {
        User.echoClient = echoClient;
    }
}
