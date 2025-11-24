package command;

public class QuitCommand implements ICommand {
    public QuitCommand() {
    }
    @Override
    public void execute(CLI cli, String [] args) {
        cli.setRunning(false);
        System.out.println("Quit-Command");
    }
    @Override
    public String getName() {
        return "/quit";
    }
    @Override
    public String getDescription() {
        return "End the CLI";
    }
}
