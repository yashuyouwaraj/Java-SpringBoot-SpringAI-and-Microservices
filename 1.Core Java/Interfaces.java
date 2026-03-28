interface A12 {
    int a = 10; // by default public static final
    String name = "Java";

    void show(); // by default public abstract

    void config();
}

class B12 implements A12 {

    @Override
    public void show() {
        System.out.println("in show");
    }

    @Override
    public void config() {
        System.out.println("in config");
    }
}

public class Interfaces {
    public static void main(String[] args) {
        A12 obj = new B12();
        obj.show();
        obj.config();

        System.out.println(A12.a);
        System.out.println(A12.name);
    }
}
