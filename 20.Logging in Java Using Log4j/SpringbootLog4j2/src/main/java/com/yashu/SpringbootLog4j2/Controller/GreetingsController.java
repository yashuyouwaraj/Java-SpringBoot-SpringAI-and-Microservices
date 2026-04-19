package com.yashu.SpringbootLog4j2.Controller;

import com.yashu.SpringbootLog4j2.service.IGreetService;
//import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {
//    private static final Logger log = LogManager.getLogger(GreetingsController.class);
    private static final Logger log = LoggerFactory.getLogger(GreetingsController.class);

    @Autowired
    private IGreetService service;

    @GetMapping("/api1")
    public ResponseEntity<String> generateGreetings(){
        log.info("Control in api1");
        String body = service.generateWishes();
        log.debug("Service logic is been invocked and we got response");
        return  new ResponseEntity<String>(body, HttpStatus.OK);
    }
}
