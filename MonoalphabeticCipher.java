public class MonoalphabeticCipher{

    // Function to encrypt the plaintext using a given key
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);

            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                int offset = ch - base;
                char encryptedChar = key.charAt(offset);
                ciphertext.append(Character.isLowerCase(ch) ? Character.toLowerCase(encryptedChar) : encryptedChar);
            } else {
                ciphertext.append(ch);
            }
        }

        return ciphertext.toString();
    }
	
	 //Function to decrypt the ciphertext using a given key
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i++) {
            char ch = ciphertext.charAt(i);

            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                int index = key.indexOf(Character.toUpperCase(ch));
                char decryptedChar = (char) (base + index);
                plaintext.append(Character.isLowerCase(ch) ? Character.toLowerCase(decryptedChar) : decryptedChar);
            } else {
                plaintext.append(ch);
            }
        }

        return plaintext.toString();
    }
		
	public static void main(String[] args) {
        String plaintext = "Hello, World!";
        String key = "QWERTYUIOPASDFGHJKLZXCVBNM";

        String encryptedText = encrypt(plaintext, key);
        String decryptedText = decrypt(encryptedText, key);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted: " + encryptedText);
        System.out.println("Decrypted: " + decryptedText);
    }
}