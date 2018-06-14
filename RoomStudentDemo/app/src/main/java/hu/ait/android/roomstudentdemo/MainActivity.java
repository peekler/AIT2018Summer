package hu.ait.android.roomstudentdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import hu.ait.android.roomstudentdemo.data.AppDatabase;
import hu.ait.android.roomstudentdemo.data.Grade;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etStudentId = findViewById(R.id.etStudentId);
        final EditText etGrade = findViewById(R.id.etGrade);
        final TextView tvResult = findViewById(R.id.tvResult);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Grade grade = new Grade(etStudentId.getText().toString(),
                        etGrade.getText().toString());

                new Thread() {
                    @Override
                    public void run() {
                        AppDatabase.getAppDatabase(MainActivity.this).gradeDao().insertGrades(
                                grade);
                    }
                }.start();
            }
        });

        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        final List<Grade> grades =
                            AppDatabase.getAppDatabase(MainActivity.this).gradeDao().getSpecificGrades("A+");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvResult.setText("");

                                for (Grade grade : grades) {
                                    tvResult.append(grade.getStudentId()+" "+grade.getGrade()+"\n");
                                }
                            }
                        });
                    }
                }.start();
            }
        });


    }
}
