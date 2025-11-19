package command;

public class HelpCommand implements ICommand {
    @Override
    public void execute() {
        System.out.println("help");
    }
}
