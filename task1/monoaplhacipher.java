import java.util.Scanner;

public class monoaplhacipher {
    public static char[] crtchat = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z' };
    public static char[] incrtchat = { 'z', 'y', 'x', 'w', 'v', 'u', 't', 's', 'r', 'q', 'p', 'o', 'n', 'm', 'l', 'k',
            'j', 'i', 'h', 'g', 'f', 'e',
            'd', 'c', 'b', 'a' };

    public static String encryptedcipher(String s, int key) {
        StringBuilder cipher = new StringBuilder();
        for (char c : s.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                int index = new String(crtchat).indexOf(c);
                if (index != -1)
                    cipher.append(incrtchat[index]);
                else
                    cipher.append(c);
            } else
                cipher.append(c);
        }
        return cipher.toString();
    }

    public static String decryptedcipher(String s, int key) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : s.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                int index = new String(incrtchat).indexOf(c);
                if (index != -1)
                    decrypted.append(crtchat[index]);
                else
                    decrypted.append(c);
            } else
                decrypted.append(c);
        }
        return decrypted.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the text: ");
        String s = sc.nextLine();
        int key = 0;
        String encrypted = encryptedcipher(s, key);
        String decrypted = decryptedcipher(encrypted, key);
        System.out.println("Original Text: " + s);
        System.out.println("Encrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + decrypted);
        sc.close();
    }
}