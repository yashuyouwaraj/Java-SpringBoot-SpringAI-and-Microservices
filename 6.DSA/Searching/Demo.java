
public class Demo {

    public static void main(String[] args) {
        int nums[] = {5, 7, 9, 11, 13, 15};

        int target = 15;

        int result = linearSearch(nums, target);
        int result1 = binarySearch(nums, target);

        if (result != -1) {
            System.out.println("Element found at Index by linearSearch: " + result);
        } else {
            System.out.println("Element not found");
        }

        if (result1 != -1) {
            System.out.println("Element found at Index by BinarySearch: " + result1);
        } else {
            System.out.println("Element not found");
        }

    }

    public static int linearSearch(int[] nums, int target) {
        int steps = 0;
        for (int i = 0; i < nums.length; i++) {
            steps++;
            if (nums[i] == target) {
                System.out.println("Steps taken by linear: "+ steps);
                return i;
            }
        }
        System.out.println("Steps taken by linear: "+ steps);
        return -1;
    }

    public static int binarySearch(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        int steps = 0;

        while (i <= j) {
            steps++;
            int mid = (i + j) / 2;
            if (nums[mid] == target) {
                System.out.println("Steps taken by binary: "+ steps);
                return mid;
            } else if (nums[mid] < target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }

        System.out.println("Steps taken by binary: "+ steps);

        return -1;
    }
}
