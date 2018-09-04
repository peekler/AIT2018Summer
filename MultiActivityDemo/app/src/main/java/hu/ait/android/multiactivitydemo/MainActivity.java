package hu.ait.android.multiactivitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_NAME = "KEY_NAME";
    public static final String TAG_LIFE = "TAG_LIFE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etName = findViewById(R.id.etName);
        Button btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStartPayment = new Intent();
                intentStartPayment.setClass(MainActivity.this,
                        PaymentActivity.class);
                intentStartPayment.putExtra(KEY_NAME,
                        etName.getText().toString());

                startActivityForResult(intentStartPayment,
                        1001);

                //finish();
            }
        });

        Log.d(TAG_LIFE, "A onCreate called");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG_LIFE, "A onActivityResult");

        if (requestCode == 1001 && resultCode == RESULT_OK) {
            String pay = data.getStringExtra(PaymentActivity.KEY_PAY);
            Toast.makeText(this, "Payment done: "+pay, Toast.LENGTH_SHORT).show();
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "CANCELLED", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG_LIFE, "A onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG_LIFE, "A onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG_LIFE, "A onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG_LIFE, "A onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG_LIFE, "A onDestroy called");
    }
}
