package service;

import org.apache.log4j.Logger;
import pojo.TaskClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCService {
    public static final Logger log = Logger.getLogger(JDBCService.class);

    private static JDBCService jdbcService;

    private final Connection connection;

    private JDBCService(String url, String userName, String password) throws Exception {
        try {
            this.connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            log.error(String.format("Do not connection with url=%s, userName=%s, password=%s",
                    url, userName, password.replaceAll(".", "*")), e);
            throw e;
        }
    }

    public void add(String taskName, String author) throws Exception {
        String query = String.format("INSERT INTO tasks (task_name, author) VALUES ('%s', '%s')", taskName, author);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            log.error(String.format("Could not insert task into table. TaskName =%s, Author=%s",
                    taskName, author), e);
            throw e;
        }
    }

    public void delete(int id) throws Exception {
        String query = String.format("DELETE FROM tasks WHERE id=%d", id);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            log.error(String.format("Could not delete task into table. TaskId=%d", id), e);
            throw e;
        }
    }

    public List<TaskClass> getAll() throws Exception {
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
        } catch (Exception e) {
            log.error("Could not get all tasks from table.", e);
            throw e;
        }

        return list;
    }

    public static void createInstance(String url, String userName, String password) throws Exception {
        if (jdbcService == null) {
            jdbcService = new JDBCService(url, userName, password);
        }
    }

    public static JDBCService getInstance() {
        return jdbcService;
    }
}
