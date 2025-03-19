package com.example.musicrental


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest2 {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest2() {
        val materialButton = onView(
            allOf(
                withId(R.id.nextButton), withText("Next"),
                childAtPosition(
                    allOf(
                        withId(R.id.mainLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    10
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val materialButton2 = onView(
            allOf(
                withId(R.id.nextButton), withText("Next"),
                childAtPosition(
                    allOf(
                        withId(R.id.mainLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    10
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val materialButton3 = onView(
            allOf(
                withId(R.id.nextButton), withText("Next"),
                childAtPosition(
                    allOf(
                        withId(R.id.mainLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    10
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())

        val materialButton4 = onView(
            allOf(
                withId(R.id.borrowButton), withText("Borrow"),
                childAtPosition(
                    allOf(
                        withId(R.id.mainLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    8
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())

        val radioButton = onView(
            allOf(
                withText("Electric"),
                childAtPosition(
                    allOf(
                        withId(R.id.attributeRadioGroup),
                        childAtPosition(
                            withId(R.id.borrowLayout),
                            6
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        radioButton.perform(click())

        val radioButton2 = onView(
            allOf(
                withText("Acoustic"),
                childAtPosition(
                    allOf(
                        withId(R.id.attributeRadioGroup),
                        childAtPosition(
                            withId(R.id.borrowLayout),
                            6
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        radioButton2.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.renterName),
                childAtPosition(
                    allOf(
                        withId(R.id.borrowLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("na"), closeSoftKeyboard())

        val materialButton5 = onView(
            allOf(
                withId(R.id.saveButton), withText("Confirm Rental"),
                childAtPosition(
                    allOf(
                        withId(R.id.borrowLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        materialButton5.perform(click())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.renterName), withText("na"),
                childAtPosition(
                    allOf(
                        withId(R.id.borrowLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("nam"))

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.renterName), withText("nam"),
                childAtPosition(
                    allOf(
                        withId(R.id.borrowLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(closeSoftKeyboard())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
