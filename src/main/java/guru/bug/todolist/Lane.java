package guru.bug.todolist;

import guru.bug.todolist.dao.Storage;
import guru.bug.todolist.model.ToDoItem;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

import java.io.IOException;
import java.util.Objects;

public class Lane extends ListView<ToDoItem> {

    public Lane() {
        var url = Lane.class.getResource("Lane.fxml");
        var loader = new FXMLLoader(Objects.requireNonNull(url, "Lane.fxml not found"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
