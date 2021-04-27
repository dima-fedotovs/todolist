package guru.bug.todolist.dao;

import guru.bug.todolist.model.ToDoItem;

import java.util.List;

public class DBStorage implements Storage {
    private static final String JDBC_URL = "jdbc:h2:~/todolist";

    @Override
    public void init() {

    }

    @Override
    public long nextId() {
        return 0;
    }

    @Override
    public List<ToDoItem> selectAll() {
        return null;
    }

    @Override
    public void insert(ToDoItem item) {

    }

    @Override
    public void update(ToDoItem item) {

    }
}
