package hu.ait.android.todoreyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import hu.ait.android.todoreyclerview.MainActivity;
import hu.ait.android.todoreyclerview.R;
import hu.ait.android.todoreyclerview.data.AppDatabase;
import hu.ait.android.todoreyclerview.data.Todo;
import hu.ait.android.todoreyclerview.touch.TodoTouchHelperAdapter;

public class TodoRecyclerAdapter
        extends RecyclerView.Adapter<TodoRecyclerAdapter.ViewHolder>
        implements TodoTouchHelperAdapter {

    private List<Todo> todoList;
    private Context context;

    public TodoRecyclerAdapter(Context context, List<Todo> todoList) {
        this.context = context;
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View todoRowView =
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.todo_row, parent, false);

        return new ViewHolder(todoRowView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Todo todo = todoList.get(position);

        holder.tvTodoDate.setText(todo.getTodoDate());
        holder.cbDone.setText(todo.getTodoTitle());
        holder.cbDone.setChecked(todo.isDone());

        holder.cbDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todo.setDone(holder.cbDone.isChecked());
                new Thread() {
                    @Override
                    public void run() {
                        AppDatabase.getAppDatabase(context).todoDao().updateTodo(todo);
                    }
                }.start();
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTodoBasedOnPosition(holder.getAdapterPosition());
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).showEditTodoDialog(
                        todo
                );
            }
        });
    }

    public void deleteTodoBasedOnPosition(int position) {
        final Todo todoToDelete = todoList.get(position);
        new Thread() {
            @Override
            public void run() {
                AppDatabase.getAppDatabase(context).todoDao().deleteTodo(
                        todoToDelete);
            }
        }.start();

        todoList.remove(position);
        //notifyDataSetChanged();
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public void addTodo(Todo todo) {
        todoList.add(todo);
        notifyDataSetChanged();
        //notifyItemInserted(todoList.size()-1);
    }

    @Override
    public void onItemDismissed(int position) {
        deleteTodoBasedOnPosition(position);
    }

    @Override
    public void onItemMoved(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(todoList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(todoList, i, i - 1);
            }
        }

        //notifyDataSetChanged();
        notifyItemMoved(fromPosition, toPosition);
    }

    public void updateTodo(Todo todo) {
        int pos = findTodoIndexByTodoId(todo.getTodoId());
        if (pos != -1) {
            todoList.set(pos, todo);
            notifyItemChanged(pos);
        }
    }

    private int findTodoIndexByTodoId(long todoId) {
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getTodoId() == todoId) {
                return i;
            }
        }

        return  -1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTodoDate;
        private CheckBox cbDone;
        private Button btnDelete;
        private Button btnEdit;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTodoDate = itemView.findViewById(R.id.tvDate);
            cbDone = itemView.findViewById(R.id.cbDone);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }
}
