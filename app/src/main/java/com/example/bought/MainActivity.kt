package com.example.bought

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bought.ui.theme.BoughtTheme


class MainActivity : ComponentActivity() {
    private lateinit var btnAddgrocery: Button
    private lateinit var listViewGroceries: ListView

    private lateinit var groceryAdapter: GroceryAdapter
    private lateinit var groceryList: ArrayList<Grocery>

    private val addGroceryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            data?.let {

                val name = it.getStringExtra("name")
                val price = it.getStringExtra("price")
                val purchaseDate = it.getStringExtra("purchaseDate")
                val type = it.getStringExtra("type")
                val memo = it.getStringExtra("memo")


                val store = it.getStringExtra("store")
                val position = it.getIntExtra("position", -1)
                if (name != null && price != null && purchaseDate != null && type != null && memo != null && store != null) {
                    if (position == -1) {
                        groceryList.add(Grocery(name,price,purchaseDate,store,type,memo))
                    } else {
                        groceryList[position] = Grocery(name,price,purchaseDate,store,type,memo)
                    }
                    groceryAdapter.notifyDataSetChanged()
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddgrocery = findViewById(R.id.btnAddGrocery)
        listViewGroceries = findViewById(R.id.listViewGroceries)

        groceryList = ArrayList()
        groceryAdapter = GroceryAdapter(this, groceryList)
        listViewGroceries.adapter = groceryAdapter

        btnAddgrocery.setOnClickListener {
            val intent = Intent(this, AddGroceryActivity::class.java)
            addGroceryLauncher.launch(intent)
        }

        listViewGroceries.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val grocery = groceryList[position]
            val intent = Intent(this, AddGroceryActivity::class.java).apply {
                putExtra("name", grocery.name)
                putExtra("price", grocery.price)
                putExtra("type", grocery.type)
                putExtra("memo", grocery.memo)

                putExtra("store", grocery.store)
                putExtra("position", position)
            }
            addGroceryLauncher.launch(intent)
        }
    }
}
