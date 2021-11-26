package me.aakash.todoapp.controllers;

import me.aakash.todoapp.handlers.ResponseHandler;
import me.aakash.todoapp.models.Todo;
import me.aakash.todoapp.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/")
public class TodoController {

    public ArrayList<Todo> todos = new ArrayList<Todo>();
    @Autowired
    TodoRepository todoRepository;

    /**
     * returns the List of Todos for the loggedIn user
     */

    @GetMapping("/getTodoList")
    public ResponseEntity getTodos(@RequestParam(required = true) String userId) {
        try {

            List<Todo> todoList = new ArrayList<Todo>();

            if (userId != null)
                todoRepository.getTodoListByUserId(userId).forEach(todoList::add);

            else
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId not passed in requestParam");


            return ResponseHandler.generateResponse("getTodo fetched successfully.", HttpStatus.OK, todoList);


        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/getTodo")
    public ResponseEntity getTodoById(@RequestParam(required = true) String id) {
        try {
            Todo todo = todoRepository.getTodoById(id);

            if (todo != null)

                return ResponseHandler.generateResponse("getTodoList fetched successfully.", HttpStatus.OK, todo);

            else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo does not exist");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);

        }
    }

    @PostMapping("/addTodo")
    public ResponseEntity addTodo(@RequestBody Todo todo) {

        try {
            todoRepository.save(todo);
            return ResponseHandler.generateResponse("Todo added successfully.", HttpStatus.CREATED, todo);

        } catch (Exception e) {

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);

        }

    }


    @DeleteMapping("/deleteTodo")
    public ResponseEntity deleteTodo(@RequestParam(required = true) String id) {


        try {
            todoRepository.delete(id);

            return ResponseHandler.generateResponse("Todo deleted successfully.", HttpStatus.CREATED, null);

        } catch (Exception e) {

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);

        }

    }
}
