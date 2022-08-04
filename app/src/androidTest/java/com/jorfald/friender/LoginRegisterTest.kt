package com.jorfald.friender

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.jorfald.friender.view.app.MainActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class LoginRegisterTest {


    @LargeTest
    @RunWith(AndroidJUnit4::class)
    class FriendListTest {

        @Rule
        @JvmField
        var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

        @Test
        fun loginFailRegisterLoginSuccess() {

        // Create random user, try to log in. Register it loguout and login again to see if user is created in firebase.

        var number = (1337..42000).shuffled().last()
        var randomUser: String = "${number}" + "@gmail.com"
            number++
            //Login page displayed
            onView(withId(R.id.login_fragment))
                .check(matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.login_email))
                .check(matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.login_password))
                .check(matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.login))
                .check(matches(ViewMatchers.isDisplayed()))
            // Log In
            onView(withId(R.id.login_email))
                .perform(ViewActions.replaceText("$randomUser"))
            onView(withId(R.id.login_password))
                .perform(ViewActions.replaceText("123123"))
            // Login with random user
            onView(withId(R.id.login)).perform(ViewActions.click())
            // It should fail, so we check if we still on login page
            // Alle kommentarene skulle vært på engelsk, føles mye riktigere
            // Gi det tid å logge inn
            Thread.sleep(5000)
            // Er vi fortsatt på login siden, sjekker om flere kjente fragmenter finnes
            onView(withId(R.id.login)).check(matches(isDisplayed()))
                    onView(withId(R.id.login_logo)).check(matches(isDisplayed()))
            // Okey, brukeren finnes ikke, vi får ikke logga inn, la oss registrere med denne brukeren også prøve igjen
            onView(withId(R.id.register_text)).check(matches(isDisplayed()))
            onView(withId(R.id.register_text)).perform(click())
            // Sjekke om ting ble endra til registreringsview varianten
            onView(withText("Create new account!")).check(matches(isDisplayed()))
            // Info ble lagra men vi skriver inn samme bruker på nytt og registrerer
            onView(withId(R.id.login_email)).perform(ViewActions.replaceText("$randomUser"))
            onView(withId(R.id.login_password)).perform(ViewActions.replaceText("123123"))
            onView(withId(R.id.login)).perform(ViewActions.click())
            // Det er samme navn på knappen, er bare view sin text som ble endret.
            // Nå bør vi bli innlogga, vente noen sek også sjekke om vi er innlogga
            Thread.sleep(3000)
            onView(withId(R.id.check_button)).check(matches(isDisplayed()))
            onView(withId(R.id.cross_button)).check(matches(isDisplayed()))
            onView(withId(R.id.friends_logout)).check(matches(isDisplayed()))
            // Logge ut, prøve å logge inn med nye brukeren
            onView(withId(R.id.friends_logout)).perform(click())
            // Vente litt
            Thread.sleep(5000)
            // Logge inn med samme bruker og se om vi er innlogga. Da vet vi om Firebase lagde bruker
            onView(withId(R.id.login_email))
                .perform(ViewActions.replaceText("$randomUser"))
            onView(withId(R.id.login_password))
                .perform(ViewActions.replaceText("123123"))
            // Login
            onView(withId(R.id.login)).perform(ViewActions.click())
            Thread.sleep(3000)
            // Er vi inne?
            onView(withId(R.id.check_button)).check(matches(isDisplayed()))
            onView(withId(R.id.cross_button)).check(matches(isDisplayed()))
            onView(withId(R.id.friends_logout)).check(matches(isDisplayed()))
            onView(withId(R.id.friends_logout)).perform(ViewActions.click())
            Thread.sleep(2000)
            // Logga ut, tilbake til login?
            onView(withId(R.id.login_fragment))
                .check(matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.login_email))
                .check(matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.login_password))
                .check(matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.login))
                .check(matches(ViewMatchers.isDisplayed()))
            // Nydelig, alt fungerer.
            // For sikkerhetskyld. Testen vil ikke alltid gi unik nummer
            

            
        }
    }}
