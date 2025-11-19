package command;

import java.util.Map;

public class TestCommand implements ICommand {
    String name="Test";
    String description="test";

    public TestCommand(Map<String, ICommand> cmds) {
    }

    @Override
    public void execute(CLI cli) {
        System.out.println(name);
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
