public class RSA{
	
	private static long modPow(long a, long b, long m){
		long result = 1;
		a%=m;
		while(b>0){
			if((b&1) == 1){
				result = (result * a) % m;
			}
			b >>= 1;
			a = (a * a) % m;
		}
		return result;
	}
	
	public static long encrypt(long plaintext, long e, long n){
		return modPow(plaintext, e, n);
	}
	
	public static long decrypt(long ciphertext, long d, long n){
		return modPow(ciphertext, d, n);
	}
	
	public static void main(String[] args){
		long p = 61;
		long q = 53;
		long n = p * q;
		long phi = (p-1) * (q-1);
		long d = 2753;
		long e = 17;
		
		long plaintext = 2222;
		long encrypted = encrypt(plaintext, e, n);
		long decrypted = decrypt(encrypted, d, n);
		
		System.out.println("Plaintext:"+plaintext);
		System.out.println("Encrypted:"+encrypted);
		System.out.println("Decrypted:"+decrypted);
		
	}
}