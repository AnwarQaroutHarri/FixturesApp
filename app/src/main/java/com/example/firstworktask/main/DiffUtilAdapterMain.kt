package com.example.firstworktask.main

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstworktask.R
import com.example.firstworktask.main.models.FixtureModelPackage.Response
import com.example.firstworktask.second.SecondActivity
import com.google.android.gms.fido.fido2.api.common.RequestOptions
import java.time.Duration


class DiffUtilAdapterMain : ListAdapter<Response, FixtureViewHolder> (UserItemDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureViewHolder {
        return FixtureViewHolder (
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row,parent,false))
    }

    override fun onBindViewHolder(holder: FixtureViewHolder, position: Int) {
        holder.bindTo(getItem(position))


    }

}

class FixtureViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    @SuppressLint("SetTextI18n")
    fun bindTo(data : Response){
        itemView.setLayoutParams(
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )


        /* Send intent to SecondActivity with the selected fixture ID */
        itemView.setOnClickListener {
            val intent = Intent(itemView.context,SecondActivity::class.java)
            intent.putExtra("id",data.fixture.id)
            itemView.context.startActivity(intent)

        }
        /* Bind the views to their data */
        val homeTeamTextView: TextView = itemView.findViewById(R.id.firstTeamTextView)
        val awayTeamTextView: TextView = itemView.findViewById(R.id.secondTeamTextView)
        val homeTeamScore: TextView = itemView.findViewById(R.id.homeTeamScore)
        val awayTeamScore: TextView = itemView.findViewById(R.id.awayTeamScore)
        val homeTeamImage: ImageView = itemView.findViewById(R.id.firstTeamImageView)
        val awayTeamImage: ImageView = itemView.findViewById(R.id.secondTeamImageView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTV)

        homeTeamTextView.text = data.teams.home.name.toString()
        awayTeamTextView.text = data.teams.away.name.toString()

        if(data.fixture.status.long.equals("Not Started")) {
            homeTeamScore.text = "TBD"
            awayTeamScore.text = "TBD"
        }


        else {
            homeTeamScore.text = data.score.fulltime.home.toString()
            awayTeamScore.text = data.score.fulltime.away.toString()
        }
        dateTextView.text = data.fixture.date.toString()
        Glide.with(itemView.context).load(data.teams.home.logo).into(homeTeamImage)
        Glide.with(itemView.context).load(data.teams.away.logo).into(awayTeamImage)


    }
}

class UserItemDiffCallback : DiffUtil.ItemCallback<Response>() {
    override fun areItemsTheSame(oldItem: Response, newItem: Response): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: Response, newItem: Response): Boolean = oldItem == newItem
}