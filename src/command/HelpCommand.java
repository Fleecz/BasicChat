package command;

import java.util.Map;

public class HelpCommand implements ICommand {
    private final Map<String, ICommand> CommandList;

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
        return "/help";
    }

    @Override
    public String getDescription() {
        return "List all commands and their description";
    }
}
