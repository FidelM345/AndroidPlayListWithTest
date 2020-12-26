package com.example.androidplaylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidplaylist.playlist.PlaylistFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //To not add the fragment twice,
        if (savedInstanceState==null){
            //starts a new Fragment
            supportFragmentManager.beginTransaction()
                .add(R.id.container, PlaylistFragment.newInstance())
                .commit()
        }
    }
}