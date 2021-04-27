package guru.bug.todolist;

import guru.bug.todolist.model.ToDoItem;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.TransferMode;

import java.io.IOException;
import java.util.Objects;

public class Lane extends ListView<ToDoItem> {
    private final ReadOnlyObjectWrapper<ToDoItem> selected = new ReadOnlyObjectWrapper<>(this, "selected");

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
        setOnDragDetected(e -> {
            var s = selected.get();
            if (s != null) {
                var db = startDragAndDrop(TransferMode.MOVE);
                var cc = new ClipboardContent();
                cc.putString(s.getTitle() + "\n" + s.getDescription());
                db.setContent(cc);
            }
            e.consume();
        });

        setOnDragOver(e -> {
            if (e.getDragboard().hasString()) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
            e.consume();
        });

        setOnDragDropped(e -> {
            var db = e.getDragboard();
            if (db.hasString() && e.getGestureSource() != e.getGestureTarget() && e.getGestureSource() instanceof Lane lane && lane.selected.get() != null) {
                var i = lane.selected.get();
                lane.getItems().remove(i);
                getItems().add(i);
                getSelectionModel().select(i);
                e.setDropCompleted(true);
                requestFocus();
            } else {
                e.setDropCompleted(false);
            }
            e.consume();
        });
    }

    public ToDoItem getSelected() {
        return selected.get();
    }

    public ReadOnlyObjectProperty<ToDoItem> selectedProperty() {
        return selected.getReadOnlyProperty();
    }
}
