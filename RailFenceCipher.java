public class RailFenceCipher{
	
	public static String encrypt(String plaintext, int rails){
		char[][] railMatrix =  new char[rails][plaintext.length()];
		boolean down = false;
		int row = 0, col = 0;
		
		for(char ch: plaintext.toCharArray()){
			railMatrix[row][col] = ch;
			
			if(row == 0 || row == rails-1){
				down = !down;
			}
			row += (down ? 1 : -1);
			col++;	
		}
		StringBuilder ciphertext = new StringBuilder();
		for(int i=0;i<rails;i++){
			for(int j=0;j<plaintext.length();j++){
				if(railMatrix[i][j] != 0){
					ciphertext.append(railMatrix[i][j]);
				}
			}
		}
		return ciphertext.toString();
	}
	
	public static String decrypt(String ciphertext, int rails){
		char[][] railMatrix = new char[rails][ciphertext.length()];
		boolean down = false;
		int row = 0, col = 0;
		for(int i=0;i<ciphertext.length();i++){
			railMatrix[row][col] = '*';
			
			if(row == 0 || row == rails - 1){
				down = !down;
			}
			
			row += (down ? 1 : -1);
			col++;
		}
		int index = 0;
		for(int i=0;i<rails;i++){
			for(int j=0;j<ciphertext.length();j++){
				if(railMatrix[i][j] == '*' && index < ciphertext.length()){
					railMatrix[i][j] = ciphertext.charAt(index++);
				}
			}
		}
		StringBuilder plaintext = new StringBuilder();
		row = 0;
		col = 0;
		down = false;
		for(int i=0;i<ciphertext.length();i++){
			plaintext.append(railMatrix[row][col]);
			
			if(row == 0 || row == rails -1){
				down = !down;
			}
			
			row += (down ? 1 : -1);
			col++;
		}
		return plaintext.toString();
	}
	
	public static void main(String[] args){
		String plaintext = "HELLO";
		int rails = 3;
		
		String encryptedText = encrypt(plaintext, rails);
		String decryptedText = decrypt(encryptedText, rails);
		
		System.out.println("Plaintext:"+plaintext);
		System.out.println("Rails:"+rails);
		System.out.println("Encrypted:"+encryptedText);
		System.out.println("Decrypted:"+decryptedText);	
	}	
}