package command;

public interface ICommand {
    public void execute(CLI cli, String [] args);
    String getName();
    String getDescription();
}
