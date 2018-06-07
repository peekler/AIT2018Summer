package hu.ait.android.highlowgame;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    public static final String KEY_GEN = "KEY_GEN";
    public static final String KEY_MSG = "KEY_MSG";
    private int generated = 0;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvStatus = findViewById(R.id.tvStatus);

        if (savedInstanceState != null) {
            generated = savedInstanceState.getInt(KEY_GEN);
            tvStatus.setText(savedInstanceState.getString(KEY_MSG));
        } else {
            generateNewNumber();
        }

        final EditText etGuess = findViewById(R.id.etGuess);
        Button btnGuess = findViewById(R.id.btnGuess);
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if (!TextUtils.isEmpty(etGuess.getText().toString())) {
                        int num = Integer.parseInt(etGuess.getText().toString());

                        if (num == generated) {
                            tvStatus.setText("Congratulations!");

                            Intent intentResult = new Intent();
                            intentResult.setClass(GameActivity.this, ResultActivity.class);
                            startActivity(intentResult);

                        } else if (num < generated) {
                            tvStatus.setText("Your number is smaller");
                        } else if (num > generated) {
                            tvStatus.setText("Your number is larger");
                        }
                    } else {
                        etGuess.setError("This field can not be empty");
                    }

                }catch (Exception e) {
                    etGuess.setError(e.getMessage());
                }
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_GEN, generated);
        outState.putString(KEY_MSG, tvStatus.getText().toString());

        super.onSaveInstanceState(outState);
    }

    private void generateNewNumber() {
        Random rand = new Random(System.currentTimeMillis());
        generated = rand.nextInt(3);
    }
}
