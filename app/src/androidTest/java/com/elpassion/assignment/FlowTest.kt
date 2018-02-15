package com.elpassion.assignment

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.UiDevice
import android.support.v7.widget.RecyclerView
import com.elpassion.assignment.ui.splash.SplashActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.action.ViewActions.pressImeActionButton
import android.support.test.espresso.action.ViewActions.typeText
import android.widget.EditText
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.*


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class FlowTest {

    @Rule @JvmField
    val mActivityRule: ActivityTestRule<SplashActivity> = object : ActivityTestRule<SplashActivity>(SplashActivity::class.java) {

    }

    @Test
    fun ensureFunction() {

        // For press Back Button
        val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        pauseTestFor(2500)

        // Check if rv is displayed"
        onView(withId(R.id.rvItems))
                .check(matches(isDisplayed()))
        pauseTestFor(500)

        onView(withId(R.id.search_view)).perform(click());
        onView(isAssignableFrom(EditText::class.java)).perform(typeText("test"), pressImeActionButton())
        pauseTestFor(1500)

        // Scroll Tests of RecyclerView, so short list on that case
        onView(withId(R.id.rvItems))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        pauseTestFor(500)
        onView(withId(R.id.rvItems))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        pauseTestFor(500)
        onView(withId(R.id.rvItems))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
        pauseTestFor(500)

        onView(withId(R.id.rvItems))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        pauseTestFor(500)

        onView(withId(R.id.rvItems))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        mDevice.pressBack()

        onView(withId(R.id.rvItems))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

        mDevice.pressBack()

        onView(withId(R.id.rvItems))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

        mDevice.pressBack()



    }


    private fun pauseTestFor(milliseconds: Long) {
        try {
            Thread.sleep(milliseconds)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }
}
