package com.akimtom.gouthelp.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akimtom.gouthelp.R
import kotlinx.android.synthetic.main.product_element.view.*


class ProductAdapter (val products: List<Product>): RecyclerView.Adapter<ProductAdapter.ProductHolder>() {


    var onItemClick: ((Product) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_element, parent, false)
        return ProductHolder(v)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.titleProd.text = products[position].title
        holder.purinaProd.text = products[position].purinaCount.toString()
        holder.imgProduct.setImageResource(products[position].imgSrsSmall)


    }
  inner  class ProductHolder( itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleProd: TextView = itemView.product_title
        val purinaProd: TextView = itemView.purina_count
        val imgProduct: ImageView = itemView.product_img

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(products[adapterPosition])
            }


        }
    }


}


