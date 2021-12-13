package com.rickymorty.androiddemo.ui.fragment.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rickymorty.androiddemo.R
import com.rickymorty.androiddemo.api.Repository
import com.rickymorty.androiddemo.ui.fragment.SharedViewModel
import com.rickymorty.androiddemo.ui.fragment.SharedViewModelFactory
import com.rickymorty.androiddemo.model.Character
import com.rickymorty.androiddemo.model.EpisodeData

import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.launch

private const val DEFAULT_QUERY = ""

class ListFragment : Fragment(R.layout.fragment_list), CharacterAdpater.ListItemClickListener {

    private val viewModel: SharedViewModel by activityViewModels { SharedViewModelFactory(Repository()) }
    var adapter = CharacterAdpater(this,repository = Repository())


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNameSearchView()

        var layoutmanager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycclerview.layoutManager = layoutmanager
        recycclerview.adapter = adapter

        txt_reset.setOnClickListener {
            viewModel.searchCharacterNames("")
            searchView.clearFocus()
            searchView.setQuery("",false)
        }
        viewModel.characters.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
            adapter.notifyDataSetChanged()
        }

    }

    private fun getNameSearchView() {


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchCharacterNames(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.getSearchResults(DEFAULT_QUERY)
    }


    override fun onLocationClick(character: Character) {

        viewModel.getcharacterLocation(character.location.url)
        viewModel.locations.observe(viewLifecycleOwner, Observer {
            val directions = ListFragmentDirections.actionListFragmentToDetailFragment(it)
            findNavController().navigate(directions)
        })

    }

    override fun onLastSeenClick(character: Character) {
        viewModel.getcharacterepisode(character.episode[0])
        viewModel.episodes.observe(viewLifecycleOwner, Observer {
            val directions = ListFragmentDirections.actionListFragmentToEpisodeFragment(it)
            findNavController().navigate(directions)
        })

    }

}