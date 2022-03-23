package service;

import pojo.TaskClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCService {

    private final Connection connection;

    public JDBCService() throws SQLException {
        this.connection = getConnection();
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
                task.setId(resultSet.getInt("id"));
                task.setAuthor(resultSet.getString("author"));
                task.setTaskName(resultSet.getString("task_name"));
                list.add(task);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    private static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/connection.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("userName"),
                properties.getProperty("password"));
    }

}
