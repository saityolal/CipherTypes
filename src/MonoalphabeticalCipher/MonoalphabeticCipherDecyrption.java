package MonoalphabeticalCipher;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MonoalphabeticCipherDecyrption {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the key");
        int key = scan.nextInt();
        scan.close();

        char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        try {
            FileOutputStream fos = new FileOutputStream(
                    "C:/Users/sado/Desktop/software optimization/software optimization/Decrypted Message.txt", false);
            PrintStream ps = new PrintStream(fos, true, StandardCharsets.UTF_8);
            FileInputStream fis = new FileInputStream(
                    "C:/Users/sado/Desktop/software optimization/software optimization/Encrypted Message.txt");
            StringBuffer content = new StringBuffer("");

            int i;

            char c;
            while ((i = fis.read()) >= 0) {
                c = (char) i;
                for (int j = 0; j < alphabet.length; j++) {
                    if (alphabet[j] == c) {
                        int remain = 0;
                        if ((j - key) < 0) {
                            remain = j - key + 26;
                        } else {
                            remain = j - key;
                        }

                        c = alphabet[remain];
                        break;
                    }
                }
                
                content.append(c);

            }
            String text = content.toString();
            System.out.println(text);
            ps.print(text);
            ps.close();
            fis.close();
           
        } catch (Exception e) {
            System.out.printf("Error  :  %s", e);

        }

    }
}
