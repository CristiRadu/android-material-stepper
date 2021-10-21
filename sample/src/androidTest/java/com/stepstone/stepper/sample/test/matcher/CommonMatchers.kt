package com.stepstone.stepper.sample.test.matcher

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.IntRange
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.stepstone.stepper.internal.widget.StepTabStateMatcher
import com.stepstone.stepper.sample.R
import org.hamcrest.Matcher
import stepper.StepperLayoutTabSubtitleMatcher.Companion.tabAtPositionHasSubtitle

/**
 * Contains commonly used matchers.
 *
 * @author Piotr Zawadzki
 */
object CommonMatchers {

    fun checkTabState(@IntRange(from = 0) position: Int, state: StepTabStateMatcher.TabState) {
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).check(
            ViewAssertions.matches(
                StepperLayoutTabStateMatcher.tabAtPositionIsInState(
                    position,
                    state
                )
            )
        )
    }

    fun checkTabSubtitle(@IntRange(from = 0) position: Int, subtitleMatcher: Matcher<View>) {
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout))
            .check(ViewAssertions.matches(tabAtPositionHasSubtitle(position, subtitleMatcher)))
    }

    fun checkCurrentStepIs(@IntRange(from = 0) expectedCurrentStep: Int) {
        Espresso.onView(ViewMatchers.withId(R.id.ms_stepPager)).check(
            ViewAssertions.matches(
                ViewPagerPositionMatcher.hasPagePosition(expectedCurrentStep)
            )
        )
    }

    fun checkCompleteButtonShown() {
        Espresso.onView(ViewMatchers.withId(R.id.ms_stepCompleteButton))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun checkBackButtonColor(@ColorRes expectedColor: Int) {
        Espresso.onView(ViewMatchers.withId(com.stepstone.stepper.R.id.ms_stepPrevButton))
            .check(ViewAssertions.matches(ViewMatchers.hasTextColor(expectedColor)))
    }

    fun checkNextButtonColor(@ColorRes expectedColor: Int) {
        Espresso.onView(ViewMatchers.withId(com.stepstone.stepper.R.id.ms_stepNextButton))
            .check(ViewAssertions.matches(ViewMatchers.hasTextColor(expectedColor)))
    }

    fun checkCompleteButtonColor(@ColorRes expectedColor: Int) {
        Espresso.onView(ViewMatchers.withId(com.stepstone.stepper.R.id.ms_stepCompleteButton))
            .check(ViewAssertions.matches(ViewMatchers.hasTextColor(expectedColor)))
    }
}