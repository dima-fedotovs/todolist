package guru.bug.todolist.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectedOperation<T> {

    T execute(Connection conn) throws SQLException;

}
