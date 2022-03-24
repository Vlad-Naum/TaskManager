
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField taskName;

    @FXML
    private TextField author;

    @FXML
    private TextField id;

    @FXML
    void initialize() {
        addButton.setOnAction(event -> {

        });

        deleteButton.setOnAction(event -> {

        });
    }

}
