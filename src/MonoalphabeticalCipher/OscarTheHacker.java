package MonoalphabeticalCipher;
//import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.InputStreamReader;
import java.io.PrintStream;
//import java.util.StringTokenizer;

public class OscarTheHacker {
    public static void main(String[] args) {

        char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        int[] alphabet_number = new int[26];
        try {
            FileOutputStream fos = new FileOutputStream(
                    "C:/Users/sado/Desktop/software optimization/software optimization/Oscars.txt", false);
            PrintStream ps = new PrintStream(fos);

            FileInputStream fis = new FileInputStream(
                    "C:/Users/sado/Desktop/software optimization/software optimization/Encrypted Message.txt");

           // InputStreamReader isr = new InputStreamReader(fis);
          //  BufferedReader br = new BufferedReader(isr);

            String content = " ";

            int i;
            char c;

            while ((i = fis.read()) >= 0) {

                c = (char) i;
                content += c;

            }
            System.out.println("Text :" + content + "\n");

            for (char x : content.toCharArray()) {
                if (Character.isLetter(x) && x >= 'a' && x <= 'z') {
                    int remain = x - 'a'; // if letter is 'a' then remain must be 0
                    alphabet_number[remain]++;
                }
            }

            // For to find out number of most repeated char
            int most_repeated_char_number = 0;
            for (int k = 0; k < alphabet_number.length; k++) {
                for (int j = i + 1; j < alphabet_number.length; j++) {
                    if (alphabet_number[j] > alphabet_number[k]) {
                        most_repeated_char_number = alphabet_number[j];
                    }
                }
            }
            // System.out.println(most_repeated_char_number);

            // To find most repeated char first we need to check index
            char most_repeated_char = ' ';
            for (int index = 0; index < alphabet_number.length; index++) {
                if (alphabet_number[index] == most_repeated_char_number) {
                    most_repeated_char = alphabet[index];
                }
            }
            System.out.println("Most repeated char :  " + most_repeated_char + "\n");
            System.out.println("How many times repeated :  " + most_repeated_char_number + "\n");

            // Read words
            // String row;
            // String word;
            // int count = 0;
            // StringTokenizer st;
            // while ((row = br.readLine()) != null) {
            // st = new StringTokenizer(row);
            // while (st.hasMoreTokens()) {
            // if (st.nextToken().toString().endsWith("wjz")) {
            // count++;
            // }

            // }
            // }
            // System.out.println("Times used : " + count + "\n");

            // To find most used char
            // int index = 0;
            // System.out.println("Most used characters:");
            // for (char x = 'a'; x <= 'z'; x++) {
            // index = x - 'a';
            // if (0 <= index && index < 26 && alphabet_number[index] ==
            // most_repeated_char_number) {
            // System.out.println(x + ": " + alphabet_number[index] + " times");
            // }
            // }

            // for (int j : alphabet_number) {
            // System.out.println(alphabet_number[j] + "\n");
            // }
            // int most_used_char_number = 0;
            // for (int j : alphabet_number) {
            // if (alphabet_number[j] > most_used_char_number) {
            // most_used_char_number = alphabet_number[j];

            // }
            // }

            // char most_used_char;
            // int index = 0;
            // for (int k = 0; k < alphabet_number.length; k++) {
            // if (alphabet_number[k] == most_used_char_number) {
            // index = alphabet_number[k];
            // }
            // }

            // System.out.println("Most used char repeated times: " + alphabet_number[index]
            // + "\n");
            // System.out.println("Most used char's times " + most_used_char_number + "\n");
            // int most_used_char_number = 0;
            // for (int j : alphabet_number) {
            // if (alphabet_number[j] > most_used_char_number) {
            // most_used_char_number = alphabet_number[j];

            // int count = 0;

            // String temp = "";
            // String[] words = content.split(" ");
            // for (String s : words) {
            // // System.out.println(s);
            // if (s.endsWith("bgz")) {
            // count += 1;
            // temp += s;
            // temp += " ";
            // }
            // }
            // assume that bgz is ing then we should try reverse engineering, so key must be
            // 7 or 19

            // String decrypted = " ";

            // Most used Letter in English is 'e','t' and 'a' so lets presume that 'w' is
            // 'e' for now ifit doesn't work we need to try other letters
            // And now Oscar need to calculate the key number.
            int key = (most_repeated_char - 'e' +26)%26; // This will give the estimated Oscar the key number
            int key_2 = (most_repeated_char - 't' + 26)%26;
            int key_3 = (most_repeated_char - 'a' + 26)%26; 

            System.out.println("The Estimated keys are " + key + ", " + key_2 + ", " + key_3);

            // br.close();
            // isr.close();
            decrypt_message(key);
            decrypt_message(key_2);
            decrypt_message(key_3);
            fis.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

    }

    static void decrypt_message(int key) throws Exception {

        char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

        FileOutputStream fos = new FileOutputStream(
                "C:/Users/sado/Desktop/software optimization/software optimization/Oscars.txt", true);
        PrintStream ps = new PrintStream(fos);

        FileInputStream fis = new FileInputStream(
                "C:/Users/sado/Desktop/software optimization/software optimization/Encrypted Message.txt");

        // BufferedReader br = new BufferedReader(isr);
        StringBuffer sb = new StringBuffer(" ");
        int i = 0;
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

            sb.append(c);

        }
        ps.print(sb);
        ps.println("\n");

    }

    

}