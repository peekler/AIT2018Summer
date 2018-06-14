package hu.ait.android.todoreyclerview.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TodoDAO {

    @Query("SELECT * FROM todo")
    List<Todo> getAllTodo();

    @Insert
    long insertTodo(Todo todo);

    @Delete
    void deleteTodo(Todo todo);

    @Update
    void updateTodo(Todo todo);

}
