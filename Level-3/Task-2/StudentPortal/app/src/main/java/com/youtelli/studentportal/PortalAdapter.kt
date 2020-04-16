package com.youtelli.studentportal

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_portal.view.*

class PortalAdapter(): RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    companion object {
        var portals = ArrayList<Portal>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_portal, parent, false)
        )
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(portal : Portal) {
            itemView.tvTitle.text = portal.title
            itemView.tvURL.text = portal.url

            itemView.setOnClickListener {view ->
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(itemView.tvURL.text.toString()))
                startActivity(view.context, browserIntent, null)
            }
        }
    }

    override fun getItemCount(): Int {
        return portals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(portals[position])
    }
}