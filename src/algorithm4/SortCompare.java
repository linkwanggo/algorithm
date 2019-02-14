package algorithm4;

import algorithm4.chapter_1.Insertion;
import algorithm4.chapter_1.Selection;
import algorithm4.chapter_1.Shell;
import algorithm4.chapter_2.Merge;
import algorithm4.chapter_2.MergeBU;
import algorithm4.chapter_2.Quick;
import util.StdRandom;
import util.Stopwatch;

public class SortCompare {
    public static double time(String alg, Double[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Selection")) {
            Selection.sort(a);
        }
        if (alg.equals("Insertion")) {
            Insertion.sort(a);
        }
        if (alg.equals("Shell")) {
            Shell.sort(a);
        }
        if (alg.equals("Merge")) {
            Merge.sort(a);
        }
        if (alg.equals("MergeBU")) {
            MergeBU.sort(a);
        }
        if (alg.equals("Quick")) {
            Quick.sort(a);
        }
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        // 使用算法alg将T个长度为N的数组排序
        double total = 0;
        Double[] a = new Double[N];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < N; j++) {
                a[j] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        System.out.println(t1);
        System.out.println(t2);
    }
}
