package com.amanps.nestedhorizontalrecyclerview

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recyclerview_horizontal.view.*

class MainAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var sections = listOf<SectionModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val recycledViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HORIZONTAL_LIST -> {
                val horizontalView = LayoutInflater.from(parent.context)
                        .inflate(R.layout.recyclerview_horizontal, parent, false)
                HorizontalViewHolder(horizontalView)
            }
            else -> {
                throw Exception("Unexpected viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            HORIZONTAL_LIST -> {
                holder.itemView.textview_section_name.text = sections[position].name
                holder.itemView.recyclerview_horizontal.apply {
                    adapter = HorizontalRecyclerAdapter(sections[position])
                    layoutManager = LinearLayoutManager(this@MainAdapter.context, LinearLayoutManager.HORIZONTAL, false)
                    setRecycledViewPool(this@MainAdapter.recycledViewPool)
                }
            }
            else -> { throw IllegalArgumentException("viewType in HomeAdapter#onBindViewHolder is faulty.") }
        }
    }

    override fun getItemCount(): Int {
        return sections.size
    }

    override fun getItemViewType(position: Int): Int {
        return sections[position].type
    }

    class HorizontalViewHolder(view: View) : RecyclerView.ViewHolder(view)
}