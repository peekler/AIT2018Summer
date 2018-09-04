package hu.aut.android.kotlinrecyclerviewdemo

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.widget.EditText
import hu.aut.android.kotlinrecyclerviewdemo.data.ShoppingItem
import java.util.*

class ShoppingItemDialog : DialogFragment() {

    private var shoppingItemHandler: ShoppingItemHandler? = null

    private var etItem: EditText? = null

    interface ShoppingItemHandler {
        fun shoppingItemCreated(item: ShoppingItem)

        fun shoppingItemUpdated(item: ShoppingItem)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is ShoppingItemHandler) {
            shoppingItemHandler = context
        } else {
            throw RuntimeException("The Activity does not implement the ShoppingItemHandler interface")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("New Item")

        initDialogContent(builder)

        builder.setPositiveButton("OK") { dialog, which ->
            // keep it empty
        }
        return builder.create()
    }

    private fun initDialogContent(builder: AlertDialog.Builder) {
        etItem = EditText(activity)
        builder.setView(etItem)
        if (arguments != null && arguments!!.containsKey(ShoppingActivity.KEY_ITEM_TO_EDIT)) {
            val item = arguments!!.getSerializable(
                    ShoppingActivity.KEY_ITEM_TO_EDIT) as ShoppingItem
            etItem?.setText(item.name)

            builder.setTitle("Edit todo")
        }
    }


    override fun onResume() {
        super.onResume()

        val dialog = dialog as AlertDialog
        if (dialog != null) {
            val positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE)

            positiveButton.setOnClickListener {
                if (!TextUtils.isEmpty(etItem?.text.toString())) {
                    if (arguments != null && arguments!!.containsKey(ShoppingActivity.KEY_ITEM_TO_EDIT)) {
                        handleItemEdit()
                    } else {
                        handleItemCreate()
                    }

                    dialog.dismiss()
                } else {
                    etItem?.error = "This field can not be empty"
                }
            }
        }
    }

    private fun handleItemCreate() {
        shoppingItemHandler?.shoppingItemCreated(ShoppingItem(
                etItem?.text.toString(),
                1000,
                false
        ))
    }

    private fun handleItemEdit() {
        val itemToEdit = arguments?.getSerializable(
                ShoppingActivity.KEY_ITEM_TO_EDIT) as ShoppingItem
        itemToEdit?.name = etItem?.text.toString()

        shoppingItemHandler?.shoppingItemUpdated(itemToEdit)
    }
}
