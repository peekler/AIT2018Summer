package hu.ait.android.todoreyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import hu.ait.android.todoreyclerview.R;
import hu.ait.android.todoreyclerview.data.Todo;
import hu.ait.android.todoreyclerview.touch.TodoTouchHelperAdapter;

public class TodoReyclerAdapter
        extends RecyclerView.Adapter<TodoReyclerAdapter.ViewHolder>
        implements TodoTouchHelperAdapter {

    private List<Todo> todoList;

    public TodoReyclerAdapter() {
        todoList = new ArrayList<>();

        /*for (int i = 0; i < 20; i++) {
            todoList.add(new Todo(
                    new Date(System.currentTimeMillis()).toString(),
                    "Todo "+i, false));
        }*/
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

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTodoBasedOnPosition(holder.getAdapterPosition());
            }
        });
    }

    public void deleteTodoBasedOnPosition(int position) {
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTodoDate;
        private CheckBox cbDone;
        private Button btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTodoDate = itemView.findViewById(R.id.tvDate);
            cbDone = itemView.findViewById(R.id.cbDone);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
