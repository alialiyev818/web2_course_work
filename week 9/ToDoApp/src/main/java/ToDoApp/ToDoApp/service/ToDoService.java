package ToDoApp.ToDoApp.service;

import ToDoApp.ToDoApp.model.ToDo;
import ToDoApp.ToDoApp.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;

    public ToDo save(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    public List<ToDo> findAll() {
        return toDoRepository.findAll();
    }

    public void delete(String id) {
        toDoRepository.deleteById(id);
    }

    public List<ToDo> findByPerson(String person) {
        return toDoRepository.findByPerson(person);
    }

    public ToDo complete(String id) {
        ToDo toDo = toDoRepository.findById(id).orElse(null);
        if (toDo != null) {
            toDo.setIsComplete(true);
            toDoRepository.save(toDo);
        }
        return toDo;
    }

    public ToDo uncomplete(String id) {
        ToDo toDo = toDoRepository.findById(id).orElse(null);
        if (toDo != null) {
            toDo.setIsComplete(false);
            toDoRepository.save(toDo);
        }
        return toDo;
    }
}
