package pojo;

public class TaskClass {

    private int id;
    private String taskName;
    private String author;

    public TaskClass() {
    }

    public TaskClass(int id, String taskName, String author) {
        this.id = id;
        this.taskName = taskName;
        this.author = author;
    }

    public TaskClass(String taskName, String author) {
        this.taskName = taskName;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
