
import java.util.Scanner;

public class Caesar {
    public static String ccipherencrypt(String s,int key){
      String ciphertext=" ";
      for(int i=0;i<s.length();i++){
        char c=s.charAt(i);
          
        if(Character.isAlphabetic(c)){
           if (Character.isUpperCase(c)) c = (char) ((c - 'A' + key)  % 26 + 'A');
        else if (Character.isLowerCase(c))c = (char) ((c - 'a' + key)  % 26 + 'a');
        ciphertext+=c;
        }
      }
      return ciphertext;
    }
    public static String ccipherdecrypt(String s,int key){
        String ciphertext=" ";
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
              
            if (Character.isUpperCase(c)) 
                c = (char) (((c - 'A' - key + 26) % 26) + 'A');
            else if (Character.isLowerCase(c)) 
                c = (char) (((c - 'a' - key + 26) % 26) + 'a');
                ciphertext+=c;
            
          }
        return ciphertext;
    }

    public static void main(String[] args) {    
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the text: ");
        String s= sc.nextLine();
        int key=sc.nextInt();
        String encryptedStr=ccipherencrypt(s,key);
        System.out.println("output is: "+encryptedStr);
        System.out.println("decrypt: "+ ccipherdecrypt(encryptedStr,key));
    }
}
