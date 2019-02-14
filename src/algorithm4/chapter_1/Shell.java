package algorithm4.chapter_1;

import algorithm4.base.BaseSort;

public class Shell extends BaseSort {

    /**
     * 视频讲解： https://www.bilibili.com/video/av15961896?from=search&seid=11476379667744279698
     * 基于插入排序
     * 选取一个增序序列 h
     * 每次使用插入排序对h间隔的数进行排序，使之趋近于有序序列 h会递减  例如 // 1, 4, 13, 40, 121
     * 算法基于：对于Dk间距的排序，再经过D(k-1)间距的排序后，D(k)仍然是有序序列
     * 最后将趋近于有序的序列进行正常的插入排序
     * @param a
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while(h < N/3) h = 3*h + 1;
        while (h > 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j-=h) {
                    exchange(a, j, j-h);
                }
            }
            h = h / 3;
        }
    }
}
