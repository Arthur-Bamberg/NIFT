package crud;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Global;

public class CRUD extends Application {
    
    private static Scene scene;
    private static Global global;
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Login"), 1920, 1080);
        stage.setScene(scene);
        stage.show();
    }

    public static Global getGlobal() {
        return global;
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CRUD.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void main(String[] args) {
        launch();
    }
}
