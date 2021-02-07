package com.example.androidplaylist.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.androidplaylist.details.PlaylistDetailFragmentArgs
import com.example.androidplaylist.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_playlist_detail.*
import javax.inject.Inject



@AndroidEntryPoint
class PlaylistDetailFragment : Fragment() {

    //uses the safeargs gradle plugin found in the build level gradle file.
    //used to retrieve the playlist id from the PlaylistFragment
    val args: PlaylistDetailFragmentArgs by navArgs()


    lateinit var viewModel: PlayListDetailsViewModel

    @Inject
    lateinit var viewModelFactory: PlayListDetailsViewModelFactory



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View= inflater.inflate(R.layout.fragment_playlist_detail, container, false)
        //used to retrieve playlist id from the argument
        val playlist_Id:String=args.playlistId


        setUpViewModel()

        viewModel.getPlayListDetails(playlist_Id)

        observeLiveData()


        return view
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlayListDetailsViewModel::class.java)
    }

    private fun observeLiveData() {
        viewModel.playlistDetails.observe(this as LifecycleOwner) { playlistDetailsModel ->

            if (playlistDetailsModel.getOrNull() != null) {

                playlist_name.text = playlistDetailsModel.getOrNull()!!.name
                Log.i("mato1", "observeLiveData: ${playlistDetailsModel.getOrNull()!!.details}")
                playlist_details.text = playlistDetailsModel.getOrNull()!!.details

            } else {
                //TODO
            }

        }
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            PlaylistDetailFragment()
    }

}