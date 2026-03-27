//final - variable, method, class

final class Calc1 {
    public void show() {
        System.out.println("By Yashu");
    }

    public void add(int a, int b) {
        System.out.println("Sum is: " + (a + b));
    }
}

// class Calc2 extends Calc1 { // Compile time error because Calc1 is final and
// cannot be extended

class Calc3 {
    public final void show() {
        System.out.println("By Yashu");
    }

    public void add(int a, int b) {
        System.out.println(a + b);
    }
}

class AdvCalc1 extends Calc3 {
    // public void show() {
    //     System.out.println("By John");
    // }
    // Compile time error because show() method in Calc3 is final and cannot be overridden
}

public class FinalKeyword {
    public static void main(String[] args) {
        final int a=10;
        // a=20; Compile time error because a is final and cannot be reassigned

        System.out.println(a);

        Calc3 obj=new Calc3();
        obj.show();

        obj.add(4, 5);

        AdvCalc1 obj1 = new AdvCalc1();
        obj1.show();
        obj1.add(6, 7);
    }
}
