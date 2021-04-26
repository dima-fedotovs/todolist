package guru.bug.todolist;

import guru.bug.todolist.dao.MemoryStorage;
import guru.bug.todolist.dao.Storage;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ToDoListController implements Initializable {
    private Storage

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var storage = new MemoryStorage();
        storage.init();
    }
}
