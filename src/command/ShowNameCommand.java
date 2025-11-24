package command;

import userProfile.UserProfile;

public class ShowNameCommand implements ICommand {
    private final UserProfile userProfile;
    public ShowNameCommand(UserProfile userProfile) {
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
        return "/showname";
    }
    @Override
    public String getDescription() {
        return "Show your Userprofilename";
    }
}
