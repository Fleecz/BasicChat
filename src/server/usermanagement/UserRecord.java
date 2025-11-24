package server.usermanagement;

public class UserRecord {
    public String username;
    public String passwordHash;
    public String salt;
    public String role;
    public UserRecord(){} //empty version vor Jackson
    public UserRecord(String username, String passwordHash, String salt, String role){
        this.username = username;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.role = role;
    }
}
