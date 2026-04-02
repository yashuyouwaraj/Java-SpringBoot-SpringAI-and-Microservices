interface Computer1 {
    void code();
}

class LaptopComputer1 implements Computer1{
    public void code(){
        System.out.println("Code, Compile, Run");
    }
}

class Desktop1 implements Computer1{
    public void code(){
        System.out.println("Code, Compile, Run: Faster than Laptop");
    }
}

class Developer{
    public void devApp(Computer1 lap){
        lap.code();
    }
}

public class NeedForInterfaces {
    public static void main(String[] args) {

        Computer1 lap = new LaptopComputer1();
        Computer1 desk = new Desktop1();

        Developer dev = new Developer();
        dev.devApp(lap);
        dev.devApp(desk);
    }
}

