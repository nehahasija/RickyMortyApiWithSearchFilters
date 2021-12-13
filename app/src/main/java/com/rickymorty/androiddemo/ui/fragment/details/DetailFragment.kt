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
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val viewModel: SharedViewModel by activityViewModels{ SharedViewModelFactory(Repository()) }

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locationData = args.character


        txt_loc_name.text= "Name : "+locationData.name
        txt_loc_type.text= "Type : "+locationData.type
        title_loc_dimension.text= "Dimension :"
        txt_loc_dimension.text= locationData.dimension
        txt_created_at.text="Created At:"
        txt_created.text = locationData.created


    }

}