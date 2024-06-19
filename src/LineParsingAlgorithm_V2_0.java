/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author 341110716
 */
public class LineParsingAlgorithm_V2_0 {

    public static char[] tCheck = {'i', 's', 'd', 's', 'i', 'i', 'd'}; // 's' = string, 'i' = integer, 'd' = decimal

    public static String concat(String var, char c) {
        switch (c) {
            case 's':
                var = var.concat("String");
                return var;
            case 'd':
                var = var.concat("Decimal");
                return var;
            case 'i':
                var = var.concat("Integer");
                return var;
        }
        return "";
    }

    /**
     * Returns an boolean true or false value. The Integer argument must be a
     * non-negative value (as with all array indices). The String can be
     * anything
     * <p>
     * This method checks whether or not the String input is a decimal, Integer
     * or a String. The Integer input determines which of the 3 this program
     * will check for (with s for String, d for Decimal and i for Integer) The
     * boolean return is whether the String input is valid or not
     *
     * @param token the String token being determined
     * @param c the char that this program verifies to be ('s' for String, 'd'
     * for decimal, 'i' for integer)
     * @return the validity of the input String
     * @see String
     */
    public static boolean verify(String token, char c) {
        switch (c) {
            case 's': //String
                if ((65 <= token.charAt(0) && 90 >= token.charAt(0))
                        || (97 <= token.charAt(0) && token.charAt(0) <= 122)) {
                    System.out.println("Good input for String");
                    return true;
                } else {
                    System.out.println("Not a String when supposed to be String");
                }
                return false;
            case 'd': //Decimal
                try {
                    double d = Double.parseDouble(token);
                    System.out.println("Good input for Decimal");
                    return true;
                } catch (Exception e) {
                    System.out.println("Not a Decimal when supposed to be Decimal");
                }
                return false;
            case 'i': //Integer
                try {
                    Integer.parseInt(token);
                    System.out.println("Good input for Integer");
                    return true;
                } catch (Exception e) {
                    System.out.println("Not a Integer when supposed to be Integer");
                }
                return false;
            default:
                System.out.println("Error in verify");
                return false;
        }
    }

    public static void main(String[] args) {
        // user input scanner
        Scanner sc = new Scanner(System.in);
        do {
            // regenerate the array
            generateRandomSequenceMethodOne();
            // regenarate the display text
            String displayTxt = "Input: ";
            for (char c : tCheck) {
                displayTxt = concat(displayTxt, c);
                displayTxt = displayTxt.concat(", ");
            }
            // remove end character (should be ',', which is bad)
            displayTxt = displayTxt.substring(0, displayTxt.length() - 1);
            
            System.out.println(displayTxt);
            System.out.println("In that order IN ONE LINE SEPERATED BY COMMAS ','");
            String lineOfString = sc.nextLine().trim();
            StringTokenizer st = new StringTokenizer(lineOfString, ",");
            int x = 0;
            // checks the input of user
            while (st.hasMoreElements() && x < tCheck.length) {
                String s = st.nextToken();
                verify(s, tCheck[x]);
                x++;
            }
            // too many inputs then say too many
            if (tCheck.length < x + 1) {
                System.out.println("Too many inputs. Had " + (Integer.sum(st.countTokens(), tCheck.length)) + " readable values when should have " + tCheck.length);
            } else if (tCheck.length > x + 1) {
                System.out.println("Too little inputs. Had " + x + " readable values when should be " + tCheck.length);
            }
            System.out.println("Continue? [y/n]");
        } while (sc.nextLine().trim().charAt(0) == 'y');
    }

    /**
     *
     * Sets the global variable controlling inputs to the randomized output
     */
    private static void generateRandomSequenceMethodOne() {
        int length = (int) (29 * Math.random() + 1);
        int count = 0;
        while (length > count) {
            int number = (int) (2 * Math.random() + 1);
            char c = 'i';
            switch (number) {
                // String (s)
                case 1:
                    c = 's';
                    break;
                // Integer (i)
                case 2:
                    c = 'i';
                    break;
                // Decimal (d)
                case 3:
                    c = 'd';
                    break;
            }
            try {
                // Add another element into the array
                tCheck[count] = c;
            } catch (ArrayIndexOutOfBoundsException e) {
                //Can't add because the array is too small, so recreate the length with a new one
                // temporary storage for new guy
                char[] tCheckTemp = new char[count + 1];
                System.arraycopy(tCheck, 0, tCheckTemp, 0, tCheck.length);
                // set end value to the current char
                tCheckTemp[count] = c;
//                // populate temporary array with the values of the previous
//                for (int i = 0; i < count; i++) {
//                    tCheckTemp[i] = tCheck[i];
//                }
                tCheck = tCheckTemp;
            }
            count++;
        }
    }
}
