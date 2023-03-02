package com.kenzie.komikapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CardViewKomik (private val listKomik : ArrayList<Komik>)
    : RecyclerView.Adapter<CardViewKomik.CardViewViewHolder>() {
    inner class CardViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvSinopsis: TextView = itemView.findViewById(R.id.tv_item_sinopsis)
        var btnFavorite: Button = itemView.findViewById(R.id.btn_set_favorite)
        var btnShare = itemView.findViewById<Button>(R.id.btn_add_library)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_komik, parent, false)
        return CardViewViewHolder(view)

    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val (name, sinopsis, photo) = listKomik[position]

        Glide.with(holder.itemView.context)
            .load(photo)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgPhoto)

        holder.tvName.text = name
        holder.tvSinopsis.text = sinopsis

        holder.btnFavorite.setOnClickListener { Toast.makeText(holder.itemView.context, "Favorite " + listKomik[position].name, Toast.LENGTH_SHORT).show() }

        holder.btnShare.setOnClickListener { Toast.makeText(holder.itemView.context, "Add to Library " + listKomik[position].name, Toast.LENGTH_SHORT).show() }

        holder.itemView.setOnClickListener { Toast.makeText(holder.itemView.context, "Choose " + listKomik[position].name, Toast.LENGTH_SHORT).show() }



    }

    override fun getItemCount(): Int {
        return listKomik.size
    }

}
