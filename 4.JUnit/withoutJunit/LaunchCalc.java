

public class LaunchCalc {
    public static void main(String[] args) {
        Calc20 c = new Calc20();
        int result = c.divide(10, 2);
        if(result == 5) {
            System.out.println("Test passed!");
        } else {
            System.out.println("Test failed!");
        }
    }
}
