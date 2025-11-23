package src.command;

import java.util.Map;

public class HelpCommand implements ICommand {
    private final Map<String, ICommand> helpList;
    String name = "Help-Command";
    String description = "Lists all commands and their description";
    public HelpCommand(Map<String, ICommand> cmds) {
        this.helpList=cmds;
    }
    @Override
    public void execute(CLI cli) {
        for(ICommand cmd : helpList.values()) {
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
