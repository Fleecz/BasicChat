package src.userProfile;


public class UserProfile implements IUserProfile{
    public String UserProfileName;
    @Override
    public String getDisplayName() {
        return UserProfileName;
    }
}
