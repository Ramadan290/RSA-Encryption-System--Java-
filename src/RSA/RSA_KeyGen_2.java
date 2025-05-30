package RSA;

import java.io.FileWriter;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA_KeyGen_2 {
    public static void main(String[] args) {
        try {
            int bitLength = 1024;
            SecureRandom rand = new SecureRandom();

            // Step 1 : Creating p and q
            BigInteger p = BigInteger.probablePrime(bitLength, rand);
            BigInteger q = BigInteger.probablePrime(bitLength, rand);

            // Step 2 : Calculating n = p*q
            BigInteger n = p.multiply(q);

            // Step 3 : Calculating fi(n) = (p-1) * (q-1)
            BigInteger fi_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

            // Step 4 : Picking e
            BigInteger e = new BigInteger("65537");

            // Step 5 : Calculating d
            BigInteger d = e.modInverse(fi_n);

            // Step 6 : Writing KU to file
            FileWriter pub = new FileWriter("I-O_Files/public2.key");
            pub.write(e.toString() + "\n" + n.toString());
            pub.close();

            // Step 7 : Writing KR to files
            FileWriter pri = new FileWriter("I-O_Files/private2.key");
            pri.write(d.toString() + "\n" + n.toString());
            pri.close();

            System.out.println("Keys generated successfully.");
        } catch (Exception ex) {
            System.out.println("Key generation failed: " + ex.getMessage());
        }
    }
}
