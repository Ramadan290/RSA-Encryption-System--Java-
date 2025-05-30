package Network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Base64;

public class Receiver {
    public static void main(String[] args) {

        // Step 1 : Establishing Port connection
        try (ServerSocket server = new ServerSocket(4444)) {
            System.out.println("Receiver listening on port 4444...");
            Socket client = server.accept();
            System.out.println("Connection established!");

            //  Step 2 : Creating Buffer
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String base64Cipher = in.readLine();

            // Step 3 : Load private key
            String[] keyLines = Files.readAllLines(Paths.get("I-O_Files/private1.key")).toArray(new String[0]);
            BigInteger d = new BigInteger(keyLines[0]);
            BigInteger n = new BigInteger(keyLines[1]);

            // Step 4 : Decode and decrypt
            byte[] cipherBytes = Base64.getDecoder().decode(base64Cipher);
            BigInteger c = new BigInteger(1, cipherBytes);
            BigInteger m = c.modPow(d, n);
            String message = new String(m.toByteArray(), StandardCharsets.UTF_8);

            System.out.println("Message received and decrypted: " + message);


            // Step 5 : Close Connection
            client.close();

        } catch (Exception e) {
            System.out.println("Receiver error: " + e.getMessage());
        }
    }
}
