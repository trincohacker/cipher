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


public class PlayfairCipher {

    private static final int MATRIX_SIZE = 5;

    // Function to generate the Playfair Square from the given key
    private static char[][] generatePlayfairSquare(String key) {
        char[][] playfairSquare = new char[MATRIX_SIZE][MATRIX_SIZE];
        boolean[] usedLetters = new boolean[26];

        // Convert the key to uppercase and remove duplicates
        String formattedKey = key.toUpperCase().replaceAll("[^A-Z]", "").replaceAll("J", "I");

        // Fill the matrix with the key
        int row = 0, col = 0;
        for (char ch : formattedKey.toCharArray()) {
            if (!usedLetters[ch - 'A']) {
                playfairSquare[row][col] = ch;
                usedLetters[ch - 'A'] = true;
                col++;
                if (col == MATRIX_SIZE) {
                    col = 0;
                    row++;
                }
            }
        }

        // Fill the rest of the matrix with remaining letters
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (ch != 'J' && !usedLetters[ch - 'A']) {
                playfairSquare[row][col] = ch;
                col++;
                if (col == MATRIX_SIZE) {
                    col = 0;
                    row++;
                }
            }
        }

        return playfairSquare;
    }

    // Function to find the coordinates of a given letter in the Playfair Square
    private static void findLetterCoordinates(char[][] playfairSquare, char letter, int[] coordinates) {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (playfairSquare[i][j] == letter) {
                    coordinates[0] = i;
                    coordinates[1] = j;
                    return;
                }
            }
        }
    }

    // Function to handle repeated letters in the plaintext
    private static String handleRepeatedLetters(String plaintext) {
        StringBuilder processedText = new StringBuilder(plaintext);
        for (int i = 0; i < processedText.length() - 1; i += 2) {
            if (processedText.charAt(i) == processedText.charAt(i + 1)) {
                processedText.insert(i + 1, 'X');
            }
        }
        return processedText.toString();
    }

    // Function to encrypt the plaintext using the Playfair Cipher
    public static String encrypt(String plaintext, String key) {
        char[][] playfairSquare = generatePlayfairSquare(key);
        String processedText = handleRepeatedLetters(plaintext.toUpperCase().replaceAll("[^A-Z]", "").replaceAll("J", "I"));
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < processedText.length(); i += 2) {
            char firstChar = processedText.charAt(i);
            char secondChar = processedText.charAt(i + 1);

            int[] firstCoordinates = new int[2];
            int[] secondCoordinates = new int[2];
            findLetterCoordinates(playfairSquare, firstChar, firstCoordinates);
            findLetterCoordinates(playfairSquare, secondChar, secondCoordinates);

            int firstRow = firstCoordinates[0];
            int firstCol = firstCoordinates[1];
            int secondRow = secondCoordinates[0];
            int secondCol = secondCoordinates[1];

            char encryptedFirstChar, encryptedSecondChar;
            if (firstRow == secondRow) {
                encryptedFirstChar = playfairSquare[firstRow][(firstCol + 1) % MATRIX_SIZE];
                encryptedSecondChar = playfairSquare[secondRow][(secondCol + 1) % MATRIX_SIZE];
            } else if (firstCol == secondCol) {
                encryptedFirstChar = playfairSquare[(firstRow + 1) % MATRIX_SIZE][firstCol];
                encryptedSecondChar = playfairSquare[(secondRow + 1) % MATRIX_SIZE][secondCol];
            } else {
                encryptedFirstChar = playfairSquare[firstRow][secondCol];
                encryptedSecondChar = playfairSquare[secondRow][firstCol];
            }

            ciphertext.append(encryptedFirstChar).append(encryptedSecondChar);
        }

        return ciphertext.toString();
    }

    // Function to decrypt the ciphertext using the Playfair Cipher
    public static String decrypt(String ciphertext, String key) {
        char[][] playfairSquare = generatePlayfairSquare(key);
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char firstChar = ciphertext.charAt(i);
            char secondChar = ciphertext.charAt(i + 1);

            int[] firstCoordinates = new int[2];
            int[] secondCoordinates = new int[2];
            findLetterCoordinates(playfairSquare, firstChar, firstCoordinates);
            findLetterCoordinates(playfairSquare, secondChar, secondCoordinates);

            int firstRow = firstCoordinates[0];
            int firstCol = firstCoordinates[1];
            int secondRow = secondCoordinates[0];
            int secondCol = secondCoordinates[1];

            char decryptedFirstChar, decryptedSecondChar;
            if (firstRow == secondRow) {
                decryptedFirstChar = playfairSquare[firstRow][(firstCol - 1 + MATRIX_SIZE) % MATRIX_SIZE];
                decryptedSecondChar = playfairSquare[secondRow][(secondCol - 1 + MATRIX_SIZE) % MATRIX_SIZE];
            } else if (firstCol == secondCol) {
                decryptedFirstChar = playfairSquare[(firstRow - 1 + MATRIX_SIZE) % MATRIX_SIZE][firstCol];
                decryptedSecondChar = playfairSquare[(secondRow - 1 + MATRIX_SIZE) % MATRIX_SIZE][secondCol];
            } else {
                decryptedFirstChar = playfairSquare[firstRow][secondCol];
                decryptedSecondChar = playfairSquare[secondRow][firstCol];
            }

            plaintext.append(decryptedFirstChar).append(decryptedSecondChar);
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        String plaintext = "HELLO";
        String key = "KEYWORD";

        String encryptedText = encrypt(plaintext, key);
        String decryptedText = decrypt(encryptedText, key);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Key:       " + key);
        System.out.println("Encrypted: " + encryptedText);
        System.out.println("Decrypted: " + decryptedText);
    }
}




----------



