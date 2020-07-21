package com.akimtom.gouthelp.product

import android.os.Bundle
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.akimtom.gouthelp.R
import kotlinx.android.synthetic.main.activity_product_description.*


class ProductDescriptionActivity : AppCompatActivity() {

    lateinit var scroll:ScrollView
    lateinit var titleDesc:TextView
    lateinit var purinaCount:TextView
    lateinit var imgeView: ImageView
    lateinit var textScroll: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_description)
        purinaCount = purina_count_description
        scroll = scroll_desc
        titleDesc =title_desc
        imgeView=img_big
        textScroll = textScr
        val intent = intent
        val imgS = intent.getIntExtra("IMG",0)
        val title = intent.getStringExtra("TITLE")
        val textSc = intent.getStringExtra("DES")
        imgeView.setImageResource(imgS)
        textScroll.text = textSc
        titleDesc.text = title
        purinaCount.text = purinaCount.text.toString()+" "+intent.getIntExtra("PUR", 0).toString()

    }
}