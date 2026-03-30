import java.util.List;
import java.util.stream.Stream;

public class MapFilterReduceSorted {
    public static void main(String[] args) {
        List<Integer> nums = java.util.Arrays.asList(4, 5, 6, 7, 8, 82, 21, 30, 5);

        // Predicate<Integer> p= new Predicate<Integer>() {
        // public boolean test(Integer n) {
        // return n%2==0;
        // if(n%2==0)
        // return true;
        // else
        // return false;
        // }
        // };

        // Predicate<Integer> p= n-> n%2==0;

        // Function<Integer, Integer> fun= new Function<Integer,Integer>() {
        // public Integer apply(Integer n) {
        // return n*2;
        // }
        // };

        // Function<Integer,Integer> fun= n-> n*2;

        int result = nums.stream().filter(n->n%2==0).map(n->n*2).reduce(0, (a,b)-> a+b);

        System.out.println(result);

        Stream<Integer> sortedStream = nums.stream().sorted();

        sortedStream.forEach(n->System.out.println(n));

        System.out.println("Using parallel stream to filter even numbers and sort them: ");

        Stream<Integer> sortedStream2 = nums.parallelStream().filter(n->n%2==0).sorted();

        sortedStream2.forEach(n->System.out.println(n));

    }
}
