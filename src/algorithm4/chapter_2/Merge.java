package algorithm4.chapter_2;

import algorithm4.base.BaseSort;

public class Merge extends BaseSort {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid); // 将左半部分排序
        sort(a, mid+1, hi); // 将右半部分排序
        merge(a, lo, mid, hi);
    }

    /**
     * 思想： 将一个数组a分为两半且两半内部均已有序， 当左半部分指针i超过mid时，说明左半部分已完成，取右半部分， 当右半部分用尽，取左半部分，
     * 如果左半部分比右半部分小，取左半部分，反之取右半部分
     * @param a
     * @param lo
     * @param mid
     * @param hi
     */
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
        Merge.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
