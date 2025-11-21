package command;

import java.util.*;
import userProfile.*;

public class CLI {
    Scanner scanner = new Scanner(System.in);
    private final Map<String, ICommand> cmds;
    private boolean running = true;

    public CLI() {
        UserProfile userProfile = new UserProfile();
        cmds = new HashMap<>();
        cmds.put("/help", new HelpCommand(cmds));
        cmds.put("/quit", new QuitCommand(cmds));
        cmds.put("/setname", new SetNameCommand(cmds, userProfile));
        cmds.put("/showname", new ShowNameCommand(cmds, userProfile));
        cmds.put("/clearname", new ClearNameCommand(cmds, userProfile));
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
    public void run(){
        System.out.print("Welcome. Type \"/help\" for a list of commands:\n ");
        while (running) {
            String line = scanner.nextLine().trim();
            String[] parts =line.split("\\s+");
            String cmdName = parts[0].toLowerCase();
            String[] args = Arrays.copyOfRange(parts, 1, parts.length);
            ICommand command = cmds.get(cmdName);
            if (command != null) {
                command.execute(this, args);
            }
            else
                System.out.println("Unknown command");
        }
        System.out.println("Goodbye!");
    }
}
