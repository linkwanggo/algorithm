package algorithm4.chapter_1;

import algorithm4.SortCompare;
import algorithm4.base.BaseSort;
import util.StdIn;
import util.StdRandom;

public class Insertion extends BaseSort {
    /**
     * 基于交换思想 每次从当前位置的元素一直向前对比交换，直到换到合适的位置
     * @param a
     */

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exchange(a, j, j-1);
            }
        }
    }


    /**
     * 优化版 插入排序
     * 将arr[i]中的元素记录，与前面的所有元素进行比较，当发现位置不合适时，不进行交换，而是将前一个元素后移
     * 直到找到合适的位置后，将元素插入到该位置
     * @param arr
     */
    public static void insertionSort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Comparable t = arr[i];
            int j;
            for (j = i; j > 0 && less(t, arr[j-1]); j--) {
                arr[j] = arr[j-1];
            }
            arr[j] = t;
        }
    }

    public static void main(String[] args) {
        int n = 10000;
        Integer[] a = StdRandom.generateRandomArray(0, n, n);
        Insertion.sort(a);
        assert Insertion.isSorted(a);
        Insertion.show(a);

        // insertSort
        Integer[] a2 = StdRandom.generateRandomArray(0, n, n);
        Insertion.insertionSort(a2);
        assert Insertion.isSorted(a2);
        Insertion.show(a2);

        System.out.println("------------------------ Time Test----------------------------");
        System.out.println(SortCompare.time("Selection", StdRandom.generateRandomArray(0, n, n)));
        System.out.println(SortCompare.time("Insertion", StdRandom.generateRandomArray(0, n, n)));
        System.out.println(SortCompare.time("Insertion2", StdRandom.generateRandomArray(0, n, n)));
        System.out.println("------------------------ Time Test----------------------------");
        System.out.println(SortCompare.time("Selection", StdRandom.generateNearlyRandomArray(n, 100)));
        System.out.println(SortCompare.time("Insertion", StdRandom.generateNearlyRandomArray(n, 100)));
        System.out.println(SortCompare.time("Insertion2", StdRandom.generateNearlyRandomArray(n, 100)));

    }
}
