// class - class -> extends
// class - interface -> implements
// interface - interface -> extends

interface  A13{
    void show();
    void config();
}

interface X {
    void run();
}

interface Y extends X {

}

class B13 implements A13,Y{
    @Override
    public void show() {
        System.out.println("in show");
    }

    @Override
    public void config() {
        System.out.println("in config");
    }

    @Override
    public void run() {
        System.out.println("in run");
    }
}


public class MoreonInterfaces {
    public static void main(String[] args) {
        A13 obj = new B13();
        obj.show();
        obj.config();

        X obj1 = new B13();
        obj1.run();
    }
}
