import java.util.Scanner;

public class playfaircipher {
    
    public static char[][] createMatrix(String key) {
        String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        
        key = key.toUpperCase().replace("J", "I");
        for (char c : (key + alphabet).toCharArray()) {
            if (sb.indexOf(String.valueOf(c)) == -1) {
                sb.append(c);
            }
        }
        
        char[][] matrix = new char[5][5];
        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = sb.charAt(index++);
            }
        }
        return matrix;
    }
    
    public static int[] findPosition(char[][] matrix, char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) return new int[]{i, j};
            }
        }
        return null;
    }
    
    public static String encrypt(String text, String key) {
        char[][] matrix = createMatrix(key);
        text = text.toUpperCase().replace("J", "I").replaceAll(" ", "");
        if (text.length() % 2 != 0) text += "X";
        
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i), b = text.charAt(i + 1);
            int[] posA = findPosition(matrix, a);
            int[] posB = findPosition(matrix, b);
            
            if (posA[0] == posB[0]) {
                cipher.append(matrix[posA[0]][(posA[1] + 1) % 5]);
                cipher.append(matrix[posB[0]][(posB[1] + 1) % 5]);
            } else if (posA[1] == posB[1]) {
                cipher.append(matrix[(posA[0] + 1) % 5][posA[1]]);
                cipher.append(matrix[(posB[0] + 1) % 5][posB[1]]);
            } else {
                cipher.append(matrix[posA[0]][posB[1]]);
                cipher.append(matrix[posB[0]][posA[1]]);
            }
        }
        return cipher.toString();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter key: ");
        String key = scanner.nextLine();
        System.out.print("Enter text: ");
        String text = scanner.nextLine();
        System.out.println("Ciphertext: " + encrypt(text, key));
    }
}
