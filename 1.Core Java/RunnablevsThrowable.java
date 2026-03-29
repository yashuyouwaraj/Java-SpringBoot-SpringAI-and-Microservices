class MyThread4 implements Runnable {
    public void run() {
        for (int i = 0; i <= 5; i++) {
            System.out.println("Hi");
        }
    }
}

class MyThread5 implements Runnable {
    public void run() {
        for (int i = 0; i <= 5; i++) {
            System.out.println("Hello");
        }
    }
}

public class RunnablevsThrowable {
    public static void main(String[] args) {

        Runnable obj1 = new MyThread4();
        Runnable obj2 = new MyThread5();

        Thread t1 = new Thread(obj1);
        Thread t2 = new Thread(obj2);

        t1.start();
        t2.start();

        Runnable obj3 = () -> {
            for (int i = 0; i <= 5; i++) {
                System.out.println("Hye");
            }
        };

        Runnable obj4 = () -> {
            for (int i = 0; i <= 5; i++) {
                System.out.println("Yo");
            }
        };

        Thread t3 = new Thread(obj3);
        Thread t4 = new Thread(obj4);

        t3.start();
        t4.start();

    }
}
