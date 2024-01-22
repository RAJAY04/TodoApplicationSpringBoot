package com.in28minutes.springboot.MyFirstWebApp.Todo;

import jakarta.validation.Valid;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;

import static com.in28minutes.springboot.MyFirstWebApp.Todo.TodoService.todos;

@Controller
@SessionAttributes("name")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        super();
        this.todoService = todoService;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model){
        todoService.findByUserName("ajay");
        model.addAttribute("todos",todos);
        return "listTodos";
    }

    @RequestMapping(value = "add-todo",method = RequestMethod.GET)
    public String showNewTodos(ModelMap model){
        String username = (String)model.get("name");
        Todo todo = new Todo(1,username,"", LocalDate.now().plusYears(1),false);
        model.put("todo",todo);
        return "todos";
    }
    //above and below fun is causing two way binding, from the bean to the form and from the form to the bean
    //we have added the @Size on description in todo class but to triger that @valid is necessary on Todo todo where binding takes place
    @RequestMapping(value = "add-todo",method = RequestMethod.POST)
    public String addNewTodos(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todos";
        }
        String username = (String)model.get("name");
        todoService.addTodo(username,todo.getDescription(), todo.getTargetDate(),false);
        return "redirect:list-todos";//if we dont redirect the list wont be populated and be empty
    }//note to use the url and not name of the jsp while redirecting

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id){
        todoService.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value ="update-todo",method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id,ModelMap model){
        Todo todo = todoService.findById(id);
        model.addAttribute("todo",todo);
        return "todos";
    }
    @RequestMapping(value = "update-todo",method = RequestMethod.POST)
    public String updateTodos(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todos";
        }
        String username = (String) model.get("name");
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }
    }
