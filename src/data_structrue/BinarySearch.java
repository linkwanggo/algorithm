package data_structrue;

/**
 * 适用于已经排序的表查找， 由于每次查找折半， 所以时间复杂度为O(logN)
 */
public class BinarySearch {

    public int search(int[] arr, int key) {
        int count = 0;  // 次数
        int mid = 0;
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            System.out.println(String.format("count: %d", ++count));
            mid = (low + high) / 2; // 会自动向下取整
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
        BinarySearch binarySearch = new BinarySearch();
        System.out.println(binarySearch.search(new int[]{1, 16, 24, 35, 47, 59, 62, 73, 88, 99}, 59));
    }
}
