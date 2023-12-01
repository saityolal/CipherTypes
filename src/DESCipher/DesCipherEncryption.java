package DESCipher;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.String.valueOf;

public class DesCipherEncryption {

    public static void main(String[] args) {


        try {
            FileInputStream fis = new FileInputStream("C:/Users/sado/Desktop/software optimization/software optimization/Alice Message.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer plaintext = new StringBuffer();

            int i = 0;
            char c;
            while ((i = br.read()) >= 0) {
                c = (char) i;
                if (Character.isLetter(c)) {
                    plaintext.append(c);
                } else {
                    plaintext.append(" ");
                }

            }
            // byte[] plainTextByte = plaintext.toString().getBytes("UTF-8");
            char[] plainTextChar = plaintext.toString().toLowerCase().toCharArray();
            String[] plainTextStringArray = get_bits(plainTextChar);

//            for (String s : plainTextStringArray) {
//                System.out.println("plainTextStringArray member :" + s);
//            }


//            for (byte b : plainTextByte) {
//                System.out.println("BYTE " + b);
//            }
//            int remain = plainTextStringArray.length % 8;//1875    remain is 3
//            System.out.println(remain);

//            ArrayList<String> right_half = new ArrayList<>();
//            ArrayList<String> left_half = new ArrayList<>();

            ArrayList<String> right_left = new ArrayList<>();
            ArrayList<String> keys = new ArrayList<>();
            int n = 0;
            for (int m = 0; m + 8 < plainTextStringArray.length; m = m + 8) {
                String letter1 = plainTextStringArray[m];
                String letter2 = plainTextStringArray[m + 1];
                String letter3 = plainTextStringArray[m + 2];
                String letter4 = plainTextStringArray[m + 3];

                String letter5 = plainTextStringArray[m + 4];
                String letter6 = plainTextStringArray[m + 5];
                String letter7 = plainTextStringArray[m + 6];
                String letter8 = plainTextStringArray[m + 7];

                System.out.println("\n");
                System.out.println("Initial permutation progress starting...");

                right_left = initial_permutation(letter1, letter2, letter3, letter4, letter5, letter6, letter7, letter8);
                String right = right_left.get(n);
                String left = right_left.get(n + 1);
              //  String right_after_expension = expansion_permutation(right);


                keys = key_generator();
                String key_right = keys.get(n);
                System.out.println("Right key is : " + key_right);

                String key_left = keys.get(n + 1);
                System.out.println("Left key is : " + key_left);


                System.out.println(" key is : " + key_right + key_left);


                String key = key_right + key_left;


                ArrayList<String> permuted_keys = permuteChoice1(key);
                String pc1_key_right = permuted_keys.get(n);
                String pc1_key_left = permuted_keys.get(n + 1);
                //  String permuted_key = permuted_key_right + permuted_key_left;
                //  System.out.println("After PC-1  : " + permuted_key_right + permuted_key_left);

                String shifted_key_right;
                String shifted_key_left;

                String round_key;
                for (int j = 0; j < 16; j++) {
                    if (j == 0 || j == 1 || j == 8 || j == 15) {
                        shifted_key_right = leftCircularShiftBy1(pc1_key_right);
                        shifted_key_left = leftCircularShiftBy1(pc1_key_left);
                        //System.out.println("After Shifted  BY 1: " + shifted_key);
                    } else {
                        shifted_key_right = leftCircularShiftBy2(pc1_key_right);
                        shifted_key_left = leftCircularShiftBy2(pc1_key_left);
                        // System.out.println("After Shifted BY 2: " + shifted_key);
                    }

                    round_key = permuteChoice2(shifted_key_right + shifted_key_left);
                    System.out.println("after PC2 ROUND key is :  " + round_key);

//                   int x =  Integer.parseInt(round_key)  ^ Integer.parseInt(right_after_expension);
//                    System.out.println("XXXXXX"+x);

//                    char[] xored_char_array = new char[round_key.length()];
//                    char[] round = round_key.toCharArray();
//                    char[] rae = right_after_expension.toCharArray();

                    // System.out.println(right_after_expension.length() + " " + round_key.length());
                    String right_after_expension = expansion_permutation(right);
                    String xored_str = xorBinaryStrings(round_key, right_after_expension);

                    String after_substitution = key_substitution(xored_str);
                    System.out.println("After  substitution : " + after_substitution);

                    String after_transposition = permutation_transposition(after_substitution);
                    System.out.println("After  transposition : " + after_transposition);

                     right = xorBinaryStrings(after_transposition, after_transposition);
                    left= right;
                    right = xorBinaryStrings(right, left);

                    System.out.println("Checking: " + right);

//                    System.out.println("result : " + result);


//                    for (int k = 0; k < right_after_expension.length(); k++) {
////                               int a = Character.getNumericValue(round[k]) ^ Character.getNumericValue(rae[k]);
////                               System.out.println("Xorlu hali " +);
////                                char xored = (char)a;
////                                xored_char_array[k]=xored;
////
////                        System.out.println(xored_char_array[k]);
//                        // String l=round_key.charAt(k);
////                        int l = Integer.parseInt(round_key);
////
////
////                        int x =  round[k];
////                        int y = rae[k];
////                        int q = x ^ y ;
////                        String charAsString1 = Character.toString((char) x );
////
////                        System.out.println("charASString1 is :  "+ l);
//
//
//                    }
                }


                //  String shiftedPermutedKey = leftCircularShiftBy1(permuted_key);


//                System.out.println("Original String: " + sb.toString());
//                System.out.println("Shifted String: " + shiftedPermutedKey);

                String swapped_after_inverse_initial_permutation = Inverse_initial_permutation(right,left);

                n++;

            }

            String s = "01101001";
            get_char(s);
            //Inverse_initial_permutation(right, left);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String permutation_transposition(String str) {
        int[] transposion_table = {
                16, 7, 20, 21, 29, 12,
                28, 17, 1, 15, 23, 26,
                5, 18, 31, 10, 2, 8,
                24, 14, 32, 27, 3, 9,
                19, 13, 30, 6, 22, 11,
                4, 25
        };
        List<Integer> forIndexList = Arrays.asList(Arrays.stream(transposion_table).boxed().toArray(Integer[]::new));

        char[] after_transposition = new char[transposion_table.length];
        char[] str_char = str.toCharArray();
        String str_aft = "";
        for (int i = 0; i < transposion_table.length; i++) {

            after_transposition[forIndexList.indexOf(i + 1)] = str_char[i];

        }
        for (char c : after_transposition) {
            str_aft += Character.toString(c);
        }

        return str_aft;
    }


    public static String xorBinaryStrings(String s1, String s2) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s1.length(); i++) {

            result.append(s1.charAt(i) ^ s2.charAt(i));
        }

        return result.toString();
    }


