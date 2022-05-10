package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.log4j.Logger;
import pojo.TaskClass;
import service.AlertService;
import service.JDBCService;

import java.util.List;

public class MainController {

    public static final Logger log = Logger.getLogger(MainController.class);
    public static final String ERROR_ADD = "Не удалось создать задачу.";
    public static final String ERROR_DELETE = "Не удалось удалить задачу.";
    public static final String ERROR_GET_ALL = "Не удалось получить все задачи.";

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
    private TableView<TaskClass> tableView;

    @FXML
    private TableColumn taskId;

    @FXML
    private TableColumn taskNameProperty;

    @FXML
    private TableColumn taskAuthor;

    @FXML
    void initialize() {
        JDBCService jdbcService = JDBCService.getInstance();

        if (jdbcService == null) {
            log.warn("JDBC service is null!");
            return;
        }

        tableInit(jdbcService);

        addButton.setOnAction(event -> {
            try {
                jdbcService.add(taskName.getText(), author.getText());
            } catch (Exception e) {
                AlertService.showErrorAlert(ERROR_ADD);
            }
            taskName.clear();
            author.clear();
            tableInit(jdbcService);
        });

        deleteButton.setOnAction(event -> {
            try {
                jdbcService.delete(Integer.parseInt(id.getText()));
            } catch (Exception e) {
                AlertService.showErrorAlert(ERROR_DELETE);
            }
            id.clear();
            tableInit(jdbcService);
        });
    }

    public void tableInit(JDBCService service) {
        List<TaskClass> all;
        try {
            all = service.getAll();
        } catch (Exception e) {
            AlertService.showErrorAlert(ERROR_GET_ALL);
            throw new RuntimeException(e);
        }
        ObservableList<TaskClass> tasks = FXCollections.observableList(all);
        taskId.setCellValueFactory(new PropertyValueFactory<TaskClass, String>("taskId"));
        taskNameProperty.setCellValueFactory(new PropertyValueFactory<TaskClass, String>("taskNameProperty"));
        taskAuthor.setCellValueFactory(new PropertyValueFactory<TaskClass, String>("taskAuthor"));
        tableView.setItems(tasks);
    }

}
