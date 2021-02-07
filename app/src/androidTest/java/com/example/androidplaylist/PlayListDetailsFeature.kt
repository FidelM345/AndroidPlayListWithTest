package com.example.androidplaylist

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AllOf
import org.junit.Rule
import org.junit.Test

class PlayListDetailsFeature:BaseUITest() {

        //the rule tells the test to target the MainActivity class
        val  mActivityRule= ActivityTestRule(MainActivity::class.java)
        @Rule get// tells expresso to intiallize and kill the activity after test is finished


            @Test
            fun displayPlaylistNameAndDetails(){


                Espresso.onView(CoreMatchers.allOf(ViewMatchers.withId(R.id.play_list_image), ViewMatchers.isDescendantOfA(nthChildOf(ViewMatchers.withId(R.id.playlist_recycler), 0))))
                        .perform(ViewActions.click())

                assertDisplayed(R.id.playlist_detials_root)

                assertDisplayed("Hard Rock Cafe")

                assertDisplayed("Rock your senses with this timeless signature vibe list. \n\n • Poison \n • You shook me all night \n • Zombie \n • Rock'n Me \n • Thunderstruck \n • I Hate Myself for Loving you \n • Crazy \n • Knockin' on Heavens Door")





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