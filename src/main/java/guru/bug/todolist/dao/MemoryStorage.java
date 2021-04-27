package guru.bug.todolist.dao;

import guru.bug.todolist.model.State;
import guru.bug.todolist.model.ToDoItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemoryStorage implements Storage {
    private final List<ToDoItem> data = new ArrayList<>();
    private long counter;

    @Override
    public void init() {
        this.data.addAll(List.of(
                new ToDoItem(nextId(), State.DONE, "Cras ut neque", false, false, null, "Sed ut enim suscipit, iaculis ante a, luctus dui. In hac habitasse platea dictumst."),
                new ToDoItem(nextId(), State.BACKLOG, "Vivamus mattis", false, false, null, "mi nec efficitur eleifend, mauris neque efficitur nibh, sit amet suscipit risus ligula sit amet ex. Fusce eu magna sed lacus gravida ultricies."),
                new ToDoItem(nextId(), State.DONE, "Send Emails", true, true, LocalDate.parse("2021-04-26"), "Quisque ut pulvinar risus. Fusce tincidunt justo eget elit sagittis consectetur."),
                new ToDoItem(nextId(), State.BACKLOG, "Sed tristique", false, true, LocalDate.parse("2021-05-02"), "Sed tristique velit et hendrerit tincidunt. Morbi sed velit libero."),
                new ToDoItem(nextId(), State.TODO, "Maecenas cursus", false, true, LocalDate.parse("2021-05-02"), "Morbi pellentesque felis sit amet efficitur rutrum."),
                new ToDoItem(nextId(), State.TODO, "Morbi pellentesque felis sit amet", true, false, null, "Nam sollicitudin tristique quam. Sed tristique velit et hendrerit tincidunt. Morbi sed velit libero."),
                new ToDoItem(nextId(), State.DONE, "Create ToDo application", false, true, LocalDate.parse("2021-04-27"), "Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
                new ToDoItem(nextId(), State.DELETED, "Mauris ac ullamcorper", false, false, null, "Quisque accumsan quam nec purus sagittis, a vestibulum mi consectetur."),
                new ToDoItem(nextId(), State.DONE, "Nulla sagittis malesuada nisi", true, false, LocalDate.parse("2021-04-24"), "Nulla sagittis malesuada nisi, eu varius augue suscipit sit amet."),
                new ToDoItem(nextId(), State.TODO, "Nam sollicitudin euismod", false, false, null, "Mauris ac ullamcorper nisi. Nulla facilisi."),
                new ToDoItem(nextId(), State.DOING, "Vestibulum", false, false, LocalDate.parse("2021-05-09"), "Curabitur ac vehicula libero."),
                new ToDoItem(nextId(), State.DOING, "Nulla convallis", false, false, LocalDate.parse("2021-05-07"), "Sed facilisis mauris vitae metus finibus ultrices.")
        ));
    }

    @Override
    public long nextId() {
        counter++;
        return counter;
    }

    @Override
    public List<ToDoItem> selectAll() {
        return data;
    }

    @Override
    public void insert(ToDoItem item) {
        data.add(item);
    }

    @Override
    public void update(ToDoItem item) {
        data.remove(item);
    }
}
