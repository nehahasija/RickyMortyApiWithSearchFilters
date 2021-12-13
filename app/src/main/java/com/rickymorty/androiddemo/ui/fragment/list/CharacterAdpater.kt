package com.rickymorty.androiddemo.ui.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rickymorty.androiddemo.R
import com.rickymorty.androiddemo.api.Repository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*
import com.rickymorty.androiddemo.model.Character
import com.rickymorty.androiddemo.model.EpisodeData
import com.rickymorty.androiddemo.ui.fragment.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import kotlin.coroutines.coroutineContext

class CharacterAdpater(clickListener: ListItemClickListener, repository: Repository) :
    PagingDataAdapter<Character, CharacterAdpater.CharacterViewHolder>(
        CharacterComparator
    ) {

    private var clickListener: ListItemClickListener
    private var repository: Repository
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return CharacterViewHolder(view)
    }

    init {
        this.clickListener = clickListener
        this.repository = repository

    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }

    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }


    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image_character = itemView.character_img
        var status_type = itemView.txt_status
        var id_number = itemView.txt_id_character
        var name_character = itemView.txt_name_character
        var specie_character = itemView.txt_specie
        var title_loc = itemView.title_last_known_loc
        var last_loc_character = itemView.txt_last_known_loc
        var title_first_seen_in = itemView.title_first_seen_in
        var first_seen_in = itemView.txt_first_seen_in

        fun bind(character: Character) {
            Picasso.get().load(character.image).into(image_character)
            status_type.text = "(" + character.status + ")"
            id_number.text = character.id.toString()
            name_character.text = character.name
            specie_character.text = character.species
            last_loc_character.text = character.location.name
            title_loc.text = "Last Known Loc :"
            last_loc_character.setOnClickListener(View.OnClickListener {
                clickListener.onLocationClick(
                    character
                )
            })
            title_first_seen_in.text = "First Seen In:"
            first_seen_in.text = character.episode[0]
            first_seen_in.setOnClickListener(View.OnClickListener {
                clickListener.onLastSeenClick(
                    character
                )
            })
            CoroutineScope(Dispatchers.Main).launch {
                var result: Int = character.episode[0].filter { it.isDigit() }.toInt()
                var episodeData = repository.getCharacterEpisode(result)
                first_seen_in.text = episodeData.name

            }
        }
    }

    object CharacterComparator : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Character, newItem: Character) =
            oldItem == newItem
    }

    interface ListItemClickListener {
        fun onLocationClick(character: Character)
        fun onLastSeenClick(character: Character)
    }

}