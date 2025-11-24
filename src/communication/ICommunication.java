package communication;

import java.io.IOException;
import java.util.ArrayList;

public interface ICommunication {

    void connect(String host, int port) throws Exception;
    void disconnect() throws IOException;
    void listen();
    public void sendMessage(String msg);
    void addMessageListener(IChatObserver listener);
    void notify(String msg);
}
