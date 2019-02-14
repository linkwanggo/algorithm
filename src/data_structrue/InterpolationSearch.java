package data_structrue;

/**
 * 插值查找：
 * 基本思想：基于二分查找，将查找点的选择改进为自适应选择，可以提高查找效率。 将二分查找的插值计算公式改为
 *
 * mid= low + (key - arr[low]) * (high - low) / (arr[high] - arr[low])
 * 时间复杂度：平均情况O（loglog(n)），最坏O（log(n)）
 *
 * 注：对于表长较大，而关键字分布又比较均匀的查找表来说，插值查找的平均性能比折半查找要好。
 */
public class InterpolationSearch {

    public int search(int[] arr, int key) {
        int count = 0;  // 次数
        int mid = 0;
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            System.out.println(String.format("count: %d", ++count));
            mid = low + (key - arr[low]) * (high - low) / (arr[high] - arr[low]) ;
            if (key < arr[mid]) {
                high = mid - 1;
            } else if (key > arr[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        InterpolationSearch binarySearch = new InterpolationSearch();
        System.out.println(binarySearch.search(new int[]{1, 16, 24, 35, 47, 59, 62, 73, 88, 99}, 88));
    }
}
