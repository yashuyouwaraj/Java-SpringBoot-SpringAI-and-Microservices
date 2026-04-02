class AnonymousA {
    public AnonymousA(){
        System.out.println("object created");
    }
    public void show(){
        System.out.println("in A show");
    }

    
}

public class AnonymousObjects {
    public static void main(String[] args) {
        new AnonymousA();  //Anonymous object
        new AnonymousA().show();  //Anonymous object
    }
}
