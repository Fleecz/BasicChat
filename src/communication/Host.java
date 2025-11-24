package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import server.usermanagement.UserManager;

public class Host implements ICommunication{
    private UserManager userManager = new UserManager();
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
        System.out.println("Waiting for client...");
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
        if (msg.startsWith("/login")) {
            String [] parts = msg.split(" ");
            String user = parts[1];
            String pwd = parts[2];
            if (userManager.validateUser(user,pwd)) {
                out.println("You are logged in!");
                out.println("Welcome. Type \"/help\" for a list of commands: \n");
            } else {
                out.println("Login failed!");
            }
            return;
        }
        if (msg.startsWith("/register")) {
            String[] parts = msg.split(" ");
            String user = parts[1];
            String pwd  = parts[2];

            boolean ok = userManager.register(user, pwd);

            if (ok) out.println("REGISTER_OK");
            else out.println("REGISTER_FAIL");
            return;
        }
        switch (msg) {
            case "REGISTER_OK" -> {
                System.out.println("Registration successful.");
                return;
            }
            case "REGISTER_FAIL" -> {
                System.out.println("Registration failed (user exists).");
                return;
            }
            case "LOGIN_OK" -> {
                System.out.println("Login successful.");
                return;
            }
            case "LOGIN_FAIL" -> {
                System.out.println("Login failed.");
                return;
            }
        }
        for (IChatObserver chatObserver : listeners){
            chatObserver.chatMessageArrived(msg);
        }
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
