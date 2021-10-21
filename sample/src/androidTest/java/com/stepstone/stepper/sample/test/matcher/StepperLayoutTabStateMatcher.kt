package com.stepstone.stepper.sample.test.matcher

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions
import androidx.test.espresso.matcher.BoundedMatcher
import com.stepstone.stepper.R
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.internal.widget.StepTab
import com.stepstone.stepper.internal.widget.StepTabStateMatcher
import com.stepstone.stepper.internal.widget.StepTabStateMatcher.TabState
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Checks the state of a [StepTab] at a specified position.
 *
 * @author Piotr Zawadzki
 */
class StepperLayoutTabStateMatcher private constructor(
    private val tabPosition: Int, private val state: TabState
) : BoundedMatcher<View, StepperLayout>(
    StepperLayout::class.java
) {
    override fun describeTo(description: Description) {
        description
            .appendText(" at position: ")
            .appendValue(tabPosition)
            .appendText(" in state: ")
            .appendValue(state.name)
    }

    override fun matchesSafely(view: StepperLayout): Boolean {
        val tabsContainer = view.findViewById<View>(R.id.ms_stepTabsInnerContainer) as ViewGroup
        val childCount = tabsContainer.childCount
        if (childCount == 0) {
            Log.e(TAG, "No tabs found!")
            return false
        }
        if (tabPosition < 0 || tabPosition >= childCount) {
            Log.e(TAG, "Invalid tab position: $tabPosition")
            return false
        }
        val stepTab = tabsContainer.getChildAt(tabPosition) as StepTab
        return StepTabStateMatcher(state).matches(stepTab)
    }

    companion object {
        private const val TAG = "StepperLayoutTabStateMa"
        fun tabAtPositionIsInState(tabPosition: Int, state: TabState): Matcher<View> {
            return StepperLayoutTabStateMatcher(tabPosition, Preconditions.checkNotNull(state))
        }
    }
}