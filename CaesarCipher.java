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