package command;

import java.io.IOException;
import java.util.*;

import communication.Client;
import communication.Host;
import communication.IChatObserver;
import communication.ICommunication;
import userProfile.*;


public class CLI implements IChatObserver {
    Scanner scanner = new Scanner(System.in);
    private Map<String, ICommand> cmds;
    private boolean running = true;
    private IUserProfile userProfile;
    private int port = 1312;
    private String hostName = "localhost";
    boolean host = false;
    private ICommunication network;
    public CLI() {
        userProfile = new UserProfile();
        cmds = new HashMap<>();
        cmds.put("/help", new HelpCommand(cmds));
        cmds.put("/quit", new QuitCommand(cmds));
        cmds.put("/setname", new SetNameCommand(cmds, (UserProfile) userProfile));
        cmds.put("/showname", new ShowNameCommand(cmds, (UserProfile) userProfile));
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
    public void run(){
        startdialoge(0,"none");
        if(host){
            try {
                network= new Host(port,this);
                startCLI();
            } catch (Exception e) {
                System.out.println("Could not start Server");
                startdialoge(0,"None");

            }
        }else{
            try {
                network = new Client(hostName,port,this);
                startCLI();
            } catch (Exception e) {
                System.out.println("Could not connect to Server");
                startdialoge(0,"None");

            }
        }




    }
    private void startCLI(){
        System.out.print("Welcome. Type \"/help\" for a list of commands: \n");
        while (running) {
            String line = scanner.nextLine().trim();
            if (line.startsWith("/")){
                String[] parts =line.split("\\s+");
                String cmdName = parts[0].toLowerCase();
                String[] args = Arrays.copyOfRange(parts, 1, parts.length);
                ICommand command = cmds.get(cmdName);
                if (command != null) {
                    command.execute(this,args);
                }
                else
                    System.out.println("Unknown command");

            }else {

                String msg =  userProfile.getDisplayName() + ": " + line;

                network.sendMessage(msg);
            }

        }
        try {
            network.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Goodbye!");

    }

    @Override
    public void chatMessageArrived(String message) {
        System.out.println(message);
    }
    void startdialoge(int stage,String type){
        if (stage == 0){
            System.out.println("Do you want to host or join?");
            String line = scanner.nextLine().trim();
            if(line.equalsIgnoreCase("join")){
                startdialoge(1,"join");
            }
            else if (line.equalsIgnoreCase("host")){
                startdialoge(1,"host");
            }
            else {
                startdialoge(0,"none");
            }

        }
        if(stage == 1){
            switch (type){
                case "join":
                    System.out.println("What is the hostname?");
                    String line = scanner.nextLine().trim();
                    hostName = line;
                    startdialoge(2,"join");
                    break;
                case "host":
                    System.out.println("What is the port??");
                    String line1 = scanner.nextLine().trim();
                    port =  Integer.parseInt(line1);
                    host = true;
                    return;


                default:
                    startdialoge(0,"none");
                    break;
            }
        }
        if(stage == 2){
            System.out.println("What is the port??");
            String line1 = scanner.nextLine().trim();
            port =  Integer.parseInt(line1);
            host = false;

        }
    }
}
