
import java.sql.DriverManager;
import java.sql.SQLException;


public class Launch1 {
    public static void main(String[] args) throws ClassNotFoundException , SQLException {
    Class.forName("com.yashu.Demo");

    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

    }
}

class Demo{
    static{
        System.out.println("Static block");
    }{
        System.out.println("Instance block ==> Non Static");
    }
}
