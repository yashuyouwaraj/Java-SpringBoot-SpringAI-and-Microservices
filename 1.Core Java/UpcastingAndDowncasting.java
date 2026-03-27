class A
{
	public void show1()
	{
		System.out.println("in A show");
	}
}
class B extends A
{
	public void show2()
	{
		System.out.println("in show B");
	}
}

public class UpcastingAndDowncasting {
    public static void main(String[] args) {
        A obj = new B(); // Upcasting: B is being upcasted to A, this is implicit and does not require a cast
        obj.show1();

        A obj1 = new B();
        B obj2 = (B) obj1; // Downcasting: A is being downcasted to B, this is explicit and requires a cast
        obj2.show2();

    }
}
