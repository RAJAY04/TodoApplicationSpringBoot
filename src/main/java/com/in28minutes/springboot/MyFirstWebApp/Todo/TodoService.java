package com.in28minutes.springboot.MyFirstWebApp.Todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    public static List<Todo> todos = new ArrayList<>();

    private static int todoCount = 0;
    //initialize static variable inside the static block
    static{
        todos.add(new Todo(++todoCount,"ajay","learn sb", LocalDate.now().plusYears(1),false));
        todos.add(new Todo(++todoCount,"ajay","learn devops", LocalDate.now().plusYears(2),false));
        todos.add(new Todo(++todoCount,"ajay","learn aws", LocalDate.now().plusYears(3),false));
    }
    //note that the statics contents will be refreshed everytime we refresh the page
    //hence if we add todo and then refresh the page then it will be lost

    public List<Todo> findByUserName(String username){
        return todos;
    }

    public void addTodo(String username,String description,LocalDate targetDate,boolean done){
        Todo todo = new Todo(++todoCount,username,description,targetDate,done);
        todos.add(todo);

    }

    public void deleteById(int id){
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
