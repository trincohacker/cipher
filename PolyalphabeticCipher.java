public class PolyalphabeticCipher {

    // Function to encrypt the plaintext using the Polyalphabetic Cipher
    public static String encrypt(String plaintext, String keyword) {
        StringBuilder ciphertext = new StringBuilder();
        plaintext = plaintext.toUpperCase();
        keyword = keyword.toUpperCase();

        int keywordIndex = 0;
        for (char ch : plaintext.toCharArray()) {
            if (Character.isLetter(ch)) {
                int shift = keyword.charAt(keywordIndex) - 'A';
                char encryptedChar = (char) ((ch - 'A' + shift) % 26 + 'A');
                ciphertext.append(encryptedChar);
                keywordIndex = (keywordIndex + 1) % keyword.length();
            } else {
                ciphertext.append(ch);
            }
        }
        return ciphertext.toString();
    }
    // Function to decrypt the ciphertext using the Polyalphabetic Cipher
    public static String decrypt(String ciphertext, String keyword) {
        StringBuilder plaintext = new StringBuilder();
        ciphertext = ciphertext.toUpperCase();
        keyword = keyword.toUpperCase();

        int keywordIndex = 0;
        for (char ch : ciphertext.toCharArray()) {
            if (Character.isLetter(ch)) {
                int shift = keyword.charAt(keywordIndex) - 'A';
                char decryptedChar = (char) ((ch - 'A' - shift + 26) % 26 + 'A');
                plaintext.append(decryptedChar);

                keywordIndex = (keywordIndex + 1) % keyword.length();
            } else {
                plaintext.append(ch);
            }
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        String plaintext = "HELLO";
        String keyword = "KEY";

        String encryptedText = encrypt(plaintext, keyword);
        String decryptedText = decrypt(encryptedText, keyword);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Keyword: " + keyword);
        System.out.println("Encrypted: " + encryptedText);
        System.out.println("Decrypted: " + decryptedText);
    }
}