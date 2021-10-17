package com.stepstone.stepper.sample

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import com.stepstone.stepper.internal.widget.StepTabStateMatcher
import com.stepstone.stepper.sample.test.action.SpoonScreenshotAction
import com.stepstone.stepper.sample.test.matcher.CommonMatchers.checkCompleteButtonShown
import com.stepstone.stepper.sample.test.matcher.CommonMatchers.checkCurrentStepIs
import com.stepstone.stepper.sample.test.matcher.CommonMatchers.checkTabState
import org.hamcrest.Matchers
import stepper.StepperNavigationActions.clickBack
import stepper.StepperNavigationActions.clickNext

/**
 * Performs tests on a tabbed stepper i.e. the one with `ms_stepperType="tabs"`.
 * This also tests if the errors are shown in the tabs and stay there when going back.
 *
 * @author Piotr Zawadzki
 */
@LargeTest
class ShowErrorOnBackTabActivityTest : AbstractActivityTest<ShowErrorOnBackTabActivity?>() {
    @org.junit.Test
    fun shouldStayOnTheFirstStepWhenVerificationFailsAndShowError() {
        //when
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())

        //then
        checkCurrentStepIs(0)
        checkTabState(0, StepTabStateMatcher.TabState.WARNING)
        checkTabState(1, StepTabStateMatcher.TabState.INACTIVE)
        checkTabState(2, StepTabStateMatcher.TabState.INACTIVE)
        SpoonScreenshotAction.perform(getScreenshotTag(1, "Verification failure test"))
    }

    @org.junit.Test
    fun shouldGoToTheNextStepWhenVerificationSucceeds() {
        //given
        Espresso.onView(
            Matchers.allOf<android.view.View>(
                ViewMatchers.withId(R.id.button),
                ViewMatchers.isCompletelyDisplayed()
            )
        ).perform(ViewActions.doubleClick())

        //when
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())

        //then
        checkCurrentStepIs(1)
        SpoonScreenshotAction.perform(getScreenshotTag(2, "Verification success test"))
    }

    @org.junit.Test
    fun shouldShowCompleteButtonOnTheLastStep() {
        //given
        Espresso.onView(
            Matchers.allOf<android.view.View>(
                ViewMatchers.withId(R.id.button),
                ViewMatchers.isCompletelyDisplayed()
            )
        ).perform(ViewActions.doubleClick())
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())
        Espresso.onView(
            Matchers.allOf<android.view.View>(
                ViewMatchers.withId(R.id.button),
                ViewMatchers.isCompletelyDisplayed()
            )
        ).perform(ViewActions.doubleClick())

        //when
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())

        //then
        checkCurrentStepIs(2)
        checkCompleteButtonShown()
        SpoonScreenshotAction.perform(getScreenshotTag(3, "Last step test"))
    }

    @org.junit.Test
    fun shouldGoToTheNextStepAndClearWarningWhenStepVerificationSucceeds() {
        //given
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())
        Espresso.onView(
            Matchers.allOf<android.view.View>(
                ViewMatchers.withId(R.id.button),
                ViewMatchers.isCompletelyDisplayed()
            )
        ).perform(ViewActions.doubleClick())

        //when
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())

        //then
        checkCurrentStepIs(1)
        checkTabState(0, StepTabStateMatcher.TabState.DONE)
        checkTabState(1, StepTabStateMatcher.TabState.ACTIVE)
        checkTabState(2, StepTabStateMatcher.TabState.INACTIVE)
        SpoonScreenshotAction.perform(getScreenshotTag(4, "Clear warning on success test"))
    }

    @org.junit.Test
    fun shouldNotClearWarningWhenGoingBackToPreviousStep() {
        //given
        Espresso.onView(
            Matchers.allOf<android.view.View>(
                ViewMatchers.withId(R.id.button),
                ViewMatchers.isCompletelyDisplayed()
            )
        ).perform(ViewActions.doubleClick())
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())

        //when
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickBack())

        //then
        checkCurrentStepIs(0)
        checkTabState(0, StepTabStateMatcher.TabState.ACTIVE)
        checkTabState(1, StepTabStateMatcher.TabState.WARNING)
        checkTabState(2, StepTabStateMatcher.TabState.INACTIVE)
        SpoonScreenshotAction.perform(getScreenshotTag(5, "Not cleared warning on Back test"))
    }
}