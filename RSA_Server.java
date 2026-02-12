import java.net.*;
import java.io.*;
import java.util.*;

public class RSA_Server {

    static int p = 17;
    static int q = 11;
    static int n, phi, e, d;

    // Modular exponentiation
    static int modExp(int base, int exp, int mod) {
        int result = 1;
        base = base % mod;
        while (exp > 0) {
            if (exp % 2 == 1)
                result = (result * base) % mod;
            exp = exp / 2;
            base = (base * base) % mod;
        }
        return result;
    }

    static int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    static void generateKeys() {
        n = p * q;
        phi = (p - 1) * (q - 1);

        e = 7;
        while (gcd(e, phi) != 1)
            e++;

        for (d = 1; d < phi; d++) {
            if ((d * e) % phi == 1)
                break;
        }
    }

    public static void main(String[] args) throws Exception {

        generateKeys();

        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("RSA Server started...");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected");

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        // Send public key
        out.writeInt(e);
        out.writeInt(n);

        int cipherText = in.readInt();
        int decrypted = modExp(cipherText, d, n);

        System.out.println("Encrypted message received: " + cipherText);
        System.out.println("Decrypted message: " + decrypted);

        socket.close();
        serverSocket.close();
    }
}
