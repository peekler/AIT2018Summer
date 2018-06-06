package hu.ait.android.tictactoe;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import org.w3c.dom.Text;

import hu.ait.android.tictactoe.model.TicTacToeModel;
import hu.ait.android.tictactoe.ui.TicTacToeView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG_TICTAC = "TAG_TICTAC";
    private TextView tvStatus;
    private LinearLayout layoutContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutContent = findViewById(R.id.layoutContent);

        tvStatus = findViewById(R.id.tvStatus);

        final TicTacToeView ticTacToeView = findViewById(R.id.ticTacToeView);

        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticTacToeView.resetGame();

                Log.d(TAG_TICTAC, "clear pressed");
            }
        });

        ShimmerFrameLayout shimmerFrameLayout = findViewById(R.id.shimmerLayout);
        shimmerFrameLayout.startShimmerAnimation();
    }

    public void showMessage(String message) {
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        tvStatus.setText(message);

        Snackbar.make(layoutContent,
                message,
                Snackbar.LENGTH_LONG).setAction("Retry",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvStatus.setText("hello");
                    }
                }).show();
    }
}
