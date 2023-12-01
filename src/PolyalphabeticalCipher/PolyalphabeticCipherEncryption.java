package PolyalphabeticalCipher;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class PolyalphabeticCipherEncryption {
    public static void main(String[] args) {


        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        try {
            FileOutputStream fos = new FileOutputStream("C:/Users/sado/Desktop/software optimization/software optimization/Alice Message With PolyAlphabeticCipher Encrypted.txt", false);
            PrintStream ps = new PrintStream(fos, true, StandardCharsets.UTF_8);

            FileOutputStream fos_key = new FileOutputStream("C:/Users/sado/Desktop/software optimization/software optimization/PolyAlphabeticCipher Key.txt", false);
            PrintStream ps_key = new PrintStream(fos_key, true, StandardCharsets.UTF_8);

            FileInputStream fis = new FileInputStream("C:/Users/sado/Desktop/software optimization/software optimization/Alice Message.txt");
            StringBuffer text = new StringBuffer(" ");
            StringBuffer key = new StringBuffer(" ");

            Random random = new Random();
            int i = 0;
            char c;
            while ((i = fis.read()) >= 0) {
                c = (char) i;
                if (Character.isLetter(c)) {
                    text.append(c);
                    char x = alphabet[random.nextInt(26)];
                    key.append(x);
                } else {
                    text.append(" ");
                    key.append(" ");
                }

            }
            System.out.println("The Key is : " + key);
            String key_str = key.toString();

            //System.out.println(text.toString());
            String plaintext = text.toString();
            System.out.println("Our text : " + plaintext);
            //System.out.println(plaintext.toString());

            //String key = Key_Generator(text.toString());
            //System.out.println(key.toString());

            String encryptedText = PolyalphabeticEncryption(plaintext, key_str);
            System.out.println("EncryptedText : " + encryptedText);
            ps.println(encryptedText);
            ps_key.println(key);
            ps.close();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
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

//    static String Key_Generator(String text) {
//        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
//                's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
//        Random random = new Random();
//        StringBuffer key = new StringBuffer("");
//        char c;
//        for (int i = 0; i < text.length(); i++) {
//            int index = random.nextInt(25);
//            c = alphabet[index];
//            key.append(c);
//        }
//
//        return key.toString();
//    }

    static String PolyalphabeticEncryption(String text, String key) {
        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        StringBuffer encryptedText = new StringBuffer("");

//        char[] text_char = text.toCharArray();
//        char[] key_char = key.toCharArray();

        for (int i = 0; i < text.toCharArray().length; i++) {
            if (text.charAt(i) != ' ') {
                int temp = indexOfChar(alphabet, text.charAt(i));
                int temp2 = indexOfChar(alphabet, key.charAt(i));
                int index = (temp + temp2 + 26) % 26;
                encryptedText.append(alphabet[index]);
            } else {
                encryptedText.append(" ");
            }
        }
        return encryptedText.toString();
    }
}
