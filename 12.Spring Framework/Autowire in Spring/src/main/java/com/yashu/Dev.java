package com.yashu;


public class Dev {

    private  Computer com;

    public Dev(){
        System.out.println("Dev Constructor");
    }

    public Dev(Computer comp) {
        this.com = comp;
    }
    public Computer getCom() {
        return com;
    }

    public void setCom(Computer comp) {
        this.com = comp;
    }

    public void build(){
        System.out.println("Working on Awesome Project");
        com.compile();
    }
}
