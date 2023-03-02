package com.kenzie.komikapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListKomik (private val listKomik: ArrayList<Komik>) : RecyclerView.Adapter<ListKomik.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_komik, viewGroup, false)
        return ListViewHolder(view)

    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, sinopsis, photo) = listKomik[position]
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listKomik[holder.adapterPosition]) }

        Glide.with(holder.itemView.context)
            .load(photo)
            .apply(RequestOptions().override(55, 55))
            .into(holder.imgPhoto)
        holder.tvName.text = name
        holder.tvSinopsis.text = sinopsis

    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Komik)
    }


    override fun getItemCount(): Int {
        return listKomik.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvSinopsis: TextView = itemView.findViewById(R.id.tv_item_sinopsis)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }
}
