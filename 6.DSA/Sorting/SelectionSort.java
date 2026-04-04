
public class SelectionSort {

    public static void main(String[] args) {
        int nums[] = {6, 7, 4, 3, 1, 8, 13, 2};

        System.out.println("Before Sorting");
        for (int num : nums) {
            System.out.print(num + " ");
        }

        for (int i = 0; i < nums.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < nums.length; j++) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }

            // swap
            int temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;

            // print array
            System.out.println();
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
