package hu.ait.android.httpmoneydemo.network;


import hu.ait.android.httpmoneydemo.data.MoneyResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoneyAPI {

    @GET("api/latest")
    Call<MoneyResult> getRates(@Query("access_key") String accessKey,
                               @Query("format") int format);

}


//http://
//data.fixer.io
///api/latest
// ?
// access_key=969c37b5a73f8cb2d12c081dcbdc35e6
// &
// format=1