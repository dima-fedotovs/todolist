package guru.bug.todolist;

import guru.bug.todolist.dao.Storage;
import guru.bug.todolist.model.State;
import guru.bug.todolist.model.ToDoItem;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ToDoListController implements Initializable {
    private final ObjectProperty<ToDoItem> selectedItem = new SimpleObjectProperty<>();
    private final Storage storage = Storage.newInstance();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupLanes(backlogLane, todoLane, doingLane, doneLane);
        selectedItem.addListener((o, ov, nv) -> selectionChanged(ov, nv));
        storage.init();
        loadData();
    }

    private void loadData() {
        for (var i : storage.selectAll()) {
            switch (i.getState()) {
                case BACKLOG -> backlogLane.getItems().add(i);
                case TODO -> todoLane.getItems().add(i);
                case DOING -> doingLane.getItems().add(i);
                case DONE -> doneLane.getItems().add(i);
            }
        }
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
            storage.update(oldValue);
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
        var item = new ToDoItem(storage.nextId());
        item.setState(State.BACKLOG);
        storage.insert(item);
        backlogLane.getItems().add(item);
        backlogLane.getSelectionModel().select(item);
        editTitle.requestFocus();
    }
}
