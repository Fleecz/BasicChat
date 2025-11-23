package src.command;

import java.util.Map;

public class HelpCommand implements ICommand {
    private final Map<String, ICommand> CommandList;
    private final String name = "/help";
    private final String description = "List all commands and their description";
    public HelpCommand(Map<String, ICommand> cmds) {
        this.CommandList=cmds;
    }
    @Override
    public void execute(CLI cli, String [] args) {
        for(ICommand cmd : CommandList.values()) {
            System.out.println(cmd.getName() +": " + cmd.getDescription());
        }
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
