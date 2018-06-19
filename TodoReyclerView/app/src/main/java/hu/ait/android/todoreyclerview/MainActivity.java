package hu.ait.android.todoreyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.List;

import hu.ait.android.todoreyclerview.adapter.TodoRecyclerAdapter;
import hu.ait.android.todoreyclerview.data.AppDatabase;
import hu.ait.android.todoreyclerview.data.Todo;
import hu.ait.android.todoreyclerview.touch.TodoItemTouchHelperCallback;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class MainActivity extends AppCompatActivity
        implements NewAndEditTodoDialog.TodoHandler {

    public static final String KEY_TODO_TO_EDIT = "KEY_TODO_TO_EDIT";
    private TodoRecyclerAdapter adapter;

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

        new MaterialTapTargetPrompt.Builder(MainActivity.this)
                .setTarget(findViewById(R.id.fab))
                .setPrimaryText("New todo")
                .setSecondaryText("Tap the envelop to create new todo")
                .show();

        RecyclerView recyclerViewTodo = findViewById(R.id.recyclerTodo);
        recyclerViewTodo.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTodo.setHasFixedSize(true);

        initRecyclerView(recyclerViewTodo);
    }

    private void initRecyclerView(final RecyclerView recyclerViewTodo) {
        new Thread() {
            @Override
            public void run() {
                final List<Todo> todos =
                        AppDatabase.getAppDatabase(
                                MainActivity.this).todoDao().getAllTodo();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new TodoRecyclerAdapter(
                                MainActivity.this, todos);
                        recyclerViewTodo.setAdapter(adapter);

                        ItemTouchHelper.Callback callback =
                                new TodoItemTouchHelperCallback(adapter);
                        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
                        touchHelper.attachToRecyclerView(recyclerViewTodo);
                    }
                });
            }
        }.start();
    }

    public void showEditTodoDialog(Todo todoToEdit) {
        NewAndEditTodoDialog editTodoDialog = new NewAndEditTodoDialog();

        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_TODO_TO_EDIT, todoToEdit);
        editTodoDialog.setArguments(bundle);

        editTodoDialog.show(getSupportFragmentManager(),
                "EDITTODODIALOG");
    }


    public void showNewTodoDialog() {
        new NewAndEditTodoDialog().show(getSupportFragmentManager(),
                "TAG_NEW_TODO");
    }

    @Override
    public void todoCreated(final Todo todo) {
        new Thread() {
            @Override
            public void run() {
                long id = AppDatabase.getAppDatabase(MainActivity.this).todoDao().
                        insertTodo(todo);

                todo.setTodoId(id);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addTodo(todo);
                    }
                });
            }
        }.start();
    }

    @Override
    public void todoUpdated(final Todo todo) {
        new Thread() {
            @Override
            public void run() {
                AppDatabase.getAppDatabase(MainActivity.this).todoDao().
                        updateTodo(todo);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateTodo(todo);
                    }
                });
            }
        }.start();
    }
}
