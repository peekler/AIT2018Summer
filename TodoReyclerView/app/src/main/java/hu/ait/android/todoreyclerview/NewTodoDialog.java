package hu.ait.android.todoreyclerview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import java.util.Date;

import hu.ait.android.todoreyclerview.data.Todo;

public class NewTodoDialog extends DialogFragment {

    public interface TodoHandler {
        public void todoCreated(Todo todo);
    }

    private TodoHandler todoHandler;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof TodoHandler) {
            todoHandler = (TodoHandler) context;
        } else {
            throw new RuntimeException("The Activity does not implement the TodoHandler interface");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("New Todo");

        final EditText etTodo = new EditText(getActivity());
        builder.setView(etTodo);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                todoHandler.todoCreated(new Todo(
                        new Date(System.currentTimeMillis()).toString(),
                        etTodo.getText().toString(),
                        false
                ));
            }
        });
        return builder.create();
    }
}
