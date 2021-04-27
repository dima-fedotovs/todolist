package guru.bug.todolist;

import guru.bug.todolist.model.ToDoItem;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;
import java.util.Objects;

public class ToDoCard extends ListCell<ToDoItem> {
    public Label idText;
    public Label titleText;
    public Label dueDateText;
    public Label urgentIcon;
    public Label importantIcon;

    public ToDoCard() {
        var url = Lane.class.getResource("ToDoCard.fxml");
        var loader = new FXMLLoader(Objects.requireNonNull(url, "ToDoCard.fxml not found"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void updateItem(ToDoItem item, boolean empty) {
        super.updateItem(item, empty);

        setText(null);

        idText.textProperty().unbind();
        titleText.textProperty().unbind();
        dueDateText.textProperty().unbind();
        urgentIcon.visibleProperty().unbind();
        importantIcon.visibleProperty().unbind();

        idText.setText(null);
        titleText.setText(null);
        dueDateText.setText(null);
        urgentIcon.setVisible(false);
        importantIcon.setVisible(false);

        if (empty || item == null) {
            return;
        }

        idText.textProperty().bind(Bindings.concat("#", item.idProperty()));
        titleText.textProperty().bind(Bindings.when(item.titleProperty().isEmpty()).then("Untitled").otherwise(item.titleProperty()));
        dueDateText.textProperty().bind(Bindings.when(item.dueDateProperty().isNull()).then("No Due").otherwise(Bindings.format("%1$te %1$tb %1$tY", item.dueDateProperty())));
        urgentIcon.visibleProperty().bind(item.urgentProperty());
        importantIcon.visibleProperty().bind(item.importantProperty());
    }
}
