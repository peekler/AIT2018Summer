package hu.ait.android.todoreyclerview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import hu.ait.android.todoreyclerview.data.Todo;

public class NewAndEditTodoDialog extends DialogFragment {

    public interface TodoHandler {
        public void todoCreated(Todo todo);

        public void todoUpdated(Todo todo);
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

    private EditText etTodo;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("New Todo");

        etTodo = new EditText(getActivity());
        builder.setView(etTodo);

        if (getArguments() != null && getArguments().containsKey(MainActivity.KEY_TODO_TO_EDIT)) {
            Todo todo = (Todo) getArguments().getSerializable(
                    MainActivity.KEY_TODO_TO_EDIT);
            etTodo.setText(todo.getTodoTitle());

            builder.setTitle("Edit todo");
        }

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // keep it empty
            }
        });
        return builder.create();
    }


    @Override
    public void onResume() {
        super.onResume();

        final AlertDialog dialog = (AlertDialog) getDialog();
        if (dialog != null) {
            Button positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE);

            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(etTodo.getText().toString())) {
                        if (getArguments() != null && getArguments().containsKey(MainActivity.KEY_TODO_TO_EDIT)) {
                            Todo todoToEdit = (Todo) getArguments().getSerializable(
                                    MainActivity.KEY_TODO_TO_EDIT);
                            todoToEdit.setTodoTitle(etTodo.getText().toString());

                            todoHandler.todoUpdated(todoToEdit);
                        } else {
                            todoHandler.todoCreated(new Todo(
                                    new Date(System.currentTimeMillis()).toString(),
                                    etTodo.getText().toString(),
                                    false
                            ));
                        }

                        dialog.dismiss();
                    } else {
                        etTodo.setError("This field can not be empty");
                    }
                }
            });
        }
    }
}
