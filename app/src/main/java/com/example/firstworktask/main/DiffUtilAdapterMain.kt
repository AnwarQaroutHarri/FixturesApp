package com.example.firstworktask.main

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstworktask.R
import com.example.firstworktask.databinding.RecyclerViewRowBinding
import com.example.firstworktask.main.models.FixtureModelPackage.Response
import com.example.firstworktask.second.FixtureDetailsActivity


class DiffUtilAdapterMain
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
        itemView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )


        /* Send intent to SecondActivity with the selected fixture ID */
        itemView.setOnClickListener {
            val intent = Intent(itemView.context,FixtureDetailsActivity::class.java)
            intent.putExtra("id",data.fixture.id)
            itemView.context.startActivity(intent)
        }



        binding.firstTeamTextView.text = data.teams.home.name.toString()
        binding.secondTeamTextView.text = data.teams.away.name.toString()


        if(data.fixture.status.long.equals("Not Started")) {
            binding.homeTeamScore.text = "TBD"
            binding.awayTeamScore.text = "TBD"
        }


        else {
            binding.homeTeamScore.text = data.score.fulltime.home.toString()
            binding.awayTeamScore.text = data.score.fulltime.away.toString()

        }
        binding.dateTV.text = data.fixture.date.toString()
        Glide.with(itemView.context).load(data.teams.home.logo).into(binding.firstTeamImageView)
        Glide.with(itemView.context).load(data.teams.away.logo).into(binding.secondTeamImageView)
    }
}

class UserItemDiffCallback : DiffUtil.ItemCallback<Response>() {
    override fun areItemsTheSame(oldItem: Response, newItem: Response): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: Response, newItem: Response): Boolean = oldItem == newItem
}