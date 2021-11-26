package me.aakash.todoapp.repositories;

import me.aakash.todoapp.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TodoRepositoryImpl implements TodoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Todo todo) {
        final String INSERT_TODO = "INSERT INTO TODO (title, content, user_id ) VALUES (?, ?, ?)";
        return jdbcTemplate.update(INSERT_TODO, todo.getTitle(), todo.getContent(), todo.getUserId());
    }

    @Override
    public List<Todo> getTodoListByUserId(String userId) {
        final String GET_TODO_LIST_BY_USER_ID = "SELECT id, title, content, user_id FROM TODO WHERE USER_ID = ?";
        return jdbcTemplate.query(GET_TODO_LIST_BY_USER_ID, BeanPropertyRowMapper.newInstance(Todo.class), userId);
    }

    @Override
    public Todo getTodoById(String todoId) {
        try {
            final String GET_TODO_BY_ID = "SELECT id, title, content, user_id FROM TODO WHERE id = ?";
            return jdbcTemplate.queryForObject(GET_TODO_BY_ID, BeanPropertyRowMapper.newInstance(Todo.class), todoId);

        } catch (IncorrectResultSizeDataAccessException e) {
            throw e;
        }
    }

    @Override
    public int delete(String todoId) {
        final String DELETE_TODO = "DELETE FROM TODO WHERE id = ?";
        return jdbcTemplate.update(DELETE_TODO, todoId);
    }
}
