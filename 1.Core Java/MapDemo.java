import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        Map<String, Integer> students = new HashMap<>();

        students.put("Yashu", 97);
        students.put("Deepika", 99);
        students.put("Aman", 65);
        students.put("Ruchi", 45);
        students.put("Aman", 70);

        System.out.println(students.keySet());
        System.out.println(students.values());

        for (String key: students.keySet()){
            System.out.println(key+" : "+students.get(key));
        }

        Map<String, Integer> students1 = new Hashtable<>(); // Hashtable is synchronized and does not allow null keys or values, while HashMap allows one null key and multiple null values.
    }
}
