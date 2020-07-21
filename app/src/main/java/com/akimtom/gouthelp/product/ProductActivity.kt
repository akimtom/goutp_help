package com.akimtom.gouthelp.product

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akimtom.gouthelp.MainActivity
import com.akimtom.gouthelp.R
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.dialog.view.*
import java.time.Duration


private lateinit var recyclerProduct: RecyclerView
private lateinit var productAdapter: RecyclerView.Adapter<ProductAdapter.ProductHolder>
private lateinit var autoComplete:AutoCompleteTextView
//private lateinit var viewManager: RecyclerView.LayoutManager
private val products = mutableListOf<Product>()

class ProductActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        recyclerProduct = recy_pr
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val imgs = resources.obtainTypedArray(R.array.imgess)
        val acids = resources.getIntArray(R.array.acid_count)
        val titles:Array<String> = resources.getStringArray(R.array.prod_title)
        val descriptions:Array<String> = resources.getStringArray(R.array.prod_decsr)
        if(products.isEmpty()){

            for ((index) in titles.withIndex()) {
            products.add(Product(titles[index],descriptions[index],acids[index],imgs.getResourceId(index,0)))
        }
        }

           productAdapter = ProductAdapter(products)
            (productAdapter as ProductAdapter).onItemClick = { product ->
                val intent = Intent(this, ProductDescriptionActivity::class.java).apply {
                    putExtra("TITLE",product.title)
                    putExtra("IMG",product.imgSrsSmall)
                    putExtra("DES",product.description)
                    putExtra("PUR",product.purinaCount)


                }
                startActivity(intent)
            }
        recyclerProduct.layoutManager = LinearLayoutManager(this)
        recyclerProduct.adapter = productAdapter

        recyclerProduct.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.HORIZONTAL))

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)



    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.find-> {
                showAddDialog()
                return true
            }
           android.R.id.home->{
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAddDialog() {
        val viewDialog = LayoutInflater.from(this).inflate(R.layout.dialog,null)
        val autoText: AutoCompleteTextView = viewDialog.auto_c
        val list  = products.map { it.title }
        autoText.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line, list
            )
        )
        val builder = AlertDialog.Builder(this)
        builder.setView(viewDialog)
        builder.setTitle("Поиск")
        builder.setNegativeButton("Выйти") {
                dialog, _ ->
            dialog.cancel()
        }
        builder.setPositiveButton("Найти"
        ) { dialog, _ ->
           val  productSelect = products.filter { it.title ==autoText.text.toString() }
            if(productSelect.isNotEmpty()) {

                val intent = Intent(this, ProductDescriptionActivity::class.java).apply {
                    putExtra("TITLE", productSelect[0].title)
                    putExtra("IMG", productSelect[0].imgSrsSmall)
                    putExtra("DES", productSelect[0].description)
                    putExtra("PUR", productSelect[0].purinaCount)


                }
                startActivity(intent)
            }
            else
                Toast.makeText(this,"введите что хотите найти",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        builder.create().show()
    }



}