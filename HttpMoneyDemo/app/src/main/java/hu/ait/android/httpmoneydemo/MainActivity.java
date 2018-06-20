package hu.ait.android.httpmoneydemo;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import hu.ait.android.httpmoneydemo.data.MoneyResult;
import hu.ait.android.httpmoneydemo.network.MoneyAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private TextView tvData;

    //http://data.fixer.io/api/latest?access_key=969c37b5a73f8cb2d12c081dcbdc35e6&format=1


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://data.fixer.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final MoneyAPI moneyAPI = retrofit.create(MoneyAPI.class);


        tvData = findViewById(R.id.tvData);
        Button btnGetMoney = findViewById(R.id.btnGetMoney);
        btnGetMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moneyAPI.getRates(
                        "969c37b5a73f8cb2d12c081dcbdc35e6",
                        1
                ).enqueue(new Callback<MoneyResult>() {
                    @Override
                    public void onResponse(Call<MoneyResult> call, Response<MoneyResult> response) {
                        MoneyResult result = response.body();
                        tvData.setText(""+result.getRates().getuSD());
                    }

                    @Override
                    public void onFailure(Call<MoneyResult> call, Throwable t) {
                        tvData.setText(t.getMessage());
                    }
                });
            }
        });
    }

}
