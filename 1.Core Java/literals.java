public class literals {
    public static void main(String[] args) {
        int num1 = 0b1010; // Binary literal for 10
        int num2 = 0x1A; // Hexadecimal literal for 26
        int num3 = 123; // Decimal literal
        double d = 3.14; // Floating-point literal
        int num4 = 10_00_00_000; // Underscores in numeric literals for readability

        char c ='a';
        c++;

        System.out.println("Character literal: " + c);

        System.out.println("Binary literal (0b1010): " + num1);
        System.out.println("Hexadecimal literal (0x1A): " + num2);
        System.out.println("Decimal literal (123): " + num3);
        System.out.println("Floating-point literal (3.14): " + d);
        System.out.println("Numeric literal with underscores (10_00_00_000): " + num4);
    }
}
