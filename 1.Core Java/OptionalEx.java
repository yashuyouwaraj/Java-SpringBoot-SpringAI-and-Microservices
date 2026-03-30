import java.util.List;
import java.util.Optional;
import java.util.Arrays;

public class OptionalEx {
    public static void main(String[] args) {
        List<String> name = Arrays.asList("Yashu","Laxmi","Rohit","Aman","Shivam");

        Optional<String> result = name.stream().filter(str->str.contains("f")).findFirst();

        System.out.println(result.orElse("Not found"));

        String res = name.stream().filter(str->str.contains("x")).findFirst().orElse("Not Found");

        System.out.println(res);
    }
}
