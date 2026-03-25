public class WhatisString {
    public static void main(String[] args) {
        String name = new String();
        String name1= new String("Java");
        String name2="Yashu";
        System.out.println(name); // it will print null because we have not assigned any value to it
        System.out.println(name1.hashCode());
        System.out.println("Hello " + name2);
        System.out.println(name2.concat("Youwaraj"));

    }
}
