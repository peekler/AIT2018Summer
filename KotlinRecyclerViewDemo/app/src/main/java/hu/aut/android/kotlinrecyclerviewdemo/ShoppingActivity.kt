package hu.aut.android.kotlinrecyclerviewdemo

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import hu.aut.android.kotlinrecyclerviewdemo.data.ShoppingItem
import kotlinx.android.synthetic.main.activity_shopping.*
import kotlinx.android.synthetic.main.content_shopping.*

class ShoppingActivity : AppCompatActivity() {

    private val adapter = ShoppingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
        setSupportActionBar(toolbar)

        recyclerShopping.adapter = adapter

        btnAddItem.setOnClickListener{
            if (etName.text.isNotBlank() &&
                    etPrice.text.isNotBlank()) {
                addItem(etName.text.toString(), etPrice.text.toString().toInt())
            }
        }
    }

    private fun addItem(name: String, price: Int) {
        adapter.addItem(ShoppingItem(name, price))
        recyclerShopping.smoothScrollToPosition(adapter.itemCount)
    }
}
