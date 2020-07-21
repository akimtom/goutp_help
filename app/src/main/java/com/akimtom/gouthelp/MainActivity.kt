package com.akimtom.gouthelp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.akimtom.gouthelp.product.ProductActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var buttonProductList:Button
    private lateinit var buttonHealingList:Button
    private lateinit var buttonRecipesList:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonProductList = btn_products
        buttonHealingList = btn_healing
        buttonRecipesList = btn_recipes

        buttonProductList.setOnClickListener {
            startActivity(Intent(this,
                ProductActivity::class.java))
        }
        buttonHealingList.setOnClickListener {
            startActivity(Intent(this,HealingActivity::class.java))
        }
        buttonRecipesList.setOnClickListener {
            startActivity(Intent(this,RecipesActivity::class.java))
        }
    }
}