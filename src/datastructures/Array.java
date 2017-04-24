package datastructures;

import java.util.Arrays;

/**
 * @author Manoj Khanna
 */

public class Array {

    private static int[] sumPair(int[] a, int s) {
        Arrays.sort(a);

        int l = 0,
                r = a.length - 1;
        while (l < r) {
            int al = a[l],
                    ar = a[r],
                    t = al + ar;
            if (t == s) {
                return new int[]{al, ar};
            } else if (t < s) {
                l++;
            } else if (t > s) {
                r--;
            }
        }

        return null;
    }

    private static int majorElement(int[] a) {
        int m = 0,
                c = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == a[m]) {
                c++;
            } else {
                c--;
            }

            if (c == 0) {
                m = i;
                c = 1;
            }
        }

        int am = a[m],
                cm = 0;
        for (int ai : a) {
            if (ai == am) {
                cm++;
            }
        }

        if (cm > a.length / 2) {
            return am;
        } else {
            return -1;
        }
    }

    private static int oddElement(int[] a) {
        int ao = 0;
        for (int ai : a) {
            ao ^= ai;
        }

        return ao;
    }

    public static void main(String[] args) {
        System.out.println("Sum pair: " + Arrays.toString(sumPair(new int[]{10, 40, 20, 50, 30}, 30)));

        System.out.println("Major Element: " + majorElement(new int[]{10, 30, 10, 10, 20}));

        System.out.println("Odd Element: " + oddElement(new int[]{10, 20, 10, 10, 20}));
    }

}
