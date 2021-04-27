package guru.bug.todolist.dao;

import guru.bug.todolist.model.ToDoItem;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBStorage implements Storage {
    private static final String JDBC_URL = "jdbc:h2:~/todolist";
    private static final String USER = "sa";
    private static final String PASSWORD = "sa";

    @Override
    public void init() {
        executeUpdate("""
                CREATE TABLE IF NOT EXISTS ITEMS (
                    ID BIGINT PRIMARY KEY,
                    TITLE VARCHAR(250),
                    DESCRIPTION VARCHAR(5000),
                    URGENT BOOLEAN,
                    IMPORTANT BOOLEAN,
                    DUE_DATE DATE,
                    STATE VARCHAR(50)
                )""");
        executeUpdate("CREATE SEQUENCE IF NOT EXISTS ITEM_ID_GENERATOR");
    }

    @Override
    public long nextId() {
        return executeQuery("SELECT NEXT VALUE FOR ITEM_ID_GENERATOR", rs -> {
            if (rs.next()) {
                return rs.getLong(1);
            } else {
                throw new IllegalStateException("No rows in result set");
            }
        });
    }

    @Override
    public List<ToDoItem> selectAll() {
        return List.of();
    }

    @Override
    public void insert(ToDoItem item) {

    }

    @Override
    public void update(ToDoItem item) {

    }

    private <T> T withConnection(ConnectedOperation<T> op) {
        try (var conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {

            return op.exec(conn);

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private void executeUpdate(String sql) {
        withConnection(c -> {
            try (var stmt = c.createStatement()) {
                stmt.executeUpdate(sql);
            }
            return null;
        });
    }

    private <T> T executeQuery(String sql, ResultSetConverter<T> converter) {
        return withConnection(c -> {
            try (var stmt = c.createStatement();
                 var rs = stmt.executeQuery(sql)) {
                return converter.convert(rs);
            }
        });
    }
}
