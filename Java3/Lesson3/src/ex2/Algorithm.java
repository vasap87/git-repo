package ex2;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by vasilenko.aleksandr on 25.07.2016.
 * Algorithms
 * Lesson 3 Java3
 */
public class Algorithm {

    /***
     * Method to find max element in array
     * @param arr Array of numbers
     * @return max element of array
     */
    public static double findMaxElementInArray(double[] arr ){
        double max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] > max) max=arr[i];
        }
        return max;
    }

    /**
     * Method reverse {@link String}
     * @return String with reverse {@link String}
     * @param s input {@link String}
     * */
    public static String reverseString(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = s.length()-1; i >=0 ; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    /**
     * Method find count of letters in input {@link String}
     * @return Hashtable<String, Integer>, where {@link String} is letter
     * {@link Integer} is count of this letter in input {@link String}
     * */
    public static Map<String, Integer> countLetters(String s){
        Map<String, Integer> resultMap = new Hashtable<>();
        StringBuilder sb = new StringBuilder(s);
        while (sb.length()>0){
            char ch = sb.charAt(0);
            int count = countCharsAtString(ch, sb.toString());
            sb = trimString(ch, sb.toString());
            resultMap.put(String.valueOf(ch),count);
        }
        return resultMap;

    }

    private static StringBuilder trimString(char ch, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(ch != s.charAt(i)) sb.append(s.charAt(i));
        }
        return sb;
    }


    private static int countCharsAtString(char ch, String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if(ch == s.charAt(i)) count++;
        }
        return count;
    }


}
