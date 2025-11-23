package src.command;

import src.command.ICommand;
import src.userProfile.UserProfile;

import java.util.Map;

public class ClearNameCommand implements ICommand {
    private final String name="/clearname";
    private final String description="Remove your Userprofilename";
    private final UserProfile userProfile;
    public ClearNameCommand(Map<String, ICommand> cmds, UserProfile userProfile) {
        this.userProfile = userProfile;
    }
    @Override
    public void execute(CLI cli, String[] args) {
        userProfile.UserProfileName = null;
        System.out.println("Your UserProfile Name has been removed.");
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
