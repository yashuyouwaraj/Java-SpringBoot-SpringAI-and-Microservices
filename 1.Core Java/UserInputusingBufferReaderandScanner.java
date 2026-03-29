import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserInputusingBufferReaderandScanner {
    public static void main(String[] args) throws IOException {
        System.out.print("Enter a number: ");
        int num = System.in.read();
        System.out.println("The number you entered is: "+(num-48));
        System.out.println("ASCII value of the number is: "+(char)num);

        System.out.print("Enter a number: ");
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(in);

        int num1 = Integer.parseInt(bf.readLine());
        System.out.println("The number you entered is: "+(num1));

        System.out.println("Enter a number: ");
        Scanner sc = new Scanner(System.in);
        int num2 = sc.nextInt();
        System.out.println("The number you entered is: "+num2);

    }
}