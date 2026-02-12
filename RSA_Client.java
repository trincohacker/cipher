import java.net.*;
import java.io.*;
import java.util.*;

public class RSA_Client {

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

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 5000);

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        Scanner sc = new Scanner(System.in);

        int e = in.readInt();
        int n = in.readInt();

        System.out.print("Enter plaintext (number): ");
        int plaintext = sc.nextInt();

        int cipherText = modExp(plaintext, e, n);
        out.writeInt(cipherText);

        System.out.println("Encrypted message sent: " + cipherText);

        socket.close();
    }
}
