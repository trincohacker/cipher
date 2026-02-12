import java.util.*;
public class oneTimePadCipher{
	
	public static String encrypt(String plaintext, String key){
		StringBuilder ciphertext = new StringBuilder();
		for(int i=0;i<plaintext.length();i++){
			char ch = plaintext.charAt(i);
			char encryptedchar = (char) (ch ^ key.charAt(i));
			ciphertext.append(encryptedchar);
		}
		return ciphertext.toString();
	}
	
	public static String decrypt(String ciphertext, String key){
		return encrypt(ciphertext,key);
	}
	
	public static void main(String[] args){
		Scanner sc =  new Scanner(System.in);
		String plaintext = "HELLOWORLD";
		String key = "RANDOM1234";
		
		if(plaintext.length() != key.length()){
			System.out.println("The plaintext and the key should be same length");
		}
		
		String encryptedText = encrypt(plaintext,key);
		String decryptedText = decrypt(encryptedText,key);
		
		System.out.println("Plaintext:"+plaintext);
		System.out.println("Encrypted Text:"+encryptedText);
		System.out.println("Decrypted Text:"+decryptedText);
	}
}