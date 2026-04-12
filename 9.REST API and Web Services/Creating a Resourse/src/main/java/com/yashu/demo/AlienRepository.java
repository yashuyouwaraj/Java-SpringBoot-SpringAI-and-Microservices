package com.yashu.demo;

import java.util.ArrayList;
import java.util.List;

public class AlienRepository{
    List<Alien> aliens;
    public AlienRepository(){
        aliens = new ArrayList<>();

        Alien a= new Alien();
        a.setId(101);
        a.setName("Yashu");
        a.setPoints("100");

        Alien a1= new Alien();
        a1.setId(102);
        a1.setName("Deepika");
        a1.setPoints("200");

        aliens.add(a);
        aliens.add(a1);
    }

    public List<Alien> getAliens(){
        return aliens;
    }

    public Alien getAliens(int id){
        for(Alien a: aliens){
            if(a.getId()==id){
                return a;
            }
        }
        return null;
    }

    public void createAlien(Alien a1){
        aliens.add(a1);
    }

    
}