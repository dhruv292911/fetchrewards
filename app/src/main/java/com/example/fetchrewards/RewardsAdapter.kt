package com.example.fetchrewards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RewardsAdapter(private val rewardsList: List<Rewards>) :
    RecyclerView.Adapter<RewardsAdapter.RewardsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return RewardsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RewardsViewHolder, position: Int) {
        val rewards = rewardsList[position]
        holder.idTextView.text = rewards.id.toString()
        holder.listIdTextView.text = rewards.listId?.toString() ?: "N/A"
        holder.nameTextView.text = rewards.name
    }

    override fun getItemCount(): Int = rewardsList.size

    class RewardsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView: TextView = itemView.findViewById(R.id.textViewId)
        val listIdTextView: TextView = itemView.findViewById(R.id.textViewListId)
        val nameTextView: TextView = itemView.findViewById(R.id.textViewName)
    }
}