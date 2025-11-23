package src.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Host implements ICommunication{
   ArrayList<IChatObserver> listeners = new ArrayList<>();
    ServerSocket serverSocket = null;
    Socket client = null;
    BufferedReader in = null;
    PrintWriter out = null;
    ListenThread listenThread = null;
    public Host(int port,IChatObserver chatObserver) throws Exception {
        connect("localhost",port);
        listeners.add(chatObserver);
        listen();
    }
    @Override
    public void connect(String host, int port) throws Exception {
        serverSocket = new ServerSocket(port);
        System.out.println("Wainting for client...");
        client = serverSocket.accept();
        System.out.println("client connected");


        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);



    }

    @Override
    public void disconnect() throws IOException {
        serverSocket.close();
        client.close();
        listenThread.isRunning = false;
    }

    @Override
    public void listen()  {
        listenThread = new ListenThread(in,this);
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
