package com.example.androidplaylist

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.androidplaylist.playlist.resource
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AllOf.allOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule



@RunWith(AndroidJUnit4::class)
class PlayListFeature :BaseUITest(){

    //the rule tells the test to target the MainActivity class
    val  mActivityRule=ActivityTestRule(MainActivity::class.java)
    @Rule get// tells expresso to intiallize and kill the activity after test is finished

    @Test
    fun displayScreenTitle() {

        assertDisplayed("Music Playlist")

    }


    @Test
    fun displayListOfPlaylists() {

        //Thread.sleep(4000)


       assertRecyclerViewItemCount(R.id.playlist_recycler, 10)

        onView(allOf(withId(R.id.play_list_title), isDescendantOfA(nthChildOf(withId(R.id.playlist_recycler),0))))
            .check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))


        onView(allOf(withId(R.id.play_list_category), isDescendantOfA(nthChildOf(withId(R.id.playlist_recycler),0))))
            .check(matches(withText("rock")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.play_list_image), isDescendantOfA(nthChildOf(withId(R.id.playlist_recycler),1))))
            .check(matches(withDrawable(R.drawable.playlislogo)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displayLoaderWhileFetchingPlaylist(){

        //it ensures the assertion is not blocked while waiting for response from th e database
        IdlingRegistry.getInstance().unregister(resource)

        assertDisplayed(R.id.loader)
    }

    @Test
    fun hidesLoader(){
      //  Thread.sleep(4000)

        assertNotDisplayed(R.id.loader)
    }

    @Test
    fun displayRockimageForRocklistItems(){

     //   Thread.sleep(4000)

        onView(allOf(withId(R.id.play_list_image), isDescendantOfA(nthChildOf(withId(R.id.playlist_recycler),0))))
            .check(matches(withDrawable(R.drawable.rock)))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.play_list_image), isDescendantOfA(nthChildOf(withId(R.id.playlist_recycler),3))))
            .check(matches(withDrawable(R.drawable.rock)))
            .check(matches(isDisplayed()))

    }



    fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == view)
            }
        }
    }



}