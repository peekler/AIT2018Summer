package hu.ait.android.todoreyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import hu.ait.android.todoreyclerview.adapter.TodoReyclerAdapter;
import hu.ait.android.todoreyclerview.data.Todo;
import hu.ait.android.todoreyclerview.touch.TodoItemTouchHelperCallback;

public class MainActivity extends AppCompatActivity
        implements NewTodoDialog.TodoHandler {

    private TodoReyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNewTodoDialog();
            }
        });

        RecyclerView recyclerViewTodo = findViewById(R.id.recyclerTodo);
        recyclerViewTodo.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTodo.setHasFixedSize(true);

        adapter = new TodoReyclerAdapter();
        recyclerViewTodo.setAdapter(adapter);


        ItemTouchHelper.Callback callback =
                new TodoItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerViewTodo);
    }

    public void showNewTodoDialog() {
        new NewTodoDialog().show(getSupportFragmentManager(),
                "TAG_NEW_TODO");
    }

    @Override
    public void todoCreated(Todo todo) {
        adapter.addTodo(todo);
    }
}
