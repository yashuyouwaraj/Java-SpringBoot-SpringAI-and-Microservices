import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args) {
        // Collection<Integer> nums= new ArrayList<Integer>();
        // Collection nums=new ArrayList();
        List<Integer> nums=new ArrayList<Integer>();
        nums.add(6);
        nums.add(7);
        nums.add(8);
        nums.add(9);

        System.out.println(nums.get(2));
        System.out.println(nums.indexOf(8));

        for(int n:nums){
            System.out.println(n);
        }
    }
}
