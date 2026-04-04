
public class InsertionSort {

    public static void main(String[] args) {
        int nums[] = {6, 7, 4, 3, 1, 8, 13, 2};
        int temp;

        System.out.println("Before Sorting");
        for (int num : nums) {
            System.out.print(num + " ");
        }

        for (int i = 1; i < nums.length; i++) {
            temp = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > temp) {
                nums[j + 1] = nums[j];
                j = j - 1;
            }
            nums[j + 1] = temp;

            System.out.println("");
            for (int num : nums) {
                System.out.print(num + " ");
            }
        }

        System.out.println("");
        System.out.println("After Sorting");
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
