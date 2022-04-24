package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.JDBCService;

import java.io.IOException;
import java.sql.SQLException;

public class ConnectionController {

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
        if (url != null && userName != null && password != null) {
            try {
                JDBCService.createInstance(url.getText(), userName.getText(), password.getText());
                changeOnMainWindow();
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        } else {
            //TODO: выкинуть исключение
        }
    }

    public void changeOnMainWindow() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        connection.getScene().setRoot(root);
    }
}
