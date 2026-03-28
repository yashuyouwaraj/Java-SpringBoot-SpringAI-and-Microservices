
public class ExceptionThrowkeyword {
    public static void main(String[] args) {
        int i=20;
        int j=0;

        try {
            j=18/i;
            if(j==0){
                throw new ArithmeticException("I dont want to print zero");
            }
        }
        catch(ArithmeticException e){
            j=18/1;
            System.out.println("that is default output "+ e);
        }
         catch (Exception e) {
            System.out.println("Something went Wrong "+e);
        }

        System.out.println(j);
        System.out.println("Bye");
    }
    
}