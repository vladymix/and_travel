package com.fabricio.travel.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fabricio.travel.R
import com.fabricio.travel.commons.SuitCase
import android.view.LayoutInflater
import android.widget.CheckBox
import com.fabricio.travel.listeners.IOnItemsSeleccted
import com.squareup.picasso.Picasso


class BagSelecctedAdapter(val context: Context) : RecyclerView.Adapter<BagSelecctedAdapter.BagHolder>() {

    private val ctx: Context
    private val list: ArrayList<SuitCase>

    init {
        this.ctx = context
        this.list = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagHolder {
        val artistView = LayoutInflater.from(context)
            .inflate(R.layout.item_seleccted, parent, false)
        return BagHolder(artistView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BagHolder, position: Int) {

        val item = list.get(position)
        holder.tvname.text = item.name
        holder.setImage(this.ctx, item.image)
    }

    fun setData(suitcase: List<SuitCase>?) {
        suitcase?.let {
            list.clear()
            list.addAll(it)
        }
        this.notifyDataSetChanged()
    }


    class BagHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView
        val tvname: TextView

        init {
            image = view.findViewById(R.id.iv_suite)
            tvname = view.findViewById(R.id.tv_name)
        }

        fun setImage(context: Context, urlImage: String?) {
            image.setImageResource(R.drawable.ic_bag_empty)

            urlImage?.run {
                Picasso.with(context)
                    .load(urlImage)
                    .placeholder(R.drawable.ic_bag_empty)
                    .into(image)
            }
        }
    }
}