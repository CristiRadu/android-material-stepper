package com.stepstone.stepper.sample.test.matcher

import android.view.View
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.viewpager2.widget.ViewPager2
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/**
 * Checks the currently selected item position in the [ViewPager2].
 *
 * @author Piotr Zawadzki
 */
class ViewPagerPositionMatcher private constructor(private val positionMatcher: Matcher<Int>) :
    BoundedMatcher<View, ViewPager2>(
        ViewPager2::class.java
    ) {
    override fun describeTo(description: Description) {
        description.appendText("with position: ")
        positionMatcher.describeTo(description)
    }

    override fun matchesSafely(view: ViewPager2): Boolean {
        return positionMatcher.matches(view.currentItem)
    }

    companion object {
        /**
         * Returns a matcher that matches [ViewPager2] based on currently selected view position.
         */
        fun hasPagePosition(positionMatcher: Matcher<Int>): Matcher<View> {
            return ViewPagerPositionMatcher(Preconditions.checkNotNull(positionMatcher))
        }

        /**
         * Returns a matcher that matches [ViewPager2] based on currently selected view position.
         */
        fun hasPagePosition(position: Int): Matcher<View> {
            return ViewPagerPositionMatcher(Matchers.`is`(position))
        }
    }
}