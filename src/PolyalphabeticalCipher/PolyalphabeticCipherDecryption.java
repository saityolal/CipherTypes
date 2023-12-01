package PolyalphabeticalCipher;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class PolyalphabeticCipherDecryption {


    public static void main(String[] args) {


        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        try {
            FileOutputStream fos = new FileOutputStream("C:/Users/sado/Desktop/software optimization/software optimization/Alice Message With PolyAlphabeticCipher Decrypted.txt", false);
            PrintStream ps = new PrintStream(fos, true, StandardCharsets.UTF_8);

            FileInputStream fis_key = new FileInputStream("C:/Users/sado/Desktop/software optimization/software optimization/PolyAlphabeticCipher Key.txt");
            FileInputStream fis = new FileInputStream("C:/Users/sado/Desktop/software optimization/software optimization/Alice Message With PolyAlphabeticCipher Encrypted.txt");
            StringBuffer encyrptedText = new StringBuffer(" ");
            StringBuffer key = new StringBuffer(" ");


            Random random = new Random();
            int i = 0;
            char c;
            while ((i = fis.read()) >= 0) {
                c = (char) i;
                if (Character.isLetter(c)) {
                    encyrptedText.append(c);
                } else {
                    encyrptedText.append(" ");

                }

            }
            int j = 0;
            char k;
            while ((j = fis_key.read()) >= 0) {
                k = (char) j;
                if (Character.isLetter(k)) {
                    key.append(k);


                } else {
                    key.append(" ");

                }

            }


            System.out.println("The Key is : " + key);
            String key_str = key.toString();


            String plaintext = encyrptedText.toString();
            System.out.println("Encrypted text : " + plaintext);


            String decryptedText = PolyalphabeticDecryption(plaintext, key_str);
            System.out.println("DecryptedText : " + decryptedText);
            ps.println(decryptedText);
            ps.close();
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }

    }

    static int indexOfChar(char[] array, char target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

     static String PolyalphabeticDecryption(String text, String key) {

        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        StringBuffer decryptedText = new StringBuffer("");

//        char[] text_char = text.toCharArray();
//        char[] key_char = key.toCharArray();

        for (int i = 0; i < text.toCharArray().length; i++) {
            if (Character.isLetter(text.charAt(i))) {
                int temp = indexOfChar(alphabet, text.charAt(i));
                int temp2 = indexOfChar(alphabet, key.charAt(i));
                int index = (temp - temp2 + 26) % 26;
                decryptedText.append(alphabet[index]);
            } else {
                decryptedText.append(" ");
            }
        }
        return decryptedText.toString();
    }
}
