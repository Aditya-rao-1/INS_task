import java.util.*;

public class HybridCipher {
    public static String substitutionEncrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < plaintext.length(); i++) {
            char p = plaintext.charAt(i);
            char k = key.charAt(i % keyLength);
            char c = (char) (((p - 'A') + (k - 'A')) % 26 + 'A');
            ciphertext.append(c);
        }

        return ciphertext.toString();
    }

    public static String substitutionDecrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            char k = key.charAt(i % keyLength);
            char p = (char) (((c - 'A') - (k - 'A') + 26) % 26 + 'A');
            plaintext.append(p);
        }

        return plaintext.toString();
    }

    public static String transpositionEncrypt(String text, String key) {
        int[] permutation = generatePermutation(key);
        int blockSize = permutation.length;
        StringBuilder ciphertext = new StringBuilder();

        while (text.length() % blockSize != 0) {
            text += 'X'; // Padding with 'X'
        }

        for (int i = 0; i < text.length(); i += blockSize) {
            char[] block = new char[blockSize];
            for (int j = 0; j < blockSize; j++) {
                block[j] = text.charAt(i + permutation[j]);
            }
            ciphertext.append(block);
        }

        return ciphertext.toString();
    }

    public static String transpositionDecrypt(String text, String key) {
        int[] permutation = generatePermutation(key);
        int blockSize = permutation.length;
        StringBuilder plaintext = new StringBuilder();
        int[] reversePermutation = new int[blockSize];

        for (int i = 0; i < blockSize; i++) {
            reversePermutation[permutation[i]] = i;
        }

        for (int i = 0; i < text.length(); i += blockSize) {
            char[] block = new char[blockSize];
            for (int j = 0; j < blockSize; j++) {
                block[reversePermutation[j]] = text.charAt(i + j);
            }
            plaintext.append(block);
        }

        return plaintext.toString();
    }

    private static int[] generatePermutation(String key) {
        int[] permutation = new int[key.length()];
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < key.length(); i++) {
            indices.add(i);
        }

        indices.sort(Comparator.comparingInt(key::charAt));

        for (int i = 0; i < key.length(); i++) {
            permutation[indices.get(i)] = i;
        }

        return permutation;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext (uppercase letters only): ");
        String plaintext = sc.nextLine().toUpperCase();

        System.out.print("Enter substitution key (uppercase letters only): ");
        String substitutionKey = sc.nextLine().toUpperCase();

        System.out.print("Enter transposition key (unique characters only): ");
        String transpositionKey = sc.nextLine();

        // Encryption
        String substitutionCiphertext = substitutionEncrypt(plaintext, substitutionKey);
        String hybridCiphertext = transpositionEncrypt(substitutionCiphertext, transpositionKey);
        System.out.println("Encrypted text: " + hybridCiphertext);

        // Decryption
        String reversedSubstitutionCiphertext = transpositionDecrypt(hybridCiphertext, transpositionKey);
        String originalPlaintext = substitutionDecrypt(reversedSubstitutionCiphertext, substitutionKey);
        System.out.println("Decrypted text: " + originalPlaintext);

        sc.close();
    }
}
