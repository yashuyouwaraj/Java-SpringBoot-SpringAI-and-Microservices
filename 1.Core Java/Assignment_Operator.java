public class Assignment_Operator {
    public static void main(String[] args) {
        int num1 = 7;
        int num2 = 5;
        int result = num1 + num2; 
        System.out.println("Result of addition: " + result); 

        int num3 = 10;
        int num4 = 5;
        int result2 = num3-num4;
        System.out.println("Result of subtraction: " + result2);

        int num5 = 7;
        int num6 = 5;
        int result3 = num5 % num6;
        System.out.println("Result of modulus: " + result3);

        int num7 = 10;
        num7 = num7 + 2;
        num7+=2;
        System.out.println("Result of addition assignment: " + num7);
        num7*=2;
        System.out.println("Result of multiplication assignment: " + num7);

        int num = 20;
        num++;    //post increment
		++num;    //pre increment
		num--;    //post decrement
		--num;	  //pre decrement
        System.out.println("Value after decrement: " + num);

        int result5 = num++ ; //fetch the value and then increment
        System.out.println("Value of result: " + result5);
    }
}
