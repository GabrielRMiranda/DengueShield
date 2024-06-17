package Classes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
public class CriptografiaSenha {
    public static String gerarSalt(){
        SecureRandom criptografa = new SecureRandom();
        byte[] bytesSalt = new byte[16];
        criptografa.nextBytes(bytesSalt);
        return Base64.getEncoder().encodeToString(bytesSalt);
    }

    public static String gerarHash(String senha, String salt) throws NoSuchAlgorithmException{
        if (salt == null) {
            throw new IllegalArgumentException("Salt não pode ser null");
        }
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(Base64.getDecoder().decode(salt));
        byte[] bytes = md.digest(senha.getBytes());
        return Base64.getEncoder().encodeToString(bytes);
    }
}