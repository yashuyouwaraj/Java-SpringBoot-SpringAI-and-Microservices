class Calculator2
{
	int num=5;
	public int add(int n1, int n2)
	{
		System.out.println(num);
		return n1+n2;
	}
}

public class Stack_and_Heap {
    public static void main(String[] args) {

        Calculator2 objCalculator = new Calculator2();
        Calculator2 objCalculator2 = new Calculator2();
        int result = objCalculator.add(10, 20);
        System.out.println("The sum of 10 and 20 is: "+result);
        objCalculator.num=15;
        System.out.println(objCalculator.num);
        System.out.println(objCalculator2.num);
    }
}
