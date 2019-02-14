package algorithm4.chapter_1;

import algorithm4.base.BaseSort;
import util.StdIn;

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

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Insertion.sort(a);
        assert Insertion.isSorted(a);
        Insertion.show(a);
    }
}
