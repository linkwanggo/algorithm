package algorithm4.chapter_2;

import algorithm4.base.BaseSort;
import util.StdRandom;

public class Quick extends BaseSort {

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi+1;
        Comparable v = a[lo];
        while (true) {
            // 扫描左右，检查扫描是否结束并交换元素
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exchange(a, i, j);
        }
        exchange(a, lo, j);
        return j;
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi >= lo) { return; }
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{3, 2, 1, 9, 8, 5, 4, 2, 2};
        MergeBU.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
