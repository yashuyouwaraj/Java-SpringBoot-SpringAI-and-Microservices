interface Computer {
    void code();
}

class Laptop implements Computer{
    public void code(){
        System.out.println("Code, Compile, Run");
    }
}

class Desktop implements Computer{
    public void code(){
        System.out.println("Code, Compile, Run: Faster than Laptop");
    }
}

class Developer{
    public void devApp(Computer lap){
        lap.code();
    }
}

public class NeedForInterfaces {
    public static void main(String[] args) {

        Computer lap = new Laptop();
        Computer desk = new Desktop();

        Developer dev = new Developer();
        dev.devApp(lap);
        dev.devApp(desk);
    }
}

