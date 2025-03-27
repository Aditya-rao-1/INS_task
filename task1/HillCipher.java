import java.util.Scanner;
public class HillCipher {
    private static int charToNum(char c) {
        return c - 'A';
    }
    private static char numToChar(int num) {
        return (char) (num + 'A');
    }
 private static int[] multiplyMatrix(int[][] matrix, int[] vector) {
        int n = matrix.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = 0;
            for (int j = 0; j < n; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
            result[i] %= 26;
        }
        return result;
    }
public static String encrypt(String pl, int[][] matrix) {
        pl = pl.toUpperCase().replaceAll("[^A-Z]", "");
        int n = matrix.length;
        StringBuilder ciphertext = new StringBuilder();
while (pl.length() % n != 0) {
            pl += 'X';
        }
for (int i = 0; i < pl.length(); i += n) {
            int[] vector = new int[n];
for (int j = 0; j < n; j++) {
                vector[j] = charToNum(pl.charAt(i + j));
            }
         int[] encryptedVector = multiplyMatrix(matrix, vector);
       for (int num : encryptedVector) {
                ciphertext.append(numToChar(num));
            }
        }
        return ciphertext.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
System.out.print("Enter pl: ");
        String pl = sc.nextLine();
        System.out.print("Enter key matrix n: ");
        int n = sc.nextInt();
int[][] matrix = new int[n][n];
        System.out.println("Enter key matrix values row-wise:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        String ciphertext = encrypt(pl, matrix);
        System.out.println("Ciphertext: " + ciphertext);
    }
}

