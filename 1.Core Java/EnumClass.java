enum Laptop{
    HP(2000), XPS(2200), ThinkPad(1800), MACBOOK;

    private int price;

    private Laptop(){
        this.price = 2500;
    }

    private Laptop(int price){
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

public class EnumClass {
    public static void main(String[] args) {
        for(Laptop lap : Laptop.values()){
            System.out.println(lap + " : " + lap.getPrice());
        }
    }
}
