package RSA;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Base64;

public class RSA_Decrypt {
    public static void main(String[] args) {
        try {
            String[] keyLines = Files.readAllLines(Paths.get("I-O_Files/private1.key")).toArray(new String[0]);
            BigInteger d = new BigInteger(keyLines[0]);
            BigInteger n = new BigInteger(keyLines[1]);

            String base64Cipher = Files.readString(Paths.get("I-O_Files/encrypted.txt")).trim();
            byte[] cipherBytes = Base64.getDecoder().decode(base64Cipher);
            BigInteger c = new BigInteger(1, cipherBytes);

            BigInteger m = c.modPow(d, n);
            String decrypted = new String(m.toByteArray(), StandardCharsets.UTF_8);
            Files.writeString(Paths.get("I-O_Files/decrypted.txt"), decrypted);

            System.out.println("Decryption complete: check decrypted.txt");

        } catch (Exception ex) {
            System.out.println("Decryption failed: " + ex.getMessage());
        }
    }
}
