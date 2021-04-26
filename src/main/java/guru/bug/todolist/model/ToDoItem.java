package guru.bug.todolist.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class ToDoItem {
    private ReadOnlyLongWrapper id = new ReadOnlyLongWrapper(this, "id");
    private StringProperty title = new SimpleStringProperty(this, "title");
    private StringProperty description = new SimpleStringProperty(this, "description");
    private BooleanProperty urgent = new SimpleBooleanProperty(this, "urgent");
    private BooleanProperty important = new SimpleBooleanProperty(this, "important");
    private ObjectProperty<LocalDate> dueDate = new SimpleObjectProperty<>(this, "dueDate");
    private ObjectProperty<State> state = new SimpleObjectProperty<>(this, "state", State.BACKLOG);

    public ToDoItem(long id) {
        this.id.set(id);
    }

    public long getId() {
        return id.get();
    }

    public ReadOnlyLongProperty idProperty() {
        return id.getReadOnlyProperty();
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public boolean isUrgent() {
        return urgent.get();
    }

    public BooleanProperty urgentProperty() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent.set(urgent);
    }

    public boolean isImportant() {
        return important.get();
    }

    public BooleanProperty importantProperty() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important.set(important);
    }

    public LocalDate getDueDate() {
        return dueDate.get();
    }

    public ObjectProperty<LocalDate> dueDateProperty() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate.set(dueDate);
    }

    public State getState() {
        return state.get();
    }

    public ObjectProperty<State> stateProperty() {
        return state;
    }

    public void setState(State state) {
        this.state.set(state);
    }
}
