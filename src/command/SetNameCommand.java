package command;

import userProfile.UserProfile;

import java.util.Map;
import java.util.Scanner;
import userProfile.*;

public class SetNameCommand implements ICommand {
    Scanner scanner = new Scanner(System.in);
    String name="Name-Command";
    String description="use this command to set your Userprofilename";
    UserProfile userProfile;
    public SetNameCommand(Map<String, ICommand> cmds, UserProfile userProfile) {
    this.userProfile = userProfile;
    }
    @Override
    public void execute(CLI cli) {
        System.out.println("Type in your Username");
        userProfile.UserProfileName = scanner.nextLine();
        System.out.println("Username set to " + userProfile.UserProfileName);
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
