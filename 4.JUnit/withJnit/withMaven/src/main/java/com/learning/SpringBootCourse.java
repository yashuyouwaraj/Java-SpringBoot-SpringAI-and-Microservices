package com.learning;

public class SpringBootCourse implements Course {
     @Override
    public boolean coursePurchased(){
        System.out.println("Spring Boot course purchased");
        return true;
    }
}
