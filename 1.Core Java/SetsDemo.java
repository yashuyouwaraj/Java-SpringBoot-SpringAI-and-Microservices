import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SetsDemo {
    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<Integer>();
        set1.add(62);
        set1.add(54);
        set1.add(22);
        set1.add(99);
        set1.add(26);
        
        Set<Integer> set2 = new TreeSet<Integer>();
        set2.add(62);
        set2.add(54);
        set2.add(22);
        set2.add(99);
        set2.add(26);

        Iterator<Integer> values1 = set1.iterator();
        Iterator<Integer> values2 = set2.iterator();

        while (values1.hasNext()) {
            System.out.println(values1.next());
        }

        System.out.println();

        while (values2.hasNext()) {
            System.out.println(values2.next());
        }

        // Output will be different for HashSet and TreeSet. HashSet does not maintain any order, while TreeSet maintains a sorted order.
        // hasNext() method is used to check if there are more elements in the set to iterate over. If it returns true, we can call next() to get the next element in the set.
    }
}
