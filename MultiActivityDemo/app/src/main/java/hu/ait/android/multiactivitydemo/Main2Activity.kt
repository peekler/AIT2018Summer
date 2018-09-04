package hu.ait.android.multiactivitydemo

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class Main2Activity : AppCompatActivity() {

    val KEY_PAY = "TAG_LIFE"
    val payAmount = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        startActivityForResult(Intent(this,
                PaymentActivity::class.java),
                1001)

        val intentResult = Intent()
        intentResult.putExtra(KEY_PAY, payAmount)
        setResult(Activity.RESULT_OK, intentResult)
        finish()
    }
}
