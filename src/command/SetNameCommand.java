package command;

import userProfile.UserProfile;

import java.util.Scanner;

public class SetNameCommand implements ICommand {
    private final Scanner missedInput =new Scanner(System.in);
    private final UserProfile userProfile;
    public SetNameCommand(UserProfile userProfile) {
    this.userProfile = userProfile;
    }
    @Override
    public void execute(CLI cli, String [] args) {
        if(String.join(" ", args).isEmpty()) {
            System.out.println("Please enter a Userprofilename:");
            userProfile.UserProfileName= missedInput.nextLine();
        }
        else
            userProfile.UserProfileName = String.join(" ", args).trim();
        System.out.println("Username set to " + userProfile.UserProfileName);
    }
    @Override
    public String getName() {
        return "/setname";
    }
    @Override
    public String getDescription() {
        return "Set your Userprofilename";
    }
}
