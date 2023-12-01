package MonoalphabeticalCipher;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class MonoalphabeticCipherEncyrption {
    public static void main(String[] args) {

        Random random = new Random();
        int key;
        do {
            key = random.nextInt(25);
        } while (key == 0);

        char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

        System.out.println("the Key is: " + key);

        StringBuffer content = new StringBuffer("");
        try {
            FileOutputStream fos = new FileOutputStream(
                    "C:/Users/sado/Desktop/software optimization/software optimization/Encrypted Message.txt", false);
            PrintStream ps = new PrintStream(fos, true, StandardCharsets.UTF_8);
            FileInputStream fis = new FileInputStream(
                    "C:/Users/sado/Desktop/software optimization/software optimization/Alice Message.txt");
            int i;
            int count = 0;
            char c;
            while ((i = fis.read()) >= 0) {
                c = (char) i;
                c = Character.toLowerCase(c);
                for (int j = 0; j < alphabet.length; j++) {
                    if (alphabet[j] == c) {
                        int remain = (j + key) % 26;
                        c = alphabet[remain];
                        break;
                    }
                }

                content.append(c);
                count++;

            }
            fis.close();
            System.out.println("Number of letters: " + count);
            String text = content.toString();
            ps.print(text);

            System.out.println(text);
            ps.close();
        } catch (Exception e) {
            System.out.printf("Error  :  %s", e);
        }

    }
}