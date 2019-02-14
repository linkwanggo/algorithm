package algorithm4.chapter_1;

import algorithm4.base.BaseSort;
import util.In;
import util.StdIn;

public class Selection extends BaseSort {

    private Selection() {}

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i+1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exchange(a, i, min);
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Selection.sort(a);
        assert Selection.isSorted(a);
        Selection.show(a);
    }
}
