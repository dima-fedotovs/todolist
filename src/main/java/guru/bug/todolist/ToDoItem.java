package guru.bug.todolist;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class ToDoItem {
    private ReadOnlyLongWrapper id = new ReadOnlyLongWrapper(this, "id");
    private StringProperty title = new SimpleStringProperty(this, "title");
    private StringProperty description = new SimpleStringProperty(this, "description");
    private BooleanProperty urgent = new SimpleBooleanProperty(this, "urgent");
    private BooleanProperty important = new SimpleBooleanProperty(this, "important");
    private ObjectProperty<LocalDate> dueDate = new SimpleObjectProperty<>(this, "dueDate");
    private ReadOnlyObjectWrapper<ZonedDateTime> createdTime = new ReadOnlyObjectWrapper<>(this, "createdTime");
    private ReadOnlyObjectWrapper<ZonedDateTime> doneTime = new ReadOnlyObjectWrapper<>(this, "doneTime");
    private ReadOnlyBooleanWrapper done = new ReadOnlyBooleanWrapper(this, "done");

    public ToDoItem(long id) {
        this(id, ZonedDateTime.now());
    }

    public ToDoItem(long id, ZonedDateTime createdTime) {
        this.id.set(id);
        this.createdTime.set(createdTime);
        this.done.bind(Bindings.isNotNull(doneTime));
    }

    public void markAsDone() {
        if (done.get()) {
            return;
        }
        doneTime.set(ZonedDateTime.now());
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

    public ZonedDateTime getCreatedTime() {
        return createdTime.get();
    }

    public ReadOnlyObjectProperty<ZonedDateTime> createdTimeProperty() {
        return createdTime.getReadOnlyProperty();
    }

    public ZonedDateTime getDoneTime() {
        return doneTime.get();
    }

    public ReadOnlyObjectProperty<ZonedDateTime> doneTimeProperty() {
        return doneTime.getReadOnlyProperty();
    }

    public boolean isDone() {
        return done.get();
    }

    public ReadOnlyBooleanProperty doneProperty() {
        return done.getReadOnlyProperty();
    }
}
