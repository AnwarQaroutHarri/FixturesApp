package com.example.firstworktask.main

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstworktask.R
import com.example.firstworktask.databinding.RecyclerViewRowBinding
import com.example.firstworktask.main.models.models.Response
import com.example.firstworktask.second.FixtureDetailsActivity


class FixturesAdapter
    : ListAdapter<Response, FixtureViewHolder> (UserItemDiffCallback())
{

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): FixtureViewHolder {
        return FixtureViewHolder (
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row,parent,false))
    }

    override fun onBindViewHolder(
        holder: FixtureViewHolder, position: Int
    ) {
        holder.bindTo(getItem(position))
    }

}

class FixtureViewHolder(
    itemView : View
    ) : RecyclerView.ViewHolder(itemView){

    val binding = RecyclerViewRowBinding.bind(itemView)
    @SuppressLint("SetTextI18n")
    fun bindTo(data : Response){
        binding.data = data
        /* Send intent to SecondActivity with the selected fixture ID */
        itemView.setOnClickListener {
            val intent = Intent(itemView.context,FixtureDetailsActivity::class.java)
            intent.putExtra("id",data.fixture.id)
            itemView.context.startActivity(intent)
        }



     /*   if(data.fixture.status.long == "Not Started") {
            binding.homeTeamScore.text = "TBD"
            binding.awayTeamScore.text = "TBD"
        }


        else {
            binding.homeTeamScore.text = data.score.fulltime.home.toString()
            binding.awayTeamScore.text = data.score.fulltime.away.toString()

        }

      */
    }
}

class UserItemDiffCallback : DiffUtil.ItemCallback<Response>() {
    override fun areItemsTheSame(oldItem: Response, newItem: Response): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: Response, newItem: Response): Boolean = oldItem == newItem
}