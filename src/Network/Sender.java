package Network;

import java.io.*;
import java.net.Socket;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Base64;

public class Sender {
    public static void main(String[] args) {
        try {
            // Step 1 : Load public key
            String[] keyLines = Files.readAllLines(Paths.get("I-O_Files/public1.key")).toArray(new String[0]);
            BigInteger e = new BigInteger(keyLines[0]);
            BigInteger n = new BigInteger(keyLines[1]);

            // Step 2 : Read message
            String message = Files.readString(Paths.get("I-O_Files/input.txt")).trim();
            BigInteger m = new BigInteger(1, message.getBytes(StandardCharsets.UTF_8));

            // Step 3 : Encrypt
            BigInteger c = m.modPow(e, n);
            String base64Cipher = Base64.getEncoder().encodeToString(c.toByteArray());

            // Step 4 : Send over socket
            Socket socket = new Socket("localhost", 4444);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(base64Cipher);

            System.out.println("Encrypted message sent.");

            // Step 5 : Close Socket
            socket.close();

        } catch (Exception e) {
            System.out.println("Sender error: " + e.getMessage());
        }
    }
}
