package hu.ait.android.aittime;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout layoutContent = findViewById(R.id.layoutContent);

        final EditText etMessage = findViewById(R.id.etMessage);
        final TextView tvStatus = findViewById(R.id.tvStatus);
        Button btnTime = findViewById(R.id.btnTime);

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time =
                        etMessage.getText().toString()+
                        getString(R.string.time_head)+
                                new Date(System.currentTimeMillis()).toString();

                /*Toast.makeText(MainActivity.this,
                        time,
                        Toast.LENGTH_SHORT).show();*/

                tvStatus.setText(time);

                Snackbar.make(layoutContent,
                        time,
                        Snackbar.LENGTH_LONG).show();
            }
        });

    }
}
