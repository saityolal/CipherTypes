package MonoalphabeticalCipher;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class OscarTheHackerTurkish {
    public static void main(String[] args) {

        char[] alphabet = {'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'ğ', 'h', 'ı', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'ö', 'p', 'r',
                's', 'ş', 't', 'u', 'ü', 'v', 'y', 'z'};
        int[] alphabet_number = new int[29];
        try {
            FileOutputStream fos = new FileOutputStream(
                    "C:/Users/sado/Desktop/software optimization/software optimization/Oscars Turkish.txt", false);
            PrintStream ps = new PrintStream(fos, false, StandardCharsets.UTF_8);

            FileInputStream fis = new FileInputStream(
                    "C:/Users/sado/Desktop/software optimization/software optimization/Encrypted Message Turkish.txt");
            InputStreamReader isr = new InputStreamReader(fis);

            BufferedReader br = new BufferedReader(isr);

            StringBuffer encryptedText = new StringBuffer("");


            String row;
            while ((row = br.readLine()) != null) {
                encryptedText.append(row);
            }
            System.out.println("encryptedText :" + encryptedText.toString() + "\n");

            // For every character how many times used
            for (char x : encryptedText.toString().toCharArray()) {
                if (Character.isLetter(x)) {
                    for (int i = 0; i < alphabet.length; i++) {
                        if (x == alphabet[i]) {
                            alphabet_number[i]++;
                        }
                    }

                }
            }

            // For to find out number of most repeated char
            int most_repeated_char_number = 0;
            for (int k = 0; k < alphabet_number.length; k++) {
                for (int j = k + 1; j < alphabet_number.length; j++) {
                    if (alphabet_number[j] > alphabet_number[k]) {
                        most_repeated_char_number = alphabet_number[j];
                    }
                }
            }
            // System.out.println(most_repeated_char_number);

            // To find most repeated char, first we need to check index
            char most_repeated_char = ' ';
            for (int index = 0; index < alphabet_number.length; index++) {
                if (alphabet_number[index] == most_repeated_char_number) {
                    most_repeated_char = alphabet[index];

                }
            }

            // Most repeated char's index
            int most_repeated_char_index=0;
            for (int i = 0; i < alphabet.length; i++) {
                if (alphabet[i] == most_repeated_char) {
                    most_repeated_char_index = i;
                }
            }





            System.out.println("Most repeated char :  " + most_repeated_char + "\n");
            System.out.println("How many times repeated :  " + most_repeated_char_number + "\n");
            System.out.println("Most repeated character index :  " + most_repeated_char_index + "\n");

            //

            // Most used Letter in Turkish is 'e','a', 'i' and goes  // Source https://www.sttmedia.com/characterfrequency-turkish
            // 'e' for now if it doesn't work we need to try other letters
            // And now Oscar need to calculate the key number.

            int key = (most_repeated_char_index - indexOfChar(alphabet,'e') + 29) % 29; // This will give the estimated Oscar the key number
            int key_2 = (most_repeated_char_index - indexOfChar(alphabet,'a') + 29) % 29;
            int key_3 = (most_repeated_char_index - indexOfChar(alphabet,'i') + 29) % 29;
            int key_4 = (most_repeated_char_index - indexOfChar(alphabet,'r') + 29) % 29;
            int key_5 = (most_repeated_char_index - indexOfChar(alphabet,'n') + 29) % 29;
            int key_6 = (most_repeated_char_index - indexOfChar(alphabet,'l') + 29) % 29;
            int key_7 = (most_repeated_char_index - indexOfChar(alphabet,'k') + 29) % 29;
            int key_8 = (most_repeated_char_index - indexOfChar(alphabet,'d') + 29) % 29;
            int key_9 = (most_repeated_char_index - indexOfChar(alphabet,'ı') + 29) % 29;
            int key_10 = (most_repeated_char_index - indexOfChar(alphabet,'o') + 29) % 29;



            System.out.println("The Estimated keys are " + key + ", " + key_2 + ", " + key_3 + ", " + key_4 + ", " + key_5 + ", " + key_6 + "," + key_7 );

            // br.close();
            // isr.close();

            decrypt_message(key,encryptedText.toString());
            decrypt_message(key_2,encryptedText.toString());
            decrypt_message(key_3,encryptedText.toString());
            decrypt_message(key_4,encryptedText.toString());
            decrypt_message(key_5,encryptedText.toString());
            decrypt_message(key_6,encryptedText.toString());
            decrypt_message(key_7,encryptedText.toString());
            decrypt_message(key_8,encryptedText.toString());
            decrypt_message(key_9,encryptedText.toString());
            decrypt_message(key_10,encryptedText.toString());

            fis.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

    }

    static void decrypt_message(int key,String encryptedText) throws Exception {

        char[] alphabet = {'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'ğ', 'h', 'ı', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'ö', 'p', 'r', 's', 'ş', 't', 'u', 'ü', 'v', 'y', 'z'};

        FileOutputStream fos = new FileOutputStream(
                "C:/Users/sado/Desktop/software optimization/software optimization/Oscars Turkish.txt", true);
        PrintStream ps = new PrintStream(fos, true, StandardCharsets.UTF_8);

        FileInputStream fis = new FileInputStream(
                "C:/Users/sado/Desktop/software optimization/software optimization/Encrypted Message Turkish.txt");

        // BufferedReader br = new BufferedReader(isr);
        StringBuffer decryptedText = new StringBuffer("");

        int index = 0;
        for (char c : encryptedText.toCharArray()) {
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

//
//        int i = 0;
//        char c;
//        while ((i = fis.read()) >= 0) {
//            c = (char) i;
//            if (Character.isLetter(c)) {
//                for (int j = 0; j < alphabet.length; j++) {
//                    if (alphabet[j] == c) {
//                        int remain = 0;
//                        if ((j - key) < 0) {
//                            remain = j - key + 29;
//                        } else {
//                            remain = j - key;
//                        }
//
//                        c = alphabet[remain];
//                        break;
//                    }
//                }
//
//                decryptedText.append(c);
//
//            }else{decryptedText.append(" ");}
//        }
        System.out.println("Decrypted Text is :" + decryptedText.toString());
        ps.print(decryptedText);
        ps.println("\n");

    }

    static int indexOfChar(char[] array, char target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

}