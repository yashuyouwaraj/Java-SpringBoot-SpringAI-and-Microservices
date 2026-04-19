package com.yashu.SpringbootLog4j2.service;

import com.yashu.SpringbootLog4j2.Controller.GreetingsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GenerateWishesService implements IGreetService {
    private static final Logger log = LoggerFactory.getLogger(GreetingsController.class);

    @Override
    public String generateWishes(){
        log.trace("Service / Business level of generate wishes");
        LocalDateTime dateTime = LocalDateTime.now();
        int hour = dateTime.getHour();
        String body=null;

        if(hour<12) {
            body = "Good Morning";
        }
        else if (hour<16) {
            body="Good AfterNoon";
        } else if (hour<20) {
            body="Good Evening";
        } else {
            body="Good Night";
        }
        return body;
    }
}
