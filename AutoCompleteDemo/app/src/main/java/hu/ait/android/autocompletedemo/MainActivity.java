package hu.ait.android.autocompletedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    /*private String[] countries = new String[] {
            "Hungary", "Austira", "Australia", "America",
            "Amsterdam", "Angola", "Afghanistan"
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView autoComplete = findViewById(R.id.autoCity);
        autoComplete.setThreshold(1);
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line,
                getResources().getStringArray(R.array.countires)
        );

        autoComplete.setAdapter(countryAdapter);
    }
}
