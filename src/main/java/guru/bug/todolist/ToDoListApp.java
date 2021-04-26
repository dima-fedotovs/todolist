package guru.bug.todolist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class ToDoListApp extends Application {
    private static final String URL = "jdbc:h2:~/test;AUTO_SERVER=TRUE";
    public static void main(String[] args) {
        initDB();
        Application.launch(args);
    }

    private static void initDB() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        var mainFxml = ToDoListApp.class.getResource("ToDoList.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(mainFxml, "FXML file not found"));
        var scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ToDo List");
        primaryStage.show();
    }
}
