record AlienRecord(int id, String name) {
    public AlienRecord {
        if(id<0){
            throw new IllegalArgumentException("Id cannot be negative");
        }
    }
}


public class RecordClasses {
    public static void main(String[] args) {
        AlienRecord obj1 = new AlienRecord(1, "Yashu");
        AlienRecord obj2= new AlienRecord(2, "Laxmi");
        AlienRecord obj3= new AlienRecord(1, "Yashu");

        System.out.println(obj1.equals(obj3));
        System.out.println(obj1);
        System.out.println(obj2.name());
    }
}
