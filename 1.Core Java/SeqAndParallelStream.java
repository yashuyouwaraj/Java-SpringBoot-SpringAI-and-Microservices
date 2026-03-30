import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SeqAndParallelStream {
    public static void main(String[] args) {
        int size = 10_000;
        List<Integer> nums = new ArrayList<>(size);

        Random ran = new Random();
        for (int i = 0; i < size; i++) {
            nums.add(ran.nextInt(100));
        }

        long startTime = System.currentTimeMillis();
        int sum1 = nums.stream().map(i -> {
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return i * 2;
        }).mapToInt(i -> i).sum();
        long endTime = System.currentTimeMillis();

        long startTime1 = System.currentTimeMillis();
        int sum2 = nums.parallelStream().map(i -> {
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return i * 2;
        }).mapToInt(i -> i).sum();
        long endTime1 = System.currentTimeMillis();

        System.out.println(sum1 + " : " + sum2);
        System.out.println("Time taken by sequential stream: " + (endTime - startTime) + " ms");
        System.out.println("Time taken by parallel stream: " + (endTime1 - startTime1) + " ms");
    }
}
