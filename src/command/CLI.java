package command;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

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
    public CLI setRunning(boolean running) {
        this.running = running;
        return this;
    }
    public void run(){
        while (running) {
            String line = scanner.nextLine().trim().toLowerCase();
            ICommand command = cmds.get(line);
            if (command != null) {
                command.execute(this);
            }
            else
                System.out.println("Unknown command");
        }
    }
}
