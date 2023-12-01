package PolyalphabeticalCipher;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.StringTokenizer;

public class PolyalphabeticCipherDecryptionTurkish {


    public static void main(String[] args) {


        char[] alphabet = {'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'ğ', 'h', 'ı', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'ö', 'p', 'r',
                's', 'ş', 't', 'u', 'ü', 'v', 'y', 'z'};

        try {
            FileOutputStream fos = new FileOutputStream("C:/Users/sado/Desktop/software optimization/software optimization/Alice Message With PolyAlphabeticCipher Decrypted Turkish.txt", false);
            PrintStream ps = new PrintStream(fos, true, StandardCharsets.UTF_8);


            FileInputStream fis_key = new FileInputStream("C:/Users/sado/Desktop/software optimization/software optimization/PolyAlphabeticCipher Key Turkish.txt");
            InputStreamReader isr_key = new InputStreamReader(fis_key, StandardCharsets.UTF_8);
            BufferedReader br_key = new BufferedReader(isr_key);


            FileInputStream fis = new FileInputStream("C:/Users/sado/Desktop/software optimization/software optimization/Alice Message With PolyAlphabeticCipher Encrypted Turkish.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);


            StringBuffer encyrptedText = new StringBuffer("");
            StringBuffer key = new StringBuffer("");


            Random random = new Random();
            int i = 0;
            char c;
            while ((i = br.read()) >= 0) {
                c = (char) i;
                if (Character.isLetter(c)) {
                    encyrptedText.append(c);
                } else {
                    encyrptedText.append(" ");

                }

            }
            int m = 0;
            char l;
            while ((m = br_key.read()) >= 0) {
                l = (char) m;
                if (Character.isLetter(l)) {
                    key.append(l);


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

    static String PolyalphabeticDecryption(String encryptedtext, String key) {

        char[] alphabet = {'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'ğ', 'h', 'ı', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'ö', 'p', 'r',
                's', 'ş', 't', 'u', 'ü', 'v', 'y', 'z'};
        StringBuffer decryptedText = new StringBuffer("");

        for (int i = 0; i < encryptedtext.toCharArray().length; i++) {
            if (Character.isLetter(encryptedtext.charAt(i))) {
                int temp = indexOfChar(alphabet, encryptedtext.charAt(i));
                int temp2 = indexOfChar(alphabet, key.charAt(i));
                int index = (temp - temp2 + alphabet.length) % alphabet.length;
                decryptedText.append(alphabet[index]);
            } else {
                decryptedText.append(" ");
            }
        }

        return decryptedText.toString();
    }
}
