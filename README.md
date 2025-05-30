# 🔐 RSA Encryption System (Java)

This project is a Java-based implementation of the **RSA cryptographic system**, designed to demonstrate secure message exchange using both **single** and **double encryption** layers.  
It simulates the behavior of real-world encrypted communication between a `Sender` and a `Receiver`.

---

## 🧠 Features

- **Key Generation**
  - Generates RSA public/private key pairs for both sender and receiver.
- **Single Encryption**
  - Sender encrypts the message with the **receiver’s public key**.
  - Receiver decrypts it using their **private key**.
- **Double Encryption**
  - Sender first encrypts the message with **their private key** (signature).
  - Then encrypts the result with the **receiver’s public key** (confidentiality).
  - Receiver decrypts using their **private key**, then verifies with sender's **public key**.
- **File Simulation**
  - Uses `input.txt`, `encrypted.txt`, `decrypted.txt`, etc., to simulate the exchange process.

---


## ▶️ How to Run

1. **Compile all files**  
   Use your terminal or IDE to compile all `.java` files:
   ```bash
   javac *.java

2 . **Run the main java class**
  java Main

