package com.jorfald.friender

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jorfald.friender.view.app.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class FriendsFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun FriendSearchViewFullFlow() {
        onView(withId(R.id.login_fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.login_email)).check(matches(isDisplayed()))
        onView(withId(R.id.login_password)).check(matches(isDisplayed()))
        onView(withId(R.id.login)).check(matches(isDisplayed()))
        // Log In
        onView(withId(R.id.login_email)).perform(ViewActions.replaceText("ekteg@icloud.com"))
        onView(withId(R.id.login_password)).perform(ViewActions.replaceText("123123"))
        onView(withId(R.id.login)).perform(click())
        //Sjekk initiell tilstand
        Thread.sleep(5000)
        // Er navnet synlig?
        onView(withId(R.id.name_text)).check(matches(isDisplayed()))
        // Er place synlig?
        onView(withId(R.id.place_text)).check(matches(isDisplayed()))
        // Er alder synlig?
        Thread.sleep(100)
        onView(withId(R.id.age_text)).check(matches(isCompletelyDisplayed()))
        // Er kjønn synlig?
        onView(withId(R.id.gender_icon)).check(matches(isDisplayed()))
        // Er arbeidsforhold synlig?
        onView(withId(R.id.employment_text)).check(matches(isDisplayed()))
        // Er profilbilde synlig?
        onView(withId(R.id.profile_image)).check(matches(isDisplayed()))
        // Er ja knappen synlig?
        onView(withId(R.id.check_button)).check(matches(isDisplayed()))
        // Er nei synlig?
        onView(withId(R.id.cross_button)).check(matches(isDisplayed()))
        // Er mine venner knappen synlig?
        // Stemmer knappens text_
        onView(withId(R.id.my_friends_button)).check(matches(isDisplayed()))
        onView(withId(R.id.my_friends_button)).check(matches(withText("My Friends")))
        //Fungerer knappene?
        //KLIKKE PÅ MINE VENNER
        onView(withId(R.id.my_friends_button)).perform(click())
        // VISES LISTEN?
        onView(withId(R.id.fragment_friends_list)).check(matches(isDisplayed()))
        // VISES TILBAKE KNAPPEN?
        onView(withId(R.id.friends_back_button)).check(matches(isDisplayed()))


        // KLIKKE PÅ TILBAKE KNAPPEN
        onView(withId(R.id.friends_back_button)).perform(click())
        // ER TILBAKE KNAPPEN BORTE?
        Thread.sleep(3000)
        // VISES FRIENDCARD? SOM IGJEN VIL SI VI ER TILBAKE TIL VENN-LETINGEN IGJEN
        onView(withId(R.id.person_card)).check(matches(isDisplayed()))
        // Trykke på adde venn knappen:
        onView(withId(R.id.check_button)).perform(click())
        // Vises konfetti
        onView(withId(R.id.viewCelebrate)).check(matches(isDisplayed()))
    }
}


