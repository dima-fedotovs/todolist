package guru.bug.todolist.dao;

import guru.bug.todolist.model.ToDoItem;

import java.util.ArrayList;
import java.util.List;

public class MemoryStorage implements Storage {
    private long counter;

    @Override
    public void init() {

    }

    @Override
    public long getNextId() {
        counter++;
        return counter;
    }
}
