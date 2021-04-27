package guru.bug.todolist.dao;

import java.sql.SQLException;
import java.sql.Statement;

public interface StatementOperation<T> {
    T execute(Statement stmt) throws SQLException;
}
