package RSA;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Base64;

public class RSA_Decrypt_Double {
    public static void main(String[] args) {
        try {
            // Step 1 : Load private key for second encryption layer
            String[] pri2 = Files.readAllLines(Paths.get("I-O_Files/private2.key")).toArray(new String[0]);
            BigInteger d2 = new BigInteger(pri2[0]);
            BigInteger n2 = new BigInteger(pri2[1]);

            // Step 2 : Load private key for first encryption layer
            String[] pri1 = Files.readAllLines(Paths.get("I-O_Files/private1.key")).toArray(new String[0]);
            BigInteger d1 = new BigInteger(pri1[0]);
            BigInteger n1 = new BigInteger(pri1[1]);

            // Step 3 : Read and decode the Base64 encrypted data
            String base64Cipher = Files.readString(Paths.get("I-O_Files/encrypted_double.txt")).trim();
            byte[] cipherBytes = Base64.getDecoder().decode(base64Cipher);
            BigInteger c2 = new BigInteger(1, cipherBytes);

            // Step 4 : Decrypt: Layer 2 (outer layer)
            BigInteger c1 = c2.modPow(d2, n2);

            // Step 5 : Decrypt: Layer 1 (inner layer)
            BigInteger m = c1.modPow(d1, n1);

            // Step 6 : Convert to readable string
            String message = new String(m.toByteArray(), StandardCharsets.UTF_8);

            // Step 7 : Write result
            Files.writeString(Paths.get("I-O_Files/decrypted_double.txt"), message, StandardCharsets.UTF_8);

            System.out.println("Double decryption successful -> I-O_Files/decrypted_double.txt");

        } catch (Exception ex) {
            System.out.println("Double decryption failed: " + ex.getMessage());
        }
    }
}
