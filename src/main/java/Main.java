import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class Main extends Application {
    public static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/connection.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("TaskManager");
        primaryStage.show();
        log.info("Starting application");
    }
}
