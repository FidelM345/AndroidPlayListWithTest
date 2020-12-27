package com.example.androidplaylist.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.androidplaylist.R

/**
 * A fragment representing a list of Items.
 */
class PlaylistFragment : Fragment() {

    lateinit var viewModel: PlayListViewModel
    lateinit var viewModelFactory: PlayListViewModelFactory
    private val playlistAPI:PlaylistAPI=object :PlaylistAPI{}
    private val service:PlaylistService=PlaylistService(playlistAPI)
    private val repository: PlaylistRepository = PlaylistRepository(service)




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        setUpViewModel()

        viewModel.playlist.observe(this as LifecycleOwner,{playlistitems->

            if (playlistitems.getOrNull() !=null)
                setUpList(view, playlistitems.getOrNull()!!)
            else{
                //TODO
            }

        })

        return view
    }

    private fun setUpList(view: View?, playlistitems: List<PlaylistItem>) {
        //casting the view to a recycler view
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyPlaylistRecyclerViewAdapter(playlistitems)
        }
    }

    private fun setUpViewModel() {
        viewModelFactory = PlayListViewModelFactory(repository)//initializing viewmodel factory provider
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlayListViewModel::class.java)//initializing view model class
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlaylistFragment().apply {

            }
    }
}

/*

// Set the adapter
if (view is RecyclerView) {
    with(view) {
        layoutManager = when {
            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }
        adapter = MyPlaylistRecyclerViewAdapter(DummyContent.ITEMS)
    }
}*/
