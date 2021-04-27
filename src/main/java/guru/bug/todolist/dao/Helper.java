package guru.bug.todolist.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Helper {
    private static final String JDBC_URL = "jdbc:h2:~/todolist";

    private Helper() {
    }

    public static <T> T withConnection(ConnectedOperation<T> op) {
        try (var conn = DriverManager.getConnection(JDBC_URL, "sa", "sa")) {

            return op.execute(conn);

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static <T> T withStatement(StatementOperation<T> op) {
        return withConnection(c -> {
            try (var stmt = c.createStatement()) {
                return op.execute(stmt);
            }
        });
    }

}
