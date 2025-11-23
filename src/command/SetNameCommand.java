package src.command;

import src.command.CLI;
import src.command.ICommand;
import src.userProfile.UserProfile;

import java.util.Map;
import java.util.Scanner;
import src.userProfile.*;

public class SetNameCommand implements ICommand {
    private final Scanner missedInput =new Scanner(System.in);
    private final String name="/setname";
    private final String description="Set your Userprofilename";
    private final UserProfile userProfile;
    public SetNameCommand(Map<String, ICommand> cmds, UserProfile userProfile) {
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
        return name;
    }
    @Override
    public String getDescription() {
        return description;
    }
}
