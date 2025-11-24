package command;

import java.util.Map;

public class QuitCommand implements ICommand {
    private final String name="/quit";
    private final String description="End the CLI";
    public QuitCommand(Map<String, ICommand> cmds) {
    }
    @Override
    public void execute(CLI cli, String [] args) {
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
