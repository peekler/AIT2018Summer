package hu.ait.android.httpmoneydemo.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoneyResult {

    @SerializedName("success")
    @Expose
    public Boolean success;
    @SerializedName("timestamp")
    @Expose
    public Integer timestamp;
    @SerializedName("base")
    @Expose
    public String base;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("rates")
    @Expose
    public Rates rates;

    public Boolean getSuccess() {
        return success;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public Rates getRates() {
        return rates;
    }
}


