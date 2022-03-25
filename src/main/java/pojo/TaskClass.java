package pojo;

import javafx.beans.property.SimpleStringProperty;

public class TaskClass {

    private final SimpleStringProperty taskId;
    private final SimpleStringProperty taskNameProperty;
    private final SimpleStringProperty taskAuthor;

    public TaskClass() {
        taskId = new SimpleStringProperty();
        taskNameProperty = new SimpleStringProperty();
        taskAuthor = new SimpleStringProperty();
    }

    public TaskClass(int taskId, String taskNameProperty, String taskAuthor) {
        this.taskId = new SimpleStringProperty(String.valueOf(taskId));
        this.taskNameProperty = new SimpleStringProperty(taskNameProperty);
        this.taskAuthor = new SimpleStringProperty(taskAuthor);
    }

    public String getTaskId() {
        return taskId.get();
    }

    public void setTaskId(Integer taskId) {
        this.taskId.set(String.valueOf(taskId));
    }

    public String getTaskNameProperty() {
        return taskNameProperty.get();
    }

    public void setTaskNameProperty(String taskNameProperty) {
        this.taskNameProperty.set(taskNameProperty);
    }

    public String getTaskAuthor() {
        return taskAuthor.get();
    }

    public void setTaskAuthor(String taskAuthor) {
        this.taskAuthor.set(taskAuthor);
    }
}
