package command;

import command.CLI;
import command.ICommand;
import userProfile.UserProfile;
import java.util.Map;

public class ShowNameCommand implements ICommand {
    private final String name="/showname";
    private final String description="Show your Userprofilename";
    private final UserProfile userProfile;
    public ShowNameCommand(Map<String, ICommand> cmds, UserProfile userProfile) {
        this.userProfile = userProfile;
    }
    @Override
    public void execute(CLI cli, String [] args) {

        if (userProfile.UserProfileName != null) {
            System.out.println("Your Username is: " + userProfile.getDisplayName());
        } else
            System.out.println("Your Username is not set");

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
