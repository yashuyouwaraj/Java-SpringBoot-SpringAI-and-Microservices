
public class ExceptionHandlingwithtrycatch {
    public static void main(String[] args) {
        int a = 0;
        int b = 0;

        try {
            b=18/a;
        } catch (Exception e) {
            System.out.println("in catch block "+ e);
        }

        System.out.println(b);
        System.out.println("bye");
    }    
}
