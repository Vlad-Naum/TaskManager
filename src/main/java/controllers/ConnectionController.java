package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import service.JDBCService;

import java.io.IOException;
import java.sql.SQLException;

public class ConnectionController {

    public static final String ERROR_MESSAGE = "Не удалось подключиться к базе данных. Проверьте данные!";

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
        } catch (SQLException | IOException exception) {
            //TODO: добавить log
            exception.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, ERROR_MESSAGE, ButtonType.OK);
            errorAlert.setTitle("Ошибка!");
            errorAlert.showAndWait();
        }
    }

    public void changeOnMainWindow() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        connection.getScene().setRoot(root);
    }
}
