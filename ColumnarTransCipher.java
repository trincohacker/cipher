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