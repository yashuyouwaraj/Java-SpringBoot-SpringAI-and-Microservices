import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Student4 {
    int age;
    String name;

    public Student4(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String toString() {
        return "Student [age=" + age + ", name=" + name + "]";
    }
}

public class ComparatorDemo {
    public static void main(String[] args) {
        Comparator<Integer> com = new Comparator<Integer>() {
            public int compare(Integer i, Integer j) {
                if (i % 10 > j % 10) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };

        List<Integer> nums = new ArrayList<>();
        nums.add(62);
        nums.add(31);
        nums.add(45);
        nums.add(29);

        Collections.sort(nums, com);
        System.out.println(nums);

        // Comparator<Student> com1 = new Comparator<Student>() {
        //     public int compare(Student i, Student j) {
        //         if (i.age > j.age)
        //             return 1;
        //         else
        //             return -1;
        //     }
        // };

        Comparator<Student4> com1 = (i,j)-> i.age > j.age ? 1 : -1; // Using lambda expression to simplify the code

        List<Student4> studs= new ArrayList<>();
    	studs.add(new Student4(21,"Yashu"));
    	studs.add(new Student4(12,"John"));
    	studs.add(new Student4(18,"Parul"));
    	studs.add(new Student4(20,"Kiran"));
    	Collections.sort(studs, com1);
    	// System.out.println(studs);

        for(Student4 s: studs){
            System.out.println(s);
        }


    }
}
