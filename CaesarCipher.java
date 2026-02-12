import java.util.*;
public class CaesarCipher{
	
	public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	public static String encrypt(String plaintext, int shiftkey){
		plaintext = plaintext.toLowerCase();
		String ciphertext = "";
		 
		for(int i=0;i<plaintext.length();i++){
			char ch = plaintext.charAt(i);
			int posofchar = alphabet.indexOf(ch);
			
			int keyvalue = (posofchar + shiftkey) % 26;
			char replacevalue = alphabet.charAt(keyvalue);
			
			ciphertext += replacevalue;
		}
		return ciphertext;
	}
	
	public static String decrypt(String ciphertext, int shiftkey){
		ciphertext = ciphertext.toLowerCase();
		String plaintext = "";
		
		for(int i=0;i<ciphertext.length();i++){
			char ch = ciphertext.charAt(i);
			int posofchar = alphabet.indexOf(ch);
			
			int keyvalue = (posofchar - shiftkey) % 26;
			if(keyvalue < 0){
				keyvalue = alphabet.length() + keyvalue;
			}
			char replacevalue = alphabet.charAt(keyvalue);
			plaintext += replacevalue;
		}
		return plaintext;
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the plaintext:");
		String plaintext = sc.nextLine();
		
		System.out.print("Enter the shiftkey:");
		int shiftkey = sc.nextInt();
		
		String encryptedText = encrypt(plaintext,shiftkey);
		String decryptedText = decrypt(encryptedText,shiftkey);
		
		System.out.println("Plain text:"+plaintext);
		System.out.println("Encrypted text:"+encryptedText);
		System.out.println("Decrypted text:"+decryptedText);
	}

}



------------------------

import java.util.Arrays;

public class ColumnarTransCipher{

	public static String encrypt(String plaintext, String keyword){
		int col = keyword.length();
		int row = (int) Math.ceil((double)plaintext.length() / col);
			
		char[][] matrix = new char[row][col];
		int k = 0;
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(k < plaintext.length()){
					matrix[i][j] = plaintext.charAt(k++);
				}else{
					matrix[i][j] = 'X';
				}
			}
		}
		Character[] keyChars = new Character[col];
		for(int i=0;i<col;i++) keyChars[i] = keyword.charAt(i);
		Character[] sortedKey = keyChars.clone();
		Arrays.sort(sortedKey);
		
		StringBuilder cipher =  new StringBuilder();
		for(char ch : sortedKey){
			int colIndex = Arrays.asList(keyChars).indexOf(ch);
			for(int i=0;i<row;i++){
				cipher.append(matrix[i][colIndex]);
			}
			keyChars[colIndex] = null;
		}
		return cipher.toString();
			
	}
	
	
	
	
	public static String decryption(String ciphertext, String keyword){
		int col = keyword.length();
		int row = (int) Math.ceil((double) ciphertext.length()/col);
		
		char[][] matrix = new char[row][col];
		
		Character[] keyChars = new Character[col];
		for(int i=0;i<col;i++) keyChars[i] = keyword.charAt(i);
		Character[] sortedKey = keyChars.clone();
		Arrays.sort(sortedKey);
		
		int k=0;
		for(char ch:sortedKey){
			int colIndex = Arrays.asList(keyChars).indexOf(ch);
			for(int i=0;i<row;i++){
				matrix[i][colIndex] = ciphertext.charAt(k++);
			}
			keyChars[colIndex] = null;
		}
		
		StringBuilder plain =  new StringBuilder();
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				plain.append(matrix[i][j]);
			}
		}
		return plain.toString().replace("X","");
	}
	public static void main(String[] args){
		String message = "HELLOCRYPTOGRAPHY";
        String key = "KEY";
		
		System.out.println("Plain text:"+message);
		
		String encrypted = encrypt(message, key);
        System.out.println("Encrypted Text: " + encrypted);
		
		String decrypted = decryption(encrypted, key);
        System.out.println("Decrypted Text: " + decrypted);
		
	}
	
}


------------



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


------------------


