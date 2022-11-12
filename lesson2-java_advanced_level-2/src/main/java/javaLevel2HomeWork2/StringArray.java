package javaLevel2HomeWork2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringArray {
    private static final int SIZE = 4;

    public static boolean onlyDigits(String str) {
        // Regex to check string
        // contains only digits
        String regex = "[0-9]+";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the string is empty
        // return false
        if (str == null) {
            return false;
        }

        // Find match between given string
        // and regular expression
        // using Pattern.matcher()
        Matcher m = p.matcher(str);

        // Return if the string
        // matched the ReGex
        return m.matches();
    }

    public void checkStringArrayForNumberedValues(String[][] array) {
        if (array.length != SIZE) {
            throw new MyArrayDataException();
        }
        for (int i = 0; i < SIZE; i++) {
            if (array[i].length != 4) {
                throw new MyArrayDataException();
            } else {
                int total = 0;
                int sizeArray = array.length;
                int[][] numberArray = new int[sizeArray][sizeArray];
                for (i = 0; i < sizeArray; i++) {
                    for (int j = 0; j < sizeArray; j++) {

                        if (onlyDigits(array[i][j])) {
                            numberArray[i][j] = Integer.parseInt(array[i][j]);
                            total += numberArray[i][j];
                        } else {
                            throw new MyArraySizeException(i, j, numberArray);
                        }
                    }
                }
                System.out.println(total);
            }
        }
    }
}



