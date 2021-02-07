package com.example.androidplaylist

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidplaylist.playlist.resource
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith


//the class can be open or abstract
@RunWith(AndroidJUnit4::class)
abstract class BaseUITest {



    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(resource)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(resource)
    }



}