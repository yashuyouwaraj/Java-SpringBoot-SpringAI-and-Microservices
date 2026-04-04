
public class QuickSort {

    public static void main(String[] args) {
        int nums[] = {6, 7, 4, 3, 1, 8, 13, 2};

        System.out.println("Before Sorting");
        for (int num : nums) {
            System.out.print(num + " ");
        }

        quickSortMethod(nums, 0, nums.length - 1);

        System.out.println("");
        System.out.println("After Sorting");
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public static void quickSortMethod(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSortMethod(arr, low, pi - 1);
            quickSortMethod(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;


        System.out.println("");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        return i + 1;
    }
}
