package guru.bug.todolist;

import guru.bug.todolist.dao.MemoryStorage;
import guru.bug.todolist.dao.Storage;
import guru.bug.todolist.model.ToDoItem;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    public Label editId;
    private Storage storage;
    private final ObjectProperty<ToDoItem> selectedItem = new SimpleObjectProperty<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupLanes(backlogLane, todoLane, doingLane, doneLane);

        selectedItem.addListener((o, ov, nv) -> selectionChanged(ov, nv));

        this.storage = new MemoryStorage();
        storage.init();
    }

    private void setupLanes(Lane... lanes) {
        for (var l : lanes) {
            l.selectedProperty().addListener((o, ov, nv) -> selectedItem.set(nv));
            l.focusedProperty().addListener((o, ov, nv) -> {
                if (nv) {
                    var item = l.getSelected();
                    selectedItem.set(item);
                }
            });
        }
    }

    private void selectionChanged(ToDoItem oldValue, ToDoItem newValue) {
        if (oldValue != null) {
            editId.setText(null);
            oldValue.titleProperty().unbind();
            oldValue.setTitle(editTitle.getText());
            oldValue.descriptionProperty().unbind();
            oldValue.setDescription(editDescription.getText());
            oldValue.dueDateProperty().unbind();
            oldValue.setDueDate(editDueDate.getValue());
            oldValue.urgentProperty().unbind();
            oldValue.setUrgent(editUrgent.isSelected());
            oldValue.importantProperty().unbind();
            oldValue.setImportant(editImportant.isSelected());
        }
        if (newValue != null) {
            editId.setText("#" + newValue.getId());
            editTitle.setText(newValue.getTitle());
            newValue.titleProperty().bind(editTitle.textProperty());
            editDescription.setText(newValue.getDescription());
            newValue.descriptionProperty().bind(editDescription.textProperty());
            editDueDate.setValue(newValue.getDueDate());
            newValue.dueDateProperty().bind(editDueDate.valueProperty());
            editUrgent.setSelected(newValue.isUrgent());
            newValue.urgentProperty().bind(editUrgent.selectedProperty());
            editImportant.setSelected(newValue.isImportant());
            newValue.importantProperty().bind(editImportant.selectedProperty());
        }
        editCardContainer.setDisable(newValue == null);
    }

    public void createNewCard() {
        var item = new ToDoItem(storage.getNextId());
        backlogLane.getItems().add(item);
        backlogLane.getSelectionModel().select(item);
        editTitle.requestFocus();
    }
}
