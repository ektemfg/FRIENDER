package com.jorfald.friender


import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.jorfald.friender.view.app.MainActivity
import org.hamcrest.*
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class FriendListTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun friendListTest() {
        // Find login page
        onView(withId(R.id.login_fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.login_email)).check(matches(isDisplayed()))
        onView(withId(R.id.login_password)).check(matches(isDisplayed()))
        onView(withId(R.id.login)).check(matches(isDisplayed()))
        // Log In
        onView(withId(R.id.login_email)).perform(replaceText("ekteg@icloud.com"))
        onView(withId(R.id.login_password)).perform(replaceText("123123"))
        onView(withId(R.id.login)).perform(click())
        Thread.sleep(5000)
        onView(withId(R.id.check_button)).check(matches(isDisplayed()))
        onView(withId(R.id.cross_button)).check(matches(isDisplayed()))
        // Adde en ny person til lista
        onView(withId(R.id.check_button)).perform(click())
        // Trykke på mine venner knapp
        onView(withId(R.id.my_friends_button)).perform(click())
        // Sjekke om vennelista er synlig.
        onView(withId(R.id.friends_recycler)).check(matches(isDisplayed()))
        // Sjekke om det ligger en person i lista og trykker på den

        onView(withPositionInParent(R.id.friends_recycler,0)).perform(click())
        // Fragment skal bytte til friend info fragment ved klikk, har det skjedd?
        onView(withId(R.id.friend_info_layout)).check(matches(isDisplayed()))
        // Er vennen sin info views visible?
        // Bilde?
        onView(withId(R.id.friend_info_avatar)).check(matches(isDisplayed()))
        // Navn?
        onView(withId(R.id.friend_info_name)).check(matches(isDisplayed()))
        // Alder?
        onView(withId(R.id.friend_info_age)).check(matches(isDisplayed()))
        // jOBB?
        onView(withId(R.id.friend_info_job)).check(matches(isDisplayed()))
        // Kjønn?
        onView(withId(R.id.friend_info_gender)).check(matches(isDisplayed()))
        // Epost?
        onView(withId(R.id.friend_info_email)).check(matches(isDisplayed()))
        // Sjekke om finnes og trykke på back to friends list. Finnes remove venn knappen
        onView(withId(R.id.friend_info_remove)).check(matches(isDisplayed()))
        onView(withId(R.id.friend_info_backbutton)).check(matches(isDisplayed()))
        // Fungerer back knappen?
        onView(withId(R.id.friend_info_backbutton)).perform(click())
        // Trykker på remove friend, blir vi sendt tilbake til lista av venner?
        onView(withId(R.id.fragment_friends_list)).check(matches(isDisplayed()))
        // Tilbsake TIL VENN INFO
        onView(withPositionInParent(R.id.fragment_friends_list,0)).perform(click())
        Thread.sleep(5000)
        onView(withId(R.id.friend_info_layout)).check(matches(isDisplayed()))
        // Remove knappen? Trykke på den
        onView(withId(R.id.friend_info_remove)).perform(click())
        // Trykker på remove friend, blir vi sendt tilbake til lista av venner?
        onView(withId(R.id.fragment_friends_list)).check(matches(isDisplayed()))
        // Teste back knapp på vennelista
        onView(withId(R.id.friends_back_button)).check(matches(isDisplayed()))
        onView(withId(R.id.friends_back_button)).perform(click())
        // Tilbake på friend search?
        onView(withId(R.id.my_friends_button)).check(matches(isDisplayed()))



    }

    fun withPositionInParent(parentViewId: Int, position: Int): Matcher<View> {
        return allOf(withParent(withId(parentViewId)), withParentIndex(position))
    }

            }
