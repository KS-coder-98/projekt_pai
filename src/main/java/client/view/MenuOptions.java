package client.view;

import client.AppClient;
import common.Message;

import java.util.HashMap;
import java.util.Map;

public enum MenuOptions {
    LOGIN(1) {
        @Override
        public void makeAction(Message message) {
            AppClient.client.getSend().addMessageToQueue(message);
        };
    }, REGISTER(2){
        @Override
        public void makeAction(Message message) {
            AppClient.client.getSend().addMessageToQueue(message);
        };
    }, CHANGE_PASSWORD(3){
        @Override
        public void makeAction(Message message) {
        };
    }, REMIND_PASSWORD(4){
        @Override
        public void makeAction(Message message) {
            AppClient.client.getSend().addMessageToQueue(message);
        };
    },LOG_OUT(5){
        //todo popraw to
        @Override
        public void makeAction(Message message) {
            AppClient.client.getSend().addMessageToQueue(message);
        };
    };


    private final int value;
    private static final Map map = new HashMap<>();

    private MenuOptions(int value) {
        this.value = value;
    }

    static {
        for (MenuOptions pageType : MenuOptions.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static MenuOptions valueOf(int pageType) {
        return (MenuOptions) map.get(pageType);
    }

    public int getValue() {
        return value;
    }

    public abstract void makeAction(Message message);
}
