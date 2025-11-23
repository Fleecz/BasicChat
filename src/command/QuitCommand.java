package src.command;

import java.util.Map;

public class QuitCommand implements ICommand {
    String name="Quit-Command";
    String description="Ends the CLI";
    public QuitCommand(Map<String, ICommand> cmds) {
    }
    @Override
    public void execute(CLI cli) {
        cli.setRunning(false);
        System.out.println("Quit-Command");
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getDescription() {
        return description;
    }
}
