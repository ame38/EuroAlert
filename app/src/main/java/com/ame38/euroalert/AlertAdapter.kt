package com.ame38.euroalert

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class AlertAdapter(private val items: List<AlertListItem>) :
    RecyclerView.Adapter<AlertAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val severityDot: View = view.findViewById(R.id.severityDot)
        val title: TextView = view.findViewById(R.id.alertTitle)
        val subtitle: TextView = view.findViewById(R.id.alertSubtitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alert, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        holder.subtitle.text = item.subtitle

        val color = ContextCompat.getColor(holder.itemView.context, item.severityColorRes)
        val dot = holder.severityDot.background.mutate() as GradientDrawable
        dot.setColor(color)
    }

    override fun getItemCount(): Int = items.size
}
