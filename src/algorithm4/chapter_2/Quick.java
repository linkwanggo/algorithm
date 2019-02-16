package algorithm4.chapter_2;

import algorithm4.base.BaseSort;
import util.StdRandom;

/**
 * 思想： 基于切分
 * 从数组中随机选取一个元素作为切点j （通常选取第一个元素）， 通过partition方法将数组中小于j的元素放在左侧，将大于j的元素放在右侧，将j放于中间位置
 * 接着对j的左右侧递归进行相同的切分操作，直到数组有序。
 */

public class Quick extends BaseSort {

    // TODO 明天学习快速排序的改进版  三向切分

    private static int partition(Comparable[] a, int lo, int hi) {
        /*
        使用 i指针向后移动，直到找到第一个大于a[lo]的元素， j指针一直向前直到找到第一个小于a[lo]的元素，交换并继续循环寻找， 直到得到左小右大的情况。
         */
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
        exchange(a, lo, j);  // 将a[lo]放到中间位置
        return j;
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) { return; }
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{1,1,3,4,1,6,7,8,1};
        Quick.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
