package guru.bug.todolist.dao;

import guru.bug.todolist.model.State;
import guru.bug.todolist.model.ToDoItem;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBStorage implements Storage {

    @Override
    public void init() {
        Helper.withStatement(stmt -> {
            stmt.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS ITEMS (
                        ID BIGINT PRIMARY KEY,
                        TITLE VARCHAR(250),
                        DESCRIPTION VARCHAR(2500),
                        URGENT BOOLEAN,
                        IMPORTANT BOOLEAN,
                        DUE_DATE DATE,
                        STATE VARCHAR(25)
                    )"""
            );
            stmt.executeUpdate("CREATE SEQUENCE IF NOT EXISTS ID_GENERATOR");
            return null;
        });

    }

    @Override
    public long nextId() {
        return Helper.withStatement(stmt -> {
            ResultSet rs = stmt.executeQuery("SELECT NEXT VALUE FOR ID_GENERATOR");
            if (rs.next()) {
                return rs.getLong(1);
            } else {
                throw new IllegalStateException("This should never happen");
            }
        });
    }

    @Override
    public List<ToDoItem> selectAll() {
        return Helper.withStatement(stmt -> {
            ResultSet rs = stmt.executeQuery("SELECT * FROM ITEMS");
            var result = new ArrayList<ToDoItem>();
            while (rs.next()) {
                var item = new ToDoItem(
                        rs.getLong("ID"),
                        State.valueOf(rs.getString("STATE")),
                        rs.getString("TITLE"),
                        rs.getBoolean("URGENT"),
                        rs.getBoolean("IMPORTANT"),
                        rs.getDate("DUE_DATE"),
                        rs.getString("DESCRIPTION")
                );
                result.add(item);
            }
            return result;
        });
    }

    @Override
    public void insert(ToDoItem item) {
        Helper.withConnection(c -> {
            var stmt = c.prepareStatement("""
                    INSERT INTO ITEMS (ID, TITLE, DESCRIPTION, URGENT, IMPORTANT, DUE_DATE, STATE)
                    VALUES (?, ?, ?, ?, ?, ?, ?)
                    """);
            stmt.setLong(1, item.getId());
            stmt.setString(2, item.getTitle());
            stmt.setString(3, item.getDescription());
            stmt.setBoolean(4, item.isUrgent());
            stmt.setBoolean(5, item.isImportant());
            stmt.setDate(6, item.getDueDate() == null ? null : Date.valueOf(item.getDueDate()));
            stmt.setString(7, item.getState().toString());

            stmt.executeUpdate();
            return null;
        });
    }

    @Override
    public void update(ToDoItem item) {
        Helper.withConnection(c -> {
            var stmt = c.prepareStatement("""
                    UPDATE ITEMS SET 
                        TITLE = ?, 
                        DESCRIPTION = ?, 
                        URGENT = ?, 
                        IMPORTANT = ?, 
                        DUE_DATE = ?, 
                        STATE = ?
                    WHERE ID = ?""");
            stmt.setString(1, item.getTitle());
            stmt.setString(2, item.getDescription());
            stmt.setBoolean(3, item.isUrgent());
            stmt.setBoolean(4, item.isImportant());
            stmt.setDate(5, item.getDueDate() == null ? null : Date.valueOf(item.getDueDate()));
            stmt.setString(6, item.getState().toString());
            stmt.setLong(7, item.getId());

            stmt.executeUpdate();
            return null;
        });
    }
}
