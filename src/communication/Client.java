package src.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client implements ICommunication{
    Socket host = null;
    ArrayList<IChatObserver> listeners = new ArrayList<>();
    BufferedReader in = null;
    PrintWriter out = null;
    ListenThread listenThread = null;
    public Client(String hostName, int port,IChatObserver chatObserver) throws Exception {
        listeners.add(chatObserver);
        connect(hostName, port);
        listen();
    }
    @Override
    public void connect(String hostName, int port) throws Exception {
         host = new Socket(hostName, port);
        System.out.println("connected.");
         in = new BufferedReader(new InputStreamReader(host.getInputStream()));
         out = new PrintWriter(host.getOutputStream(), true);



    }

    @Override
    public void disconnect() throws IOException {
        host.close();

        listenThread.isRunning = false;
    }

    @Override
    public void listen()  {
        ListenThread listenThread = new ListenThread(in,this);
        listenThread.start();

    }

    @Override
    public void sendMessage(String msg) {
        out.println(msg);
    }

    @Override
    public void addMessageListener(IChatObserver listener) {
        listeners.add(listener);
    }

    @Override
    public void notify(String msg) {
        for (IChatObserver chatObserver : listeners){
            chatObserver.chatMessageArrived(msg);
        }
    }
}
