package server.usermanagement;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
public class PasswordUtil {
    public static String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
    public static String hashPassword(String password, String saltBase64){
        try{
        byte[] salt=Base64.getDecoder().decode(saltBase64);
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 100_000, 256);
        SecretKeyFactory skf= SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte [] hash = skf.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }catch(Exception e){
            throw new RuntimeException(e);
        }
}
public static boolean verify(String password, String saltBase64, String exceptedHashBase64){
        String computed = hashPassword(password, saltBase64);
        return computed.equals(exceptedHashBase64);
}
}
