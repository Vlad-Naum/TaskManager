package service;

import pojo.TaskClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCService {

    private static JDBCService jdbcService;

    private final Connection connection;

    private JDBCService(String url, String userName, String password) throws SQLException {
        this.connection = DriverManager.getConnection(url, userName, password);
    }

    public void add(String taskName, String author) {
        String query = String.format("INSERT INTO tasks (task_name, author) VALUES ('%s', '%s')", taskName, author);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = String.format("DELETE FROM tasks WHERE id=%d", id);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TaskClass> getAll() {
        String query = "SELECT * FROM tasks";

        List<TaskClass> list = new ArrayList<TaskClass>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                TaskClass task = new TaskClass();
                task.setTaskId(resultSet.getInt("id"));
                task.setTaskAuthor(resultSet.getString("author"));
                task.setTaskNameProperty(resultSet.getString("task_name"));
                list.add(task);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public static void createInstance(String url, String userName, String password) throws SQLException {
        if (jdbcService == null) {
            jdbcService = new JDBCService(url, userName, password);
        }
    }

    public static JDBCService getInstance() {
        return jdbcService;
    }
}
