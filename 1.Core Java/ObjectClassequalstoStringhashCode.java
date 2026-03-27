class Laptop{
    String model;
    int price;

    public String toString() {
        return "Model: " + model + ", Price: " + price;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Laptop laptop = (Laptop) obj;
        return price == laptop.price && model.equals(laptop.model);
    }
}

public class ObjectClassequalstoStringhashCode {

    public static void main(String[] args) {
        Laptop obj = new Laptop();
        obj.model = "Dell";
        obj.price = 50000;

        Laptop obj2=new Laptop();
    	obj2.model="Lenevo Yoga";
 //   	obj2.model="Lenevo Yoga1";
    	obj2.price=1000;
    	
    	boolean result = obj.equals(obj2);
    	
    	System.out.println(obj.toString());
//   	System.out.println(obj);
        System.out.println(result);
    }
}