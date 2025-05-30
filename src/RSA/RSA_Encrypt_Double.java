package RSA;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Base64;

public class RSA_Encrypt_Double {
    public static void main(String[] args) {
        try {
            // Step 1 : Load first public key
            String[] pub1 = Files.readAllLines(Paths.get("I-O_Files/public1.key")).toArray(new String[0]);
            BigInteger e1 = new BigInteger(pub1[0]);
            BigInteger n1 = new BigInteger(pub1[1]);

            // Step 2 : Load second public key
            String[] pub2 = Files.readAllLines(Paths.get("I-O_Files/public2.key")).toArray(new String[0]);
            BigInteger e2 = new BigInteger(pub2[0]);
            BigInteger n2 = new BigInteger(pub2[1]);

            // Step 3 : Read message
            String message = Files.readString(Paths.get("I-O_Files/input.txt")).trim();
            BigInteger m = new BigInteger(1, message.getBytes(StandardCharsets.UTF_8));

            // Step 4 : Check M size compared to n
            if (m.compareTo(n1) >= 0 || m.compareTo(n2) >= 0) {
                System.out.println("Message too large for current keys.");
                return;
            }

            // Step 5 : First encryption
            BigInteger c1 = m.modPow(e1, n1);

            // Step 6 : Second encryption
            BigInteger c2 = c1.modPow(e2, n2);

            // Step 7 : Writing Final Cipher
            String finalCipher = Base64.getEncoder().encodeToString(c2.toByteArray());
            Files.writeString(Paths.get("I-O_Files/encrypted_double.txt"), finalCipher);

            System.out.println("Double encryption complete-> encrypted_double.txt");

        } catch (Exception ex) {
            System.out.println("Double encryption failed: " + ex.getMessage());
        }
    }
}
