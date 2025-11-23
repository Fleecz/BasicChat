package src.command;

public interface ICommand {
    public void execute(CLI cli);
    String getName();
    String getDescription();
}
