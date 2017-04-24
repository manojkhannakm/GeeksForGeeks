package algorithms;

import java.util.Arrays;

/**
 * @author Manoj Khanna
 */

public class SearchingAndSorting {

    private static void mergeSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }

        int m1 = l + (r - l) / 2,
                m2 = m1 + 1;
        mergeSort(a, l, m1);
        mergeSort(a, m2, r);

        int[] a1 = new int[m1 - l + 1];
        for (int i = 0; i < a1.length; i++) {
            a1[i] = a[l + i];
        }

        int[] a2 = new int[r - m2 + 1];
        for (int i = 0; i < a2.length; i++) {
            a2[i] = a[m2 + i];
        }

        int i = 0,
                j = 0,
                k = l;
        while (k <= r) {
            if (i < a1.length && j < a2.length) {
                if (a1[i] <= a2[j]) {
                    a[k++] = a1[i++];
                } else {
                    a[k++] = a2[j++];
                }
            } else if (i < a1.length) {
                a[k++] = a1[i++];
            } else if (j < a2.length) {
                a[k++] = a2[j++];
            }
        }
    }

    public static void mergeSort(int[] a) {
        mergeSort(a, 0, a.length - 1);
    }

    private static int pivot(int[] a, int l, int r) {
        int p = l;
        for (int i = l; i <= r - 1; i++) {
            if (a[i] <= a[r]) {
                int t = a[p];
                a[p] = a[i];
                a[i] = t;

                p++;
            }
        }

        int t = a[p];
        a[p] = a[r];
        a[r] = t;

        return p;
    }

    private static void quickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }

        int p = pivot(a, l, r);
        quickSort(a, l, p - 1);
        quickSort(a, p + 1, r);
    }

    public static void quickSort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    public static void main(String[] args) {
        int[] a = new int[]{50, 40, 60, 10, 20, 70, 30};
        mergeSort(a);
        System.out.println("Array: " + Arrays.toString(a));

        a = new int[]{50, 40, 60, 10, 20, 70, 30};
        quickSort(a);
        System.out.println("Array: " + Arrays.toString(a));
    }

}
