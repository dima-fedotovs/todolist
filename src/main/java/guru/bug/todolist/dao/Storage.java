package guru.bug.todolist.dao;

import guru.bug.todolist.model.ToDoItem;

import java.util.List;

public interface Storage {
    static Storage newInstance() {
        return new DBStorage();
    }

    void init();

    long nextId();

    List<ToDoItem> selectAll();

    void insert(ToDoItem item);

    void update(ToDoItem item);
}
