package service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertService {

    public static void showErrorAlert(String errorMessage) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.OK);
        errorAlert.setTitle("Ошибка!");
        errorAlert.showAndWait();
    }
}
