package me.aakash.todoapp.repositories;

import me.aakash.todoapp.models.Todo;

import java.util.List;

public interface TodoRepository {

    int save(Todo todo);

    List<Todo> getTodoListByUserId(String userId);

    Todo getTodoById(String todoId);

    int delete(String todoId);

}
