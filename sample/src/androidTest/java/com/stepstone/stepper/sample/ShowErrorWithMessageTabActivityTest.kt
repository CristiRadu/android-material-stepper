package com.stepstone.stepper.sample

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import com.stepstone.stepper.internal.widget.StepTabStateMatcher
import com.stepstone.stepper.sample.test.action.SpoonScreenshotAction
import com.stepstone.stepper.sample.test.matcher.CommonMatchers.checkCurrentStepIs
import com.stepstone.stepper.sample.test.matcher.CommonMatchers.checkTabState
import com.stepstone.stepper.sample.test.matcher.CommonMatchers.checkTabSubtitle
import org.hamcrest.Matchers
import org.junit.Test
import stepper.StepperNavigationActions.clickNext

/**
 * Performs tests on a tabbed stepper i.e. the one with `ms_stepperType="tabs"`.
 * This also tests if the errors are shown in the tabs with a message.
 *
 * @author Piotr Zawadzki
 */
@LargeTest
class ShowErrorWithMessageTabActivityTest :
    AbstractActivityTest<ShowErrorWithMessageTabActivity?>() {
    @Test
    fun shouldStayOnTheFirstStepWhenVerificationFailsAndShowErrorWithMessage() {
        //when
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())

        //then
        checkCurrentStepIs(0)
        checkTabState(0, StepTabStateMatcher.TabState.WARNING)
        checkTabState(1, StepTabStateMatcher.TabState.INACTIVE)
        checkTabState(2, StepTabStateMatcher.TabState.INACTIVE)
        checkTabSubtitle(0, ViewMatchers.withText(ERROR_MESSAGE))
        checkTabSubtitle(1, ViewMatchers.withText(STEP_SUBTITLE))
        SpoonScreenshotAction.perform(getScreenshotTag(1, "Verification failure test"))
    }

    @Test
    fun shouldGoToTheNextStepWhenVerificationSucceeds() {
        //given
        Espresso.onView(
            Matchers.allOf<View>(
                ViewMatchers.withId(R.id.button),
                ViewMatchers.isCompletelyDisplayed()
            )
        ).perform(ViewActions.doubleClick())

        //when
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())

        //then
        checkCurrentStepIs(1)
        checkTabSubtitle(0, ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE))
        checkTabSubtitle(1, ViewMatchers.withText(STEP_SUBTITLE))
        SpoonScreenshotAction.perform(getScreenshotTag(2, "Verification success test"))
    }

    @Test
    fun shouldClearErrorMessageWhenVerificationSucceeds() {
        //given
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())
        Espresso.onView(
            Matchers.allOf<View>(
                ViewMatchers.withId(R.id.button),
                ViewMatchers.isCompletelyDisplayed()
            )
        ).perform(ViewActions.doubleClick())

        //when
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())

        //then
        checkCurrentStepIs(1)
        checkTabSubtitle(0, ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE))
        checkTabSubtitle(1, ViewMatchers.withText(STEP_SUBTITLE))
        SpoonScreenshotAction.perform(getScreenshotTag(3, "Clear error message test"))
    }

    @Test
    fun shouldChangeSubtitleToErrorMessage() {
        //given
        Espresso.onView(
            Matchers.allOf<View>(
                ViewMatchers.withId(R.id.button),
                ViewMatchers.isCompletelyDisplayed()
            )
        ).perform(ViewActions.doubleClick())
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())

        //when
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())

        //then
        checkCurrentStepIs(1)
        checkTabSubtitle(0, ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE))
        checkTabSubtitle(1, ViewMatchers.withText(ERROR_MESSAGE))
        SpoonScreenshotAction.perform(getScreenshotTag(4, "Change subtitle to error message test"))
    }

    @Test
    fun shouldChangeBackToSubtitleFromErrorMessage() {
        //given
        Espresso.onView(
            Matchers.allOf<View>(
                ViewMatchers.withId(R.id.button),
                ViewMatchers.isCompletelyDisplayed()
            )
        ).perform(ViewActions.doubleClick())
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())
        Espresso.onView(
            Matchers.allOf<View>(
                ViewMatchers.withId(R.id.button),
                ViewMatchers.isCompletelyDisplayed()
            )
        ).perform(ViewActions.doubleClick())

        //when
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())

        //then
        checkCurrentStepIs(2)
        checkTabSubtitle(0, ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE))
        checkTabSubtitle(1, ViewMatchers.withText(STEP_SUBTITLE))
        SpoonScreenshotAction.perform(getScreenshotTag(5, "Change back to subtitle test"))
    }

    companion object {
        private const val ERROR_MESSAGE = "Click 2 more times!"
        private const val STEP_SUBTITLE = "Optional"
    }
}