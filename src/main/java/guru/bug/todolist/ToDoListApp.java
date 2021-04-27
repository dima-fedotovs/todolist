package guru.bug.todolist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class ToDoListApp extends Application {
    public static void main(String[] args) {
        Application.launch(args);
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
