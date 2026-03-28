class A10{
    public void show(){
        System.out.println("in A show");
    }
}

public class AnonymousInnerClass {
    public static void main(String[] args) {
        A10 obj = new A10(){
            public void show(){
                System.out.println("in Anonymous Inner Class show");
            }
        };

        obj.show();
    }
}
