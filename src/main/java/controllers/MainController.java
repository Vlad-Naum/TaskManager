package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.TaskClass;
import service.JDBCService;

import java.sql.SQLException;
import java.util.List;

public class MainController {

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
    void initialize() throws SQLException {
        JDBCService jdbcService = JDBCService.getInstance();

        if (jdbcService == null) {
            //TODO: выкинуть исключение
        }

        tableInit(jdbcService);

        addButton.setOnAction(event -> {
            if (taskName != null && author != null) {
                jdbcService.add(taskName.getText(), author.getText());
                taskName.clear();
                author.clear();
                tableInit(jdbcService);
            }
        });

        deleteButton.setOnAction(event -> {
            if (id != null) {
                jdbcService.delete(Integer.parseInt(id.getText()));
                id.clear();
                tableInit(jdbcService);
            }
        });
    }

    public void tableInit(JDBCService service) {
        List<TaskClass> all = service.getAll();
        ObservableList<TaskClass> tasks = FXCollections.observableList(all);
        taskId.setCellValueFactory(new PropertyValueFactory<TaskClass, String>("taskId"));
        taskNameProperty.setCellValueFactory(new PropertyValueFactory<TaskClass, String>("taskNameProperty"));
        taskAuthor.setCellValueFactory(new PropertyValueFactory<TaskClass, String>("taskAuthor"));
        tableView.setItems(tasks);
    }

}
