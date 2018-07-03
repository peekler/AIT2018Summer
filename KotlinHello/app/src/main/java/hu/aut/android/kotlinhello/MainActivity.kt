package hu.aut.android.kotlinhello

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import hu.aut.android.kotlinhello.data.Car
import hu.aut.android.kotlinhello.data.CarRental
import hu.aut.android.kotlinhello.data.ElectricCar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btnTime.setOnClickListener{
            printToTextView(null)

            val date = Date(System.currentTimeMillis()).toString()
            tvData.text = date

            Toast.makeText(this@MainActivity, date, Toast.LENGTH_LONG).show()

            val carRental = CarRental("Best cars")
            carRental.addCar(Car("Ford", 1000))
            carRental.addCar(ElectricCar("Tesla", 4000, 500))

            tvData.text = "Total price is: ${carRental.kotlinTotalPrice()}"
        }
    }


    fun printToTextView(tv: TextView?) {
        tv?.text = "hello"
    }



}
