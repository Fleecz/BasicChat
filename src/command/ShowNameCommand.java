package command;

import userProfile.IUserProfile;
import userProfile.UserProfile;
import java.util.Map;

public class ShowNameCommand implements ICommand{
    String name="ShowName-Command";
    String description="use this command to show your Userprofilename";
    UserProfile userProfile;
    public ShowNameCommand(Map<String, ICommand> cmds, UserProfile userProfile) {
        this.userProfile = userProfile;
    }
    @Override
    public void execute(CLI cli) {
        if (userProfile != null) {
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
