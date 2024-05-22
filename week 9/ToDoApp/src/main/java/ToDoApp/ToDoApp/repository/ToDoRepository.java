package ToDoApp.ToDoApp.repository;

import ToDoApp.ToDoApp.model.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ToDoRepository extends MongoRepository<ToDo, String> {
    List<ToDo> findByPerson(String person);
}
