package fx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import luigi.Luigi;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Luigi luigi = new Luigi();

    public Main() throws IOException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap, 800, 600);
            stage.setScene(scene);
            stage.setMinHeight(600);
            stage.setMinWidth(800);
            stage.setTitle("Luigi Chatbot");
            fxmlLoader.<MainWindow>getController().setLuigi(luigi);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
