package guru.bug.todolist;

import guru.bug.todolist.model.ToDoItem;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.Objects;

public class Lane extends ListView<ToDoItem> {
    private ReadOnlyObjectWrapper<ToDoItem> selected = new ReadOnlyObjectWrapper<>(this, "selected");


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

        setCellFactory(l -> new ToDoCard());

        selected.bind(Bindings.select(selectionModelProperty(), "selectedItem"));
    }

    public ToDoItem getSelected() {
        return selected.get();
    }

    public ReadOnlyObjectProperty<ToDoItem> selectedProperty() {
        return selected.getReadOnlyProperty();
    }
}
