package com.example.bought

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class GroceryAdapter(private val context: Context, private val dataSource: ArrayList<Grocery>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.list_item_grocery, parent, false)

        val textViewName = rowView.findViewById(R.id.textViewName) as TextView
        val textViewPrice = rowView.findViewById(R.id.textViewPrice) as TextView
        val textViewType = rowView.findViewById(R.id.textViewType) as TextView
        val textViewMemo = rowView.findViewById(R.id.textViewMemo) as TextView
        val textViewStore = rowView.findViewById(R.id.textViewStore) as TextView

        val storeIconImageView = rowView.findViewById(R.id.storeIconImageView) as ImageView


        val grocery = getItem(position) as Grocery

        textViewName.text = grocery.name
        textViewPrice.text = grocery.price.toString()
        textViewType.text = grocery.type
        textViewMemo.text = grocery.memo
        textViewStore.text = grocery.store


        var ic_image : Int = R.drawable.ic_deft

        if (grocery.store == "Loblaws") {
            ic_image = R.drawable.ic_loblaws
        }else if (grocery.store == "Metro") {
            ic_image = R.drawable.ic_metro
        }else if (grocery.store == "Real Canadian Superstore") {
            ic_image = R.drawable.ic_realcanadian
        }else if (grocery.store == "Walmart") {
            ic_image = R.drawable.ic_walmart
        }else if (grocery.store == "Safeway") {
            ic_image = R.drawable.ic_safeway
        }else if (grocery.store == "Giant Tiger") {
            ic_image = R.drawable.ic_gianttiger
        }else if (grocery.store == "Costco") {
            ic_image = R.drawable.ic_costco
        }
        storeIconImageView.setImageResource(ic_image)

        return rowView
    }
}
