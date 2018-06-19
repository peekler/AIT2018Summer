package hu.ait.android.todoreyclerview.data;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Todo implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long todoId;

    @ColumnInfo(name = "todo_date")
    private String todoDate;
    @ColumnInfo(name = "todo_title")
    private String todoTitle;
    @ColumnInfo(name = "done")
    private boolean done;

    public Todo(String todoDate, String todoTitle, boolean done) {
        this.todoDate = todoDate;
        this.todoTitle = todoTitle;
        this.done = done;
    }

    public long getTodoId() {
        return todoId;
    }

    public void setTodoId(long todoId) {
        this.todoId = todoId;
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
