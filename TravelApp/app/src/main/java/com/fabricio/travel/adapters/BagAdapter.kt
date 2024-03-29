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


class BagAdapter(val context: Context, listener: IOnItemsSeleccted<SuitCase>) : RecyclerView.Adapter<BagAdapter.BagHolder>() {
    private var listener: IOnItemsSeleccted<SuitCase>
    private val ctx: Context
    private val list: ArrayList<SuitCase>

    init {
        this.ctx = context
        this.list = ArrayList()
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagHolder {
        val artistView = LayoutInflater.from(context)
            .inflate(R.layout.item_selecctable, parent, false)
        return BagHolder(artistView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BagHolder, position: Int) {

        val item = list.get(position)
        holder.tvname.text = item.name
        holder.setImage(this.ctx, item.image)
        holder.check.setOnCheckedChangeListener(null)
        holder.check.isChecked = item.isChecked
        holder.check.setOnCheckedChangeListener { _, _ ->
            item.isCheckable = !item.isCheckable
            listener.itemSeleccted(list.filter { t-> t.isCheckable })
        }


    }

    fun onChangeCheckable(item: SuitCase, position: Int) {
        item.isCheckable = !item.isCheckable
        notifyItemChanged(position)
    }

    fun addAll(suitcase: List<SuitCase>?) {
        suitcase?.let { list.addAll(it) }
        this.notifyDataSetChanged()
    }


    class BagHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView
        val tvname: TextView
        val check: CheckBox

        init {
            image = view.findViewById(R.id.iv_suite)
            tvname = view.findViewById(R.id.tv_name)
            check = view.findViewById(R.id.cb_item)
        }

        fun setImage(context: Context, urlImage: String?) {
            urlImage?.run {
                Picasso.with(context)
                    .load(urlImage)
                    .placeholder(R.drawable.ic_bag_empty)
                    .into(image)
            }
        }
    }
}