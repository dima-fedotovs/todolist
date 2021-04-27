package guru.bug.todolist.model;

import javafx.beans.property.*;

import java.sql.Date;
import java.time.LocalDate;

public class ToDoItem {
    private final ReadOnlyLongWrapper id = new ReadOnlyLongWrapper(this, "id");
    private final StringProperty title = new SimpleStringProperty(this, "title");
    private final StringProperty description = new SimpleStringProperty(this, "description");
    private final BooleanProperty urgent = new SimpleBooleanProperty(this, "urgent");
    private final BooleanProperty important = new SimpleBooleanProperty(this, "important");
    private final ObjectProperty<LocalDate> dueDate = new SimpleObjectProperty<>(this, "dueDate");
    private final ObjectProperty<State> state = new SimpleObjectProperty<>(this, "state", State.BACKLOG);

    public ToDoItem(long id) {
        this.id.set(id);
    }

    public ToDoItem(long id, State state, String title, boolean urgent, boolean important, Date dueDate, String description) {
        this.id.set(id);
        this.title.set(title);
        this.description.set(description);
        this.urgent.set(urgent);
        this.important.set(important);
        if (dueDate != null) {
            this.dueDate.set(dueDate.toLocalDate());
        }
        this.state.set(state);
    }

    public ToDoItem(long id, State state, String title, boolean urgent, boolean important, LocalDate dueDate, String description) {
        this.id.set(id);
        this.title.set(title);
        this.description.set(description);
        this.urgent.set(urgent);
        this.important.set(important);
        this.dueDate.set(dueDate);
        this.state.set(state);
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

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public boolean isUrgent() {
        return urgent.get();
    }

    public void setUrgent(boolean urgent) {
        this.urgent.set(urgent);
    }

    public BooleanProperty urgentProperty() {
        return urgent;
    }

    public boolean isImportant() {
        return important.get();
    }

    public void setImportant(boolean important) {
        this.important.set(important);
    }

    public BooleanProperty importantProperty() {
        return important;
    }

    public LocalDate getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate.set(dueDate);
    }

    public ObjectProperty<LocalDate> dueDateProperty() {
        return dueDate;
    }

    public State getState() {
        return state.get();
    }

    public void setState(State state) {
        this.state.set(state);
    }

    public ObjectProperty<State> stateProperty() {
        return state;
    }
}
