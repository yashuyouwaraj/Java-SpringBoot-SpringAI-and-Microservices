
public class MergeSort {

    public static void mergeSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSortHelper(arr, left, mid);
            mergeSortHelper(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }

        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        int nums[] = {6, 7, 4, 3, 1, 8, 13, 2};

        System.out.println("Before Sorting");
        for (int num : nums) {
            System.out.print(num + " ");
        }

        mergeSortHelper(nums, 0, nums.length - 1);

        System.out.println("");
        System.out.println("After Sorting");
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
