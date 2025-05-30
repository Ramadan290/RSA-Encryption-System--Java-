package RSA;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Base64;

public class RSA_Encrypt {
    public static void main(String[] args) {
        try {
            // Step 1 : Reading KU
            String[] keyLines = Files.readAllLines(Paths.get("I-O_Files/public1.key")).toArray(new String[0]);
            BigInteger e = new BigInteger(keyLines[0]);
            BigInteger n = new BigInteger(keyLines[1]);

            // Step 2 : Reading M
            String message = Files.readString(Paths.get("I-O_Files/input.txt")).trim();
            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
            BigInteger m = new BigInteger(1, messageBytes);

            // Step 3 : Checking M size compared to n
            if (m.compareTo(n) >= 0) {
                System.out.println("Message too large for the current key size.");
                return;
            }

            // Step 4 : Encrypting , C = M^e mod n
            BigInteger c = m.modPow(e, n);
            String cipherBase64 = Base64.getEncoder().encodeToString(c.toByteArray());

            // Step 5 : Writing C to file
            Files.writeString(Paths.get("I-O_Files/encrypted.txt"), cipherBase64);

            System.out.println("Message encrypted to encrypted.txt");

        } catch (Exception ex) {
            System.out.println("Encryption failed: " + ex.getMessage());
        }
    }
}
