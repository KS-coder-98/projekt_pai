package client.service.communication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserEcho {
    private static EchoClient echoClient;

    public static EchoClient getEchoClient() {
        return echoClient;
    }

    public static void setEchoClient(EchoClient echoClient) {
        UserEcho.echoClient = echoClient;
    }
}
