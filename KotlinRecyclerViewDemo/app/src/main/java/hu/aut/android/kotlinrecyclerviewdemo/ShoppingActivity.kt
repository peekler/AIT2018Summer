package hu.aut.android.kotlinrecyclerviewdemo

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import hu.aut.android.kotlinrecyclerviewdemo.data.ShoppingItem
import kotlinx.android.synthetic.main.activity_shopping.*
import kotlinx.android.synthetic.main.content_shopping.*

class ShoppingActivity : AppCompatActivity() {
    companion object {
        val KEY_ITEM_TO_EDIT = "KEY_ITEM_TO_EDIT"
        val KEY_WAS_STARTED = "KEY_WAS_STARTED"
    }

    private val adapter = ShoppingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { showNewTodoDialog() }

        recyclerShopping.adapter = adapter
    }


    private fun showNewTodoDialog() {
        ShoppingItemDialog().show(supportFragmentManager,
                "TAG_NEW_ITEM")
    }
}
