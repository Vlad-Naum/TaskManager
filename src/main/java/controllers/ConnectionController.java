package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;
import service.AlertService;
import service.JDBCService;

import java.io.IOException;

public class ConnectionController {

    public static final Logger log = Logger.getLogger(ConnectionController.class);
    public static final String ERROR_CONNECTION = "Не удалось подключиться к базе данных. Проверьте данные!";

    @FXML
    private TextField url;

    @FXML
    private TextField userName;

    @FXML
    private TextField password;

    @FXML
    private Button connection;

    @FXML
    void connection() {
        try {
            JDBCService.createInstance(url.getText(), userName.getText(), password.getText());
            changeOnMainWindow();
        } catch (Exception exception) {
            AlertService.showErrorAlert(ERROR_CONNECTION);
        }
    }

    public void changeOnMainWindow() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        connection.getScene().setRoot(root);
        log.info("Change on Main window");
    }
}
