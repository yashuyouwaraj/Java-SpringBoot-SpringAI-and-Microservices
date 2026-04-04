
public class Demo1 {

    public static void main(String[] args) {
        int nums[] = {5, 7, 9, 11, 13, 15};

        int target = 15;
        int result1 = binarySearch(nums, target, 0, nums.length - 1);

        if (result1 != -1) {
            System.out.println("Element found at Index by BinarySearch: " + result1);
        } else {
            System.out.println("Element not found");
        }

    }

    public static int binarySearch(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            return binarySearch(nums, target, mid + 1, right);
        } else {
            return binarySearch(nums, target, left, mid - 1);
        }
    }
}
