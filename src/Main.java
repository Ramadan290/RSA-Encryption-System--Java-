import RSA.*;
import Network.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting RSA Full Process...\n " +
                "********************************************************************");

        try {
            // Step 1: Generate first key pair
            System.out.println("Generating Key Pair 1.. ");
            RSA_KeyGen_1.main(null);

            // Step 2: Generate second key pair
            System.out.println("********************************************************************" +
                    "\nGenerating Key Pair 2...");
            RSA_KeyGen_2.main(null);

            // Step 3: Encrypt using Key 1
            System.out.println("********************************************************************\n" +
                    "Encrypting with Key 1...");
            RSA_Encrypt.main(null);

            // Step 4: Decrypt using Key 1
            System.out.println("********************************************************************\n" +
                    "Decrypting with Key 1...");
            RSA_Decrypt.main(null);

            // Step 5: Double Encrypt (Key 1 → Key 2)
            System.out.println("********************************************************************\n" +
                    "Double Encrypting (Key 1 -> Key 2)...");
            RSA_Encrypt_Double.main(null);

            // Step 6: Double Decrypt (Key 2 → Key 1)
            System.out.println("********************************************************************\n" +
                    "Double Decrypting (Key 2 ->Key 1)...");
            RSA_Decrypt_Double.main(null);

            // Step 7: Start Receiver in separate thread
            System.out.println("********************************************************************\n" +
                    "Starting Receiver (server)...");
            Thread receiverThread = new Thread(() -> Receiver.main(null));
            receiverThread.start();

            // Give Receiver time to bind socket
            Thread.sleep(1000);

            // Step 8: Run Sender
            System.out.println("\n" +
                    "Running Sender (client)...");
            Sender.main(null);

            // Wait for receiver to finish
            receiverThread.join();

            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n" +
                    "All phases completed successfully.");

        } catch (Exception ex) {
            System.out.println("Error during execution: " + ex.getMessage());
        }
    }
}