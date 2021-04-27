package guru.bug.todolist.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectedOperation<T>  {

    T exec(Connection connection) throws SQLException;

}
