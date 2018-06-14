package hu.ait.android.threaddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvStatus;
    private boolean threadEnabled = false;


    private class MyThread extends Thread {
        @Override
        public void run() {
            while (threadEnabled) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvStatus.append("$");
                    }
                });

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvStatus = findViewById(R.id.tvStatus);
        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                threadEnabled = true;
                new MyThread().start();

                //tvStatus.setText("Hello");

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        threadEnabled = false;
    }
}
