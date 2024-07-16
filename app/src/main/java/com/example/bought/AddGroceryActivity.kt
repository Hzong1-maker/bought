package com.example.bought

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.ComponentActivity

class AddGroceryActivity : ComponentActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextPrice: EditText
    private lateinit var editTextPurchaseDate: EditText
    private lateinit var editTextMemo: EditText
    private lateinit var editTextType: EditText

    private lateinit var spinnerStore: Spinner

    private lateinit var btnSave: Button
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_grocery)

        editTextName = findViewById(R.id.editTextName)
        editTextPrice = findViewById(R.id.editTextPrice)
        editTextPurchaseDate = findViewById(R.id.editTextPurchaseDate)
        editTextMemo = findViewById(R.id.editTextMemo)
        editTextType = findViewById(R.id.editTextType)

        spinnerStore = findViewById(R.id.spinnerStore)

        btnSave = findViewById(R.id.btnSave)


        ArrayAdapter.createFromResource(
            this,
            R.array.grocery_stores,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerStore.adapter = adapter
        }



        intent?.let {



            editTextName.setText(it.getStringExtra("name"))
            editTextPrice.setText(it.getStringExtra("price"))
            editTextPurchaseDate.setText(it.getStringExtra("purchaseDate"))
            editTextType.setText(it.getStringExtra("type"))
            editTextMemo.setText(it.getStringExtra("memo"))

            val store = it.getStringExtra("store")
            position = it.getIntExtra("position", -1)
            val departmentIndex = resources.getStringArray(R.array.grocery_stores).indexOf(store)
            spinnerStore.setSelection(departmentIndex)
        }

        btnSave.setOnClickListener {
            val name = editTextName.text.toString()
            val price = editTextPrice.text.toString()
            val purchaseDate = editTextPurchaseDate.text.toString()
            val type = editTextType.text.toString()
            val memo = editTextMemo.text.toString()

            val store = spinnerStore.selectedItem.toString()
            val resultIntent = Intent()

            resultIntent.putExtra("name", name)
            resultIntent.putExtra("price", price)
            resultIntent.putExtra("purchaseDate", purchaseDate)
            resultIntent.putExtra("type", type)
            resultIntent.putExtra("memo", memo)

            resultIntent.putExtra("store", store)
            resultIntent.putExtra("position", position)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}