    public static String leftCircularShiftBy1(String str) {
        // For round 1,2,9,16
        String shiftedString = str.substring(1) + str.substring(0, 1);

        return shiftedString;
    }

    public static String leftCircularShiftBy2(String str) {
        // For all round except 1,2,9,16
        String shiftedString = str.substring(2) + str.substring(0, 2);

        return shiftedString;
    }

    public static ArrayList<String> permuteChoice1(String str) {
        // 8, 16, 24, 32, 40, 48, 56, 64
        StringBuilder sBuilder = new StringBuilder(str);
        sBuilder.deleteCharAt(7);
        sBuilder.deleteCharAt(14);
        sBuilder.deleteCharAt(21);
        sBuilder.deleteCharAt(28);
        sBuilder.deleteCharAt(35);
        sBuilder.deleteCharAt(42);
        sBuilder.deleteCharAt(49);
        sBuilder.deleteCharAt(56);
        ArrayList<String> result = new ArrayList<String>();
        String pc1_right = sBuilder.toString().substring(0, 27);
        String pc1_left = sBuilder.toString().substring(27);
        result.add(pc1_right);
        result.add(pc1_left);
        return result;

    }

    public static String permuteChoice2(String str) {
        // 9, 18, 22, 25, 35, 38, 43, 54
        StringBuilder sBuilder = new StringBuilder(str);
        sBuilder.deleteCharAt(8);
        sBuilder.deleteCharAt(16);
        sBuilder.deleteCharAt(19);
        sBuilder.deleteCharAt(21);
        sBuilder.deleteCharAt(30);
        sBuilder.deleteCharAt(32);
        sBuilder.deleteCharAt(36);
        sBuilder.deleteCharAt(46);

        return sBuilder.toString();
    }

