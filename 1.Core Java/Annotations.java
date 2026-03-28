@Deprecated
class A15 {
    public void showTheDataWhichBelongsToThisClass() {
        System.out.println("in show A");
    }
}

class B15 extends A15 {
    @Override
    public void showTheDataWhichBelongsToThisClass()

    {
        System.out.println("in show B");
    }
}

public class Annotations {
    public static void main(String[] args) {
        B15 obj = new B15();
        obj.showTheDataWhichBelongsToThisClass();
    }
}
