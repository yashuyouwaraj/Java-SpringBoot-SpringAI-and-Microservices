
class YashuException extends Exception {
	public YashuException(String string) {
		super(string);
	}
}

class ThrowingLoader {
	public void show() throws ClassNotFoundException {
		// try
		// {
		// Class.forName("Calc");
		// }
		// catch(ClassNotFoundException e)
		// {
		// System.out.println("Not able to find theh class");
		// }

		Class.forName("Calc");
	}
}

public class DuckingExceptionusingthrows {
	static {
		System.out.println("Class Loader");
	}

	public static void main(String[] args) {

		// try
		// {
		// Class.forName("Class");
		// }
		// catch(ClassNotFoundException e)
		// {
		// System.out.println("Not able to find theh class");
		// }

		ThrowingLoader obj = new ThrowingLoader();
		try {
			obj.show();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
