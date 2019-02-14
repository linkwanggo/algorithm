package data_structrue;

import java.util.ArrayList;
import java.util.List;

public class KMP {


    public int[] getNext(String pattern) {
        char[] patternArr = pattern.toCharArray();
        int i = 0, j = -1;
        int[] next = new int[patternArr.length];
        next[0] = -1;
        while(i < patternArr.length - 1) {
            if (j == -1 || patternArr[j] == patternArr[i]) {
                ++i;
                ++j;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }

    // 对 p = "aaaab"进行优化
    public int[] getNext2(String pattern) {
        char[] patternArr = pattern.toCharArray();
        int i = 0, j = -1;
        int[] next = new int[patternArr.length];
        next[0] = -1;
        while(i < patternArr.length - 1) {
            if (j == -1 || patternArr[j] == patternArr[i]) {
                ++i;
                ++j;
                if (patternArr[j] != patternArr[i]) {
                    next[i] = j;
                } else {
                    next[i] = next[j];
                }
            } else {
                j = next[j];
            }
        }
        return next;
    }

    public int indexKMP(String s, String pattern) {
        int i = 0, j = 0;
        char[] sArr = s.toCharArray();
        char[] patternArr = pattern.toCharArray();
        int[] next = getNext(pattern);
        while (j < pattern.length() && i < s.length()) {
            if (j == -1 || sArr[i] == patternArr[j]) {
                ++i;
                ++j;
            } else {
                j = next[j];
            }
        }
        if (j == pattern.length()) {
            return i - j;
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "abcdabcdaaab";
        String pattern = "aaab";
        KMP kmp = new KMP();
        for (int i: kmp.getNext2(pattern)) {
            System.out.print(String.format("%d ", i));
        }
        System.out.println(kmp.indexKMP(s, pattern));
//        System.out.println(kmp.indexKMP("aaaaa", "aa"));
    }

}
