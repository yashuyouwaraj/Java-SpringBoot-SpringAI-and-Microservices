class MyThread1 extends Thread {
    public void run() {
        for (int i = 0; i <= 200; i++) {
            System.out.println("Hi");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyThread3 extends Thread {
    public void run() {
        for (int i = 0; i <= 200; i++) {
            System.out.println("Hello");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadPriorityandSleep {
    public static void main(String[] args) {
        MyThread1 t1 = new MyThread1();
        MyThread3 t2 = new MyThread3();

        t1.setPriority(Thread.MAX_PRIORITY);
        System.out.println(t1.getPriority());

        t1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
