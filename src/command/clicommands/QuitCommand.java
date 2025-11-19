package command.clicommands;

import command.CLI;
import command.ICommand;

public class QuitCommand implements ICLICommand {
    @Override
    public void execute(CLI cli) {
        cli.setRunning(false);
    }
}
