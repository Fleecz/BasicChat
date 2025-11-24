package userProfile;


public class UserProfile implements IUserProfile{
    public String username;
    public String passwordHash;
    public String salt;
    public String role;
    public String UserProfileName;
    @Override
    public String getDisplayName() {
        return UserProfileName;
    }
}
