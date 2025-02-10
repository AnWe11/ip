package fx;

import exceptions.InvalidCommandException;
import fx.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import luigi.Luigi;
import javafx.application.Platform;

import java.io.IOException;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Luigi luigi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Terra.png"));
    private Image luigiImage = new Image(this.getClass().getResourceAsStream("/images/Luigi.png"));

    @FXML
    public void initialize() throws IOException {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        printLuigiUI();
    }

    /** Injects the Luigi instance */
    public void setLuigi(Luigi l) {
        luigi = l;
    }

    private void printLuigiUI() throws IOException {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(luigi.getLuigiWelcomeUI(), luigiImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InvalidCommandException, IOException {
        String input = userInput.getText();
        String response = luigi.getResponse(input);

        if (response.equals("Goodbye! Hope to see you again soon!")) {
            Platform.exit();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, luigiImage));
            userInput.clear();
        }
    }
}
