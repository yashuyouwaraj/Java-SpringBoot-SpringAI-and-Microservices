class Mobile {
    String brand;
    int price;
    String network;
    // String name;
    static String name;

    public void show(){
        System.out.println(brand + " : " + price + " : " + network + " : " + name);
    }
}

public class StaticVariable {
    public static void main(String[] args) {
        Mobile obj1=new Mobile();
        obj1.brand="Apple";
        obj1.price=80000;
        obj1.network="5G";
        //obj1.name="SmartPhone";
        Mobile.name="SmartPhone";

        Mobile obj2=new Mobile();
        obj2.brand="Samsung";
        obj2.price=60000;
        obj2.network="5G+";
        //obj2.name="SmartPhone";
        Mobile.name="SmartPhone";

        //obj1.name="Phone";


        obj1.show();
        obj2.show();

    }
}
