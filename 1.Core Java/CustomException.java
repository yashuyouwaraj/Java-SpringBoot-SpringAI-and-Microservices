class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}

public class CustomException {
    public static void main(String[] args) {
        int i = 20;
        int j = 0;

        try {
            j = 18 / i;
            if (j == 0) {
                throw new MyException("I don't want to do print zero");
            }
        } catch (ArithmeticException e) {
            j = 18 / 1;
            System.out.println("That is default output " + e);

        } catch (MyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(j);
        System.out.println("Bye");
    }
}