    public static String[] get_bits(char c[]) {
        String[] bits = new String[c.length];
        for (int i = 0; i < c.length; i++) {

            int ascii_value = (int) c[i];
            String a = Integer.toBinaryString(ascii_value);
            if (Integer.toBinaryString(ascii_value).length() < 7) {
                a = "00" + a;
                bits[i] = a;
            } else {
                a = "0" + a;
                bits[i] = a;
            }

            //   String first_part = a.substring(0, 4);
            //   String Second_part = a.substring(4);
            // System.out.println(a);

        }
        return bits;
    }

    public static void get_char(String binaryString) {
        int ascii_value = Integer.parseInt(binaryString, 2);
        char c = (char) ascii_value;
        System.out.println("Character by given bits is : " + c);
    }

    public static ArrayList<String> key_generator() {
        ArrayList<String> keyList = new ArrayList<String>();
        Random random = new Random();
        String key = "";
        for (int i = 0; i < 64; i++) {
            key += String.valueOf(random.nextInt(2));
            //key += " ";

        }
        String rightHalfKey = key.substring(0, 32);
        String leftHalfKey = key.substring(32);

        keyList.add(rightHalfKey);
        keyList.add(leftHalfKey);
        return keyList;
    }


    public static ArrayList<String> initial_permutation(String letter1, String letter2, String letter3, String letter4, String letter5, String letter6, String letter7, String letter8) {
        int[] table_64bit = {
                1, 2, 3, 4, 5, 6, 7, 8,
                9, 10, 11, 12, 13, 14, 15, 16,
                17, 18, 19, 20, 21, 22, 23, 24,
                25, 26, 27, 28, 29, 30, 31, 32,
                33, 34, 35, 36, 37, 38, 39, 40,
                41, 42, 43, 44, 45, 46, 47, 48,
                49, 50, 51, 52, 53, 54, 55, 56,
                57, 58, 59, 60, 61, 62, 63, 64
        };
        int[] initialPermutationTable = {
                58, 50, 42, 34, 26, 18, 10, 2,
                60, 52, 44, 36, 28, 20, 12, 4,
                62, 54, 46, 38, 30, 22, 14, 6,
                64, 56, 48, 40, 32, 24, 16, 8,
                57, 49, 41, 33, 25, 17, 9, 1,
                59, 51, 43, 35, 27, 19, 11, 3,
                61, 53, 45, 37, 29, 21, 13, 5,
                63, 55, 47, 39, 31, 23, 15, 7
        };

        ArrayList<String> right_left = new ArrayList<>();


        char[] after_permutation = new char[initialPermutationTable.length];
        List<Integer> forIndexList = Arrays.asList(Arrays.stream(initialPermutationTable).boxed().toArray(Integer[]::new));

        String[] letters = {letter1, letter2, letter3, letter4, letter5, letter6, letter7, letter8};

        int index = 1;
        for (int i = 0; i < 8; i++) {
            char[] letter_chars = letters[i].toCharArray();
            for (int j = 0; j < 8; j++) {
                after_permutation[forIndexList.indexOf(index)] = letter_chars[j];
                index++;
            }
        }


//        int every8th = 0;
//        for (char c : after_permutation) {
//            System.out.print(c);
//            every8th = (every8th + 1) % 8;
//            if (every8th == 0) {
//                System.out.println("\n");
//            }
//        }

        // for first four letters
        char[] forRightHalf = new char[32];
        for (int i = 0; i < 32; i++) {
            forRightHalf[i] = after_permutation[i];
        }
        String forRightHalf_str = String.valueOf(forRightHalf);


//        String part1 = forRightHalf_str.substring(0, 8);
//        String part2 = forRightHalf_str.substring(8, 16);
//        String part3 = forRightHalf_str.substring(16, 24);
//        String part4 = forRightHalf_str.substring(24);
//
//        System.out.println(part1 + " " + part2 + " " + part3 + " " + part4);


        // for other four letters
        char[] forLeftHalf = new char[32];
        for (int i = 32; i < 64; i++) {
            forLeftHalf[i - 32] = after_permutation[i];
        }
        String forLeftHalf_str = String.valueOf(forLeftHalf);


        right_left.add(forRightHalf_str);
        right_left.add(forLeftHalf_str);


        return right_left;


//        int[] after_permutation = new int[initialPermutationTable.length];
//
//
//        List<Integer> forIndexList = Arrays.asList(Arrays.stream(initialPermutationTable).boxed().toArray(Integer[]::new));
//
//        System.out.println(letter1);
//        char[] letter1_char = letter1.toCharArray();
//        for (int i = 0; i < letter1_char.length; i++) {
//            System.out.println("index " + i + " " + letter1_char[i]);
//        }
//        char[] letter2_char = letter2.toCharArray();
//        char[] letter3_char = letter3.toCharArray();
//        char[] letter4_char = letter4.toCharArray();
//
//        char[] letter5_char = letter5.toCharArray();
//        char[] letter6_char = letter6.toCharArray();
//        char[] letter7_char = letter7.toCharArray();
//        char[] letter8_char = letter8.toCharArray();
//
//        for (int i = 0; i < 8; i++) {
//            after_permutation[forIndexList.indexOf(i + 1)] = letter1_char[i];
//        }
//        for (int i = 8; i < 16; i++) {
//            after_permutation[forIndexList.indexOf(i + 1)] = letter2_char[i - 8];
//        }
//        for (int i = 16; i < 24; i++) {
//            after_permutation[forIndexList.indexOf(i + 1)] = letter3_char[i - 16];
//        }
//        for (int i = 24; i < 32; i++) {
//            after_permutation[forIndexList.indexOf(i + 1)] = letter4_char[i - 24];
//        }
//        for (int i = 32; i < 40; i++) {
//            after_permutation[forIndexList.indexOf(i + 1)] = letter5_char[i - 32];
//        }
//        for (int i = 40; i < 48; i++) {
//            after_permutation[forIndexList.indexOf(i + 1)] = letter6_char[i - 40];
//        }
//        for (int i = 48; i < 56; i++) {
//            after_permutation[forIndexList.indexOf(i + 1)] = letter7_char[i - 48];
//        }
//        for (int i = 56; i < 64; i++) {
//            after_permutation[forIndexList.indexOf(i + 1)] = letter8_char[i - 56];
//        }
//        System.out.println(after_permutation);
//
//        after_permutation[7] = letter1_char[1];
//        System.out.println(after_permutation[7]);


//        String letter1_part1 = letter1.substring(0,4);
//        String letter1_part2 = letter1.substring(4);


        // System.out.println(letter1_part1 +" " + letter1_part2);
        //System.out.println("  :" + letter1 + " " + letter2 + " " + letter3 + " " + letter4 + " " + letter5 + " " + letter6 + " " + letter7 + " " + letter8);

    }

