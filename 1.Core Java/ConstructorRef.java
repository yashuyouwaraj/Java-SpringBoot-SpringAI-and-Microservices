import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Student5 {
    int age;
    String name;

    public Student5() {
    }

    public Student5(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student5 [age=" + age + ", name=" + name + "]";
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

}

public class ConstructorRef {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Yashu","Harsh","Amit","John");

        List<Student5> students = new ArrayList<>();

        students=names.stream().map(name->new Student5(name)).toList();

        // students=names.stream().map(Student5::new).toList();

        System.out.println(students);
    }
}
