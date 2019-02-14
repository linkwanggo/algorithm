package algorithm4.chapter_2;

import algorithm4.base.BaseSort;

public class MergeBU extends BaseSort {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N-sz; lo += sz+sz) {
                merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
            }
        }
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid+1;
        // 将数组a复制到aux中去
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) { a[k] = aux[j++]; }
            else if (j > hi) { a[k] = aux[i++]; }
            else if (less(aux[i], aux[j])) { a[k] = aux[i++]; }
            else { a[k] = aux[j++]; }
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{3, 2, 1, 9, 8, 5, 4, 2, 2};
        MergeBU.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
