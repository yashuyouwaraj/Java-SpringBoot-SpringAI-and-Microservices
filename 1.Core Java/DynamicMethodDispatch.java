class A1 {
    public void show() {
        System.out.println("In class A");
    }
}

class B1 extends A1 {
    public void show() {
        System.out.println("In class B");
    }
}

class C1 extends A1 {
    public void show() {
        System.out.println("In class C");
    }
}

class D1{

}

public class DynamicMethodDispatch {
    public static void main(String[] args) {
        A1 obj = new B1();
        obj.show();

        A1 obj2=new A1();
        obj2.show();

        obj=new B1();
        obj.show();

        obj = new C1();
        obj.show();

        // obj = new D1(); Compile time error because D1 is not a subclass of A1

    }
}
