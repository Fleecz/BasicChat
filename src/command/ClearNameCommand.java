package command;

import userProfile.UserProfile;

public class ClearNameCommand implements ICommand {
    private final UserProfile userProfile;
    public ClearNameCommand(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
    @Override
    public void execute(CLI cli, String[] args) {
        userProfile.UserProfileName = null;
        System.out.println("Your UserProfile Name has been removed.");
    }

    @Override
    public String getName() {
        return "/clearname";
    }

    @Override
    public String getDescription() {
        return "Remove your Userprofilename";
    }
}
