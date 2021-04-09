package com.derek.recycleviewstudy

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.derek.recycleviewstudy.R.layout.activity_main

class MainActivity : AppCompatActivity() {

    val fruitsList = listOf<Fruits>(
        Fruits("Manga", "derek"),
        Fruits("Banada", "cavalo"), Fruits
            ("DEREK", "vAlentina")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        my_recycler_view.setBackgroundColor(Color.YELLOW)
        my_recycler_view.layoutManager = LinearLayoutManager(this)
        my_recycler_view.adapter =
            Adapter(fruitsList, { selectedFruitItem: Fruits -> listItemClicked(selectedFruitItem) })
    }

    private fun listItemClicked(fruit: Fruits) {
        Toast.makeText(this@MainActivity, "Supplier name is ${fruit.supplier}", Toast.LENGTH_LONG)
            .show()
    }
}