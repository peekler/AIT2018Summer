package hu.ait.android.todoreyclerview.data;

public class Todo {
    private String todoDate;
    private String todoTitle;
    private boolean done;

    public Todo(String todoDate, String todoTitle, boolean done) {
        this.todoDate = todoDate;
        this.todoTitle = todoTitle;
        this.done = done;
    }

    public String getTodoDate() {
        return todoDate;
    }

    public void setTodoDate(String todoDate) {
        this.todoDate = todoDate;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
