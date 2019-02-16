package algorithm4.chapter_2;

import algorithm4.base.BaseSort;
import util.StdRandom;

/**
 * 思想：基于切分
 * 和快速切分不同的是，三项切分的思路是将小于切分元素的元素放在左侧（lo--lt）， 相同的切分元素放在中间（lt--i）大的元素放在右侧（i--gt）
 * 下次递归排序只需要递归左侧（lt--i）和 右侧（gt--hi）便可以最终实现排序
 * 优点：对于存在大量重复元素的数组，排序性能将表现的很好
 */
public class Quick3way extends BaseSort {

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) { return; }
        int lt = lo, i = lo+1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) { exchange(a, lt++, i++); }
            else if (cmp > 0) { exchange(a, i, gt--); }
            else { i++; }
        }
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{1,1,3,4,1,6,7,8,1};
        Quick3way.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
