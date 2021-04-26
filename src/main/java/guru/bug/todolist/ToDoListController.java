package guru.bug.todolist;

import guru.bug.todolist.dao.MemoryStorage;
import guru.bug.todolist.dao.Storage;
import guru.bug.todolist.model.ToDoItem;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ToDoListController implements Initializable {
    public Lane backlogLane;
    public Lane todoLane;
    public Lane doingLane;
    public Lane doneLane;
    public TextField editTitle;
    public TextArea editDescription;
    public DatePicker editDueDate;
    public CheckBox editUrgent;
    public CheckBox editImportant;
    public VBox editCardContainer;
    private Storage storage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backlogLane.selectedProperty().addListener((o, ov, nv) -> selectionChanged(backlogLane, ov, nv));
        todoLane.selectedProperty().addListener((o, ov, nv) -> selectionChanged(todoLane, ov, nv));
        doingLane.selectedProperty().addListener((o, ov, nv) -> selectionChanged(doingLane, ov, nv));
        doneLane.selectedProperty().addListener((o, ov, nv) -> selectionChanged(doneLane, ov, nv));

        this.storage = new MemoryStorage();
        storage.init();
    }

    private void selectionChanged(Lane lane, ToDoItem ov, ToDoItem nv) {
        if (ov != null) {
            ov.titleProperty().unbind();
            ov.descriptionProperty().unbind();
            ov.dueDateProperty().unbind();
            ov.urgentProperty().unbind();
            ov.importantProperty().unbind();
        }
        if (nv != null) {
            editTitle.setText(nv.getTitle());
            nv.titleProperty().bind(editTitle.textProperty());
            editDescription.setText(nv.getDescription());
            nv.descriptionProperty().bind(editDescription.textProperty());
            editDueDate.setValue(nv.getDueDate());
            nv.dueDateProperty().bind(editDueDate.valueProperty());
            editUrgent.setSelected(nv.isUrgent());
            nv.urgentProperty().bind(editUrgent.selectedProperty());
            editImportant.setSelected(nv.isImportant());
            nv.importantProperty().bind(editImportant.selectedProperty());
        }
        editCardContainer.setDisable(nv == null);
    }

    public void createNewCard() {
        var item = new ToDoItem(storage.getNextId());
        backlogLane.getItems().add(item);
    }
}
