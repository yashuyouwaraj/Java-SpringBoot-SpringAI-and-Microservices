// This class demonstrates implicit and explicit type conversion in Java
public class Type_Conversion {
    public static void main(String[] args) {
        // Example 1: Implicit conversion from byte to int (widening)
        byte b = 127; // byte variable with max positive value
        int a=b; // implicitly converted to int
        System.out.println("Value of a: " + a); // Output: 127

        // Example 2: Another implicit byte to int conversion
        byte b1=125; // byte variable
        int a1 = b1; // automatically widened to int
        System.out.println("Value of a1: "+ a1); // Output: 125

        // Example 3: Explicit conversion from int to byte (narrowing) - data loss occurs
        int a2=257; // int value larger than byte range (0-255)
        byte k=(byte)a2; // explicitly cast to byte, value wraps around
        System.out.println("Value of k: "+ k); // Output: 1 (257 mod 256)

        // Example 4: Explicit conversion from float to int (truncates decimal part)
        float f=3.14f; // float value with decimal
        int i=(int)f; // explicitly cast to int, decimal part discarded
        System.out.println("Value of i: "+ i); // Output: 3

        // Example 5: Explicit narrowing conversion from int to byte
        int a3=2567; // int value beyond byte capacity
        byte b3 = (byte)a3; // explicitly narrowed to byte
        System.out.println("Value of b3: "+ b3); // Output: -25 (overflow behavior)

        // Example 6: Automatic promotion to int when performing arithmetic on bytes
        byte a4=10; // first byte operand
        byte b4=20; // second byte operand
        int c = a4*b4; // result automatically promoted to int (10*20=200)
        System.out.println("Value of c: "+ c); // Output: 200
    }
}
