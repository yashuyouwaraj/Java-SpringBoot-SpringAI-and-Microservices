import java.util.List;
import java.util.Arrays;

public class MethodRef {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Yashu","Laxmi","Rohit","Aman","Shivam");

        List<String> result = names.stream().map(String::toUpperCase).toList();

        result.forEach(System.out::println);
    }
}