    public static String expansion_permutation(String right_half) {
        int[] table_64bit = {
                1, 2, 3, 4, 5, 6, 7, 8,
                9, 10, 11, 12, 13, 14, 15, 16,
                17, 18, 19, 20, 21, 22, 23, 24,
                25, 26, 27, 28, 29, 30, 31, 32,
                33, 34, 35, 36, 37, 38, 39, 40,
                41, 42, 43, 44, 45, 46, 47, 48,
                49, 50, 51, 52, 53, 54, 55, 56,
                57, 58, 59, 60, 61, 62, 63, 64
        };
        int[] expansionTable = {
                32, 1, 2, 3, 4, 5,
                4, 5, 6, 7, 8, 9,
                8, 9, 10, 11, 12, 13,
                12, 13, 14, 15, 16, 17,
                16, 17, 18, 19, 20, 21,
                20, 21, 22, 23, 24, 25,
                24, 25, 26, 27, 28, 29,
                28, 29, 30, 31, 32, 1
        };


        char[] after_expansion = new char[expansionTable.length];
        //   List<Integer> forIndexList = Arrays.asList(Arrays.stream(expansionTable).boxed().toArray(Integer[]::new));
        // right half example 1(1) 1 1 1 1 1 0 1(8) 0 0 0 0 0 1 0 0(16) 0 1 1 1 1 0 0 0(24) 0 1 1 1 1 0 0 1(32)

        after_expansion[0] = right_half.charAt(31);

        after_expansion[1] = right_half.charAt(0);
        after_expansion[2] = right_half.charAt(1);
        after_expansion[3] = right_half.charAt(2);
        after_expansion[4] = right_half.charAt(3);
        after_expansion[5] = right_half.charAt(4);
        after_expansion[6] = right_half.charAt(3);
        after_expansion[7] = right_half.charAt(4);
        after_expansion[8] = right_half.charAt(5);

        after_expansion[9] = right_half.charAt(6);
        after_expansion[10] = right_half.charAt(7);
        after_expansion[11] = right_half.charAt(8);
        after_expansion[12] = right_half.charAt(7);
        after_expansion[13] = right_half.charAt(8);
        after_expansion[14] = right_half.charAt(9);
        after_expansion[15] = right_half.charAt(10);
        after_expansion[16] = right_half.charAt(11);

        after_expansion[17] = right_half.charAt(12);
        after_expansion[18] = right_half.charAt(11);
        after_expansion[19] = right_half.charAt(12);
        after_expansion[20] = right_half.charAt(13);
        after_expansion[21] = right_half.charAt(14);
        after_expansion[22] = right_half.charAt(15);
        after_expansion[23] = right_half.charAt(16);
        after_expansion[24] = right_half.charAt(15);

        after_expansion[25] = right_half.charAt(16);
        after_expansion[26] = right_half.charAt(17);
        after_expansion[27] = right_half.charAt(18);
        after_expansion[28] = right_half.charAt(19);
        after_expansion[29] = right_half.charAt(20);
        after_expansion[30] = right_half.charAt(19);
        after_expansion[31] = right_half.charAt(20);
        after_expansion[32] = right_half.charAt(21);

        after_expansion[33] = right_half.charAt(22);
        after_expansion[34] = right_half.charAt(23);
        after_expansion[35] = right_half.charAt(24);
        after_expansion[36] = right_half.charAt(23);
        after_expansion[37] = right_half.charAt(24);
        after_expansion[38] = right_half.charAt(25);
        after_expansion[39] = right_half.charAt(26);
        after_expansion[40] = right_half.charAt(27);

        after_expansion[41] = right_half.charAt(28);
        after_expansion[42] = right_half.charAt(27);
        after_expansion[43] = right_half.charAt(28);
        after_expansion[44] = right_half.charAt(29);
        after_expansion[45] = right_half.charAt(30);
        after_expansion[46] = right_half.charAt(31);

        after_expansion[47] = right_half.charAt(0);
//        //int i=0;
//       for (char c : after_expansion) {
//          // i++;
//           System.out.println("after expansion " + c);
//         //  System.out.println("Bit sayisi  " + i);
//       }
        //System.out.println("after expansion"+ after_expansion[1]);
        String str = new String(after_expansion);
//        System.out.println("now  str "+str);
        return str;
    }


