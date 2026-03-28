abstract class A11{
    public abstract void show();
    public abstract void config();
}

public class Abstractandanonymousinnerclass {
    public static void main(String[] args) {
        A11 obj = new A11(){
            public void show(){
                System.out.println("in Anonymous Inner Class show");
            }
            public void config(){
                System.out.println("in Anonymous Inner Class config");
            }
        };
        obj.show();
        obj.config();
    }    
}
