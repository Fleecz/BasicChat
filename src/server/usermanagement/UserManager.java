package server.usermanagement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.nio.file.Files;
import java.util.*;

public class UserManager {
    private final Map<String, UserRecord> users = new HashMap <>();
    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("src/users.json");
    public UserManager() {
        loadFromFile();
    }
    public void loadFromFile() {
        try{
            if(!file.exists()){
                saveToFile();
                return;
            }
            String json = Files.readString(file.toPath());
            List<UserRecord> list = mapper.readValue(json, new TypeReference<>() {
            });
            for (UserRecord r: list) {
                users.put(r.username, r);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load users.json", e);
        }
    }
    public void saveToFile() {
        try {
            List<UserRecord> list = new ArrayList<>(users.values());
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, list);
        } catch (Exception e) {
            throw new RuntimeException("Could not save users.json", e);
        }
    }

    public synchronized boolean register(String username, String plainPassword) {
        if (users.containsKey(username)) return false;

        String salt = PasswordUtil.generateSalt();
        String hash = PasswordUtil.hashPassword(plainPassword, salt);

        UserRecord r = new UserRecord(username, hash, salt, "User");
        users.put(username, r);
        saveToFile();
        return true;
    }

    public boolean validateUser(String username, String plainPassword) {
        UserRecord r = users.get(username);
        if (r == null) return false;
        return PasswordUtil.verify(plainPassword, r.salt, r.passwordHash);
    }
    }
