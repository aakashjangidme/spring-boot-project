package me.aakash.todoapp;

import me.aakash.todoapp.handlers.ResponseHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class TodoappApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoappApplication.class, args);
    }


    @GetMapping("/hello")
    public ResponseEntity sayHello() {
        return ResponseHandler.generateResponse("hello world", HttpStatus.OK, null);
    }
}
