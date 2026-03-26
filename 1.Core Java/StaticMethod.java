class Mobile1{
    String brand;
    int price;
    String network;
    static String name;

    public void show(){
        System.out.println(brand+" : "+price+" : "+name);
    }

    public static void show1(Mobile1 obj){
        System.out.println("in static method");
        System.out.println(obj.brand+" : "+ obj.price +" : "+name +" : "+ obj.network);
    }
}


public class StaticMethod {
    public static void main(String[] args) {
        Mobile1 obj1 = new Mobile1();
        obj1.brand = "Apple";
        obj1.price = 80000;
        obj1.network = "5G";
        Mobile1.name = "SmartPhone";

        Mobile1 obj2 = new Mobile1();
        obj2.brand = "Samsung";
        obj2.price = 60000;
        obj2.network = "5G+";
        Mobile1.name = "SmartPhone";

        obj1.show();
        obj2.show();

        Mobile1.show1(obj1);

    }   
}
