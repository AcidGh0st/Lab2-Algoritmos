package util;

import java.text.DecimalFormat;
import java.util.Random;

public class Utility {

    public static String format(double value){
        return new DecimalFormat("###,###,###.##").format(value);
    }

    public static String dolarFormat(double value){
        return new DecimalFormat("$###,###,###.##").format(value);
    }

    public static String show(int[] a, int size) {
        String result="";
        for (int i = 0; i < size; i++) {
            result+=a[i]+" ";
        }
        return result;
    }

    public static void fill(int[] a, int bound) {
        for (int i = 0; i < a.length; i++) {
            a[i] = new Random().nextInt(bound);
        }
    }
}
