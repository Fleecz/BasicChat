package command;

import java.util.*;

public class CLI {
    Scanner scanner = new Scanner(System.in);
    private Map<String, ICommand> cmds;
    private boolean running = true;
    public CLI() {
        cmds = new HashMap<>();
        cmds.put("/help", new HelpCommand(cmds));
        cmds.put("/test", new TestCommand(cmds));
        cmds.put("/quit", new QuitCommand(cmds));
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
    public void run(){
        while (running) {
            String line = scanner.nextLine().trim();
            String[] parts =line.split("\\s+");
            String cmdName = parts[0].toLowerCase();
            String[] args = Arrays.copyOfRange(parts, 1, parts.length);
            ICommand command = cmds.get(cmdName);
            if (command != null) {
                command.execute(this);
            }
            else
                System.out.println("Unknown command");
        }
    }
}
