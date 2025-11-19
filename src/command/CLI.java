package command;
import command.clicommands.ICLICommand;
import command.clicommands.QuitCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CLI {
    private Map<String, ICLICommand> commands;
    private boolean running = true;
    public CLI() {
        commands = new HashMap<>();
        commands.put("/quit", new QuitCommand());
    }
    public CLI setRunning(boolean running){
        this.running = running;
        return this;
    }
    public void run(){
        Scanner scanner = new Scanner(System.in);
        while (running) {
            String line=scanner.nextLine();
            line=line.trim();
            line= line.toLowerCase();
            ICLICommand cmd = commands.get(line);
            if(cmd!=null){
                cmd.execute(this);
            }
            else
                System.out.println("Unknown command");
        }
        System.out.println("Goodbye");
    }
}