    public static String Inverse_initial_permutation(String right, String left) {
        int[] inverseInitialPermutationTable = {
                40, 8, 48, 16, 56, 24, 64, 32,
                39, 7, 47, 15, 55, 23, 63, 31,
                38, 6, 46, 14, 54, 22, 62, 30,
                37, 5, 45, 13, 53, 21, 61, 29,
                36, 4, 44, 12, 52, 20, 60, 28,
                35, 3, 43, 11, 51, 19, 59, 27,
                34, 2, 42, 10, 50, 18, 58, 26,
                33, 1, 41, 9, 49, 17, 57, 25
        };
        String bitswap32 = left + right;

        List<Integer> forIndexList = Arrays.asList(Arrays.stream(inverseInitialPermutationTable).boxed().toArray(Integer[]::new));
            char[] before_inverse_initial_permutation = bitswap32.toCharArray();
            char[]  after_inverse_initial_permutation = new char[before_inverse_initial_permutation.length];
        for (int i = 0; i <inverseInitialPermutationTable.length ; i++) {
                        after_inverse_initial_permutation[forIndexList.indexOf(i+1)]=before_inverse_initial_permutation[i];
        }
            String ciphered=new String(after_inverse_initial_permutation);

            System.out.println("Final Form : " + ciphered);
                return ciphered;

    }


