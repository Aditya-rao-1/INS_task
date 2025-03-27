public class VigenereCipher {
    public static String encrypt(String pt, String key) {
        pt = pt.toUpperCase();
        key = key.toUpperCase();
        StringBuilder ciphertext = new StringBuilder();
        
        for (int i = 0, j = 0; i < pt.length(); i++) {
            char plainChar = pt.charAt(i);
            if (Character.isAlphabetic(plainChar)) {  
                int shift = key.charAt(j) - 'A';
                char cipherChar = (char) ('A' + (plainChar - 'A' + shift) % 26);
                ciphertext.append(cipherChar);
                j = (j + 1) % key.length();
            } else {
                ciphertext.append(plainChar); 
            }
        }

        return ciphertext.toString();
    }

    public static void main(String[] args) {
        String pt = "HELLO WORLD";
        String key = "KEY";
        System.out.println("Ciphertext: " + encrypt(pt, key));
    }
}
