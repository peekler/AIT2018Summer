package hu.ait.android.multiactivitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {

    public static final String KEY_PAY = "KEY_PAY";

    public static final String TAG_LIFE = "TAG_LIFE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        TextView tvName = findViewById(R.id.tvName);

        if (getIntent().hasExtra(MainActivity.KEY_NAME)) {
            tvName.setText(getIntent().getStringExtra(MainActivity.KEY_NAME));
        }

        final EditText etPay = findViewById(R.id.etPayment);
        Button btnPay = findViewById(R.id.btnPay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intentResult = new Intent();
            intentResult.putExtra(KEY_PAY,
                    etPay.getText().toString());
            setResult(RESULT_OK, intentResult);
            finish();
            }
        });

        Log.d(TAG_LIFE, "B onCreate called");
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "YOU HAVE TO PAY", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG_LIFE, "B onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG_LIFE, "B onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG_LIFE, "B onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG_LIFE, "B onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG_LIFE, "B onDestroy called");
    }
}