    public static String key_substitution(String str) {
        int[][] s1Box = {
                {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
        };
        int[][] s2Box = {
                {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
        };
        int[][] s3Box = {
                {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
        };
        int[][] s4Box = {
                {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
        };
        int[][] s5Box = {
                {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
        };
        int[][] s6Box = {
                {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
        };
        int[][] s7Box = {
                {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
        };
        int[][] s8Box = {
                {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
        };
        // System.out.println(" STR : " + str );
        String result = "";
        String[] sub_str = new String[8];
        for (int i = 0; i < 8; i++) {
            String Scolumn = "";
            String Srow = "";
            if (i != 7) {
                sub_str[i] = str.substring(i * 6, 6 * i + 6);
            }
            if (i == 7) {
                sub_str[i] = str.substring(i * 6);
            }
            System.out.println(" SUB_STR : " + sub_str[i]);
            //StringBuilder sb = new StringBuilder(sub_str[i]);
            Scolumn += sub_str[i].charAt(0);
            Scolumn += sub_str[i].charAt(5);
            System.out.println("Scolumn " + Scolumn);
            Srow = sub_str[i].substring(1, 5);
            System.out.println("Srow   " + Srow);

            int col = Integer.parseInt(Scolumn, 2);
            int row = Integer.parseInt(Srow, 2);
            System.out.println("col and row respectively   " + col + " and " + row);
            String output = "";
            StringBuffer sb = new StringBuffer("");

            switch (i) {
                case 0:
                    sb.append(Integer.toBinaryString(s1Box[col][row]));
                    output = Integer.toBinaryString(s1Box[col][row]);
                    System.out.println("output : " + output);
                    break;
                case 1:
                    sb.append(Integer.toBinaryString(s2Box[col][row]));

                    output = Integer.toBinaryString(s2Box[col][row]);
                    System.out.println("output : " + output);
                    break;
                case 2:
                    sb.append(Integer.toBinaryString(s3Box[col][row]));
                    output = Integer.toBinaryString(s3Box[col][row]);
                    System.out.println("output : " + output);
                    break;
                case 3:
                    sb.append(Integer.toBinaryString(s4Box[col][row]));
                    output = Integer.toBinaryString(s4Box[col][row]);
                    System.out.println("output : " + output);
                    break;
                case 4:
                    sb.append(Integer.toBinaryString(s5Box[col][row]));
                    output = Integer.toBinaryString(s5Box[col][row]);
                    System.out.println("output : " + output);
                    break;
                case 5:
                    sb.append(Integer.toBinaryString(s6Box[col][row]));
                    output = Integer.toBinaryString(s6Box[col][row]);
                    System.out.println("output : " + output);
                    break;
                case 6:
                    sb.append(Integer.toBinaryString(s7Box[col][row]));
                    output = Integer.toBinaryString(s7Box[col][row]);
                    System.out.println("output : " + output);
                    break;
                case 7:
                    sb.append(Integer.toBinaryString(s8Box[col][row]));
                    output = Integer.toBinaryString(s8Box[col][row]);
                    System.out.println("output : " + output);
                    break;

                default:

                    break;
            }
//            if (output.length() == 3) {
//                output = '0' + output;
//            } else if (output.length() == 2) {
//                output = '0' + '0' + output;
//            } else if (output.length() == 1) {
//                output='0'+'0'+'0'+ output;
//            }

            while (output.length() < 4) {
                output = "0" + output;

            }
            while (sb.length() < 4) {
                sb.insert(0, 0);
            }
            String stroutput = sb.toString();
//            System.out.println("STRoutput : " + stroutput);
            result += stroutput;
        }

        return result;
//        str.substring(0,6);
//        str.substring(6,12);
//        str.substring(12,18);
//        str.substring(18,24);
//        str.substring(24,30);
//        str.substring(30,36);
//        str.substring(36,42);
//        str.substring(42);

    }


}
// every char is 8 bit also 1 byte