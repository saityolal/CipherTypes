package MonoalphabeticalCipher;

import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MonoalphabeticCipherEncryptionTurkish {

    public static void main(String[] args) {
        Random random = new Random();
        int key;
        do {
            key = random.nextInt(28);
        } while (key == 0);

        char[] alphabet = {'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'ğ', 'h', 'ı', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'ö', 'p', 'r',
                's', 'ş', 't', 'u', 'ü', 'v', 'y', 'z'};

        System.out.println("The Key is: " + key);

        try {
            FileOutputStream fos = new FileOutputStream("C:/Users/sado/Desktop/software optimization/software optimization/Encrypted Message Turkish.txt");
            PrintStream ps = new PrintStream(fos, false, UTF_8);
            FileInputStream fis = new FileInputStream(
                    "C:/Users/sado/Desktop/software optimization/software optimization/Alice Message Turkish.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer("");
            StringBuffer encryptedText = new StringBuffer("");

            String row;
            while ((row = br.readLine()) != null) {
                sb.append(row);
            }


            String content = sb.toString();
            System.out.println(content);
            int index = 0;
            for (char c : content.toCharArray()) {

                if (Character.isLetter(c)) {

                    for (int i = 0; i < alphabet.length; i++) {
                        if (c == alphabet[i]) {
                            index = i;
                        }


                    }
                    c = alphabet[(index + key) % 29];
                    encryptedText.append(c);

                } else {
                    encryptedText.append(" ");
                }
            }


            System.out.println(encryptedText);


            ps.println(encryptedText);

        } catch (Exception e) {
            System.out.println("Hata  : " + e);
        }



    }

}
