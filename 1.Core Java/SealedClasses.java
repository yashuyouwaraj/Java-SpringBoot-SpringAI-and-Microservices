sealed class Shape permits Circle, Rectangle{

}

non-sealed class Circle extends Shape{

}

final class Rectangle extends Shape{

}

class Square extends Circle{

}

sealed interface Vehicle permits Car{

}

non-sealed interface Car extends Vehicle{

}


public class SealedClasses {
    public static void main(String[] args) {
        
    }
}

// Sealed classes and interfaces are a new feature in Java 15 that allow you to restrict which classes can extend or implement them. This can be useful for creating more secure and maintainable code, as it allows you to control the inheritance hierarchy of your classes and interfaces.
