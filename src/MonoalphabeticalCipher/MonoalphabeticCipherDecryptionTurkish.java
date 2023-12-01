package MonoalphabeticalCipher;

import java.io.*;

import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MonoalphabeticCipherDecryptionTurkish {

    public static void main(String[] args) {
        System.out.println("Please Enter The Key");
        Scanner scan = new Scanner(System.in);
        int key = scan.nextInt();

        char[] alphabet = {'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'ğ', 'h', 'ı', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'ö', 'p', 'r',
                's', 'ş', 't', 'u', 'ü', 'v', 'y', 'z'};

        System.out.println("The Key is: " + key);

        try {
            FileOutputStream fos = new FileOutputStream("C:/Users/sado/Desktop/software optimization/software optimization/Decrypted Message Turkish.txt");
            PrintStream ps = new PrintStream(fos, false, UTF_8);
            FileInputStream fis = new FileInputStream(
                    "C:/Users/sado/Desktop/software optimization/software optimization/Encrypted Message Turkish.txt");
            InputStreamReader isr = new InputStreamReader(fis);

            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer("");

            StringBuffer decryptedText = new StringBuffer("");

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
                    if ((index - key) < 0) {
                        int remain = (index - key + 29) % 29;
                        c = alphabet[remain];
                    } else {
                        int remain = (index - key) % 29;
                        c = alphabet[remain];
                    }
                } else {
                    decryptedText.append(" ");
                }
                decryptedText.append(c);

            }
            System.out.println(decryptedText.toString());
            ps.println(decryptedText);
        } catch (Exception e) {
            System.out.println("Hata  : " + e);
        }


    }
}
