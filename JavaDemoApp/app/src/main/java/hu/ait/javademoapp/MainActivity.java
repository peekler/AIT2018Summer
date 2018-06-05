package hu.ait.javademoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hu.ait.javademoapp.abstractclasses.FiboSerie;
import hu.ait.javademoapp.abstractclasses.HarmonicSerie;
import hu.ait.javademoapp.interfaces.FiboCalcWithHighMemory;
import hu.ait.javademoapp.interfaces.FiboCalcWithLessMemory;
import hu.ait.javademoapp.interfaces.FibonacciCalculator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showNextFiboNumberOnButtonClick();

        showSeriesOnButtonClick();

    }

    private void showSeriesOnButtonClick() {
        final TextView fiboSeriesTextView = (TextView) findViewById(R.id.fiboSerieTextView);
        final TextView harmonicSeriesTextView = (TextView) findViewById(R.id.harmonicSerieTextView);

        Button showSeriesButton = (Button) findViewById(R.id.showSeriesButton);
        showSeriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                harmonicSeriesTextView.setText(new HarmonicSerie(6).getSerie().toString());
                fiboSeriesTextView.setText(new FiboSerie(6).getSerie().toString());
            }
        });
    }

    private void showNextFiboNumberOnButtonClick() {
        final TextView nextFiboNumberTextView = (TextView) findViewById(R.id.fiboNumberTextView);
        final FibonacciCalculator fiboNumberCalculator = new FiboCalcWithHighMemory();

        Button button = (Button) findViewById(R.id.nextButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nextFiboNumber = fiboNumberCalculator.calculateNextFiboNumber().toString();
                nextFiboNumberTextView.setText(nextFiboNumber);
            }
        });
    }
}
