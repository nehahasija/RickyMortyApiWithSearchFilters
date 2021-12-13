package com.rickymorty.androiddemo.ui.fragment.details



import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.rickymorty.androiddemo.R
import com.rickymorty.androiddemo.api.Repository
import com.rickymorty.androiddemo.ui.fragment.SharedViewModel
import com.rickymorty.androiddemo.ui.fragment.SharedViewModelFactory

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_episode.*

class EpisodeDetailFragment : Fragment(R.layout.fragment_episode) {
    private val viewModel: SharedViewModel by activityViewModels{ SharedViewModelFactory(Repository()) }

    private val args: EpisodeDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val episode = args.character


        title_episode_name.text= "Episode:"
        txt_episode_info.text=episode.episode
        title_episode_name.text="Name:"
        txt_episode_name.text=episode.name
        title_air_date.text="Aired on:"
        txt_air_date.text=episode.air_date
        title_created_at.text="Created At:"
        txt_created_on.text=episode.created


    }

}