package ToDoApp.ToDoApp.controller;

import ToDoApp.ToDoApp.model.ToDo;
import ToDoApp.ToDoApp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @PostMapping
    public ToDo createToDo(@RequestBody ToDo toDo) {
        return toDoService.save(toDo);
    }

    @GetMapping
    public List<ToDo> getAllToDos() {
        return toDoService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable String id) {
        toDoService.delete(id);
    }

    @PutMapping("/complete/{id}")
    public ToDo completeToDo(@PathVariable String id) {
        return toDoService.complete(id);
    }

    @PutMapping("/uncomplete/{id}")
    public ToDo uncompleteToDo(@PathVariable String id) {
        return toDoService.uncomplete(id);
    }

    @GetMapping("/person/{personName}")
    public List<ToDo> getToDosByPerson(@PathVariable String personName) {
        return toDoService.findByPerson(personName);
    }
}
