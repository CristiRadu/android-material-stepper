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
import stepper.StepperNavigationActions.clickComplete
import stepper.StepperNavigationActions.clickNext
import stepper.StepperNavigationActions.clickTabAtPosition

/**
 * Performs tests on a tabbed stepper i.e. the one with `ms_stepperType="tabs"`.
 *
 * @author Piotr Zawadzki
 */
@LargeTest
class DefaultTabsActivityTest : AbstractActivityTest<DefaultTabsActivity?>() {
    @org.junit.Test
    fun shouldStayOnTheFirstStepWhenVerificationFails() {
        //when
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())

        //then
        checkCurrentStepIs(0)
        checkTabState(0, StepTabStateMatcher.TabState.ACTIVE)
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
        checkTabState(0, StepTabStateMatcher.TabState.DONE)
        checkTabState(1, StepTabStateMatcher.TabState.ACTIVE)
        checkTabState(2, StepTabStateMatcher.TabState.INACTIVE)
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
        checkTabState(0, StepTabStateMatcher.TabState.DONE)
        checkTabState(1, StepTabStateMatcher.TabState.DONE)
        checkTabState(2, StepTabStateMatcher.TabState.ACTIVE)
        SpoonScreenshotAction.perform(getScreenshotTag(3, "Last step test"))
    }

    @org.junit.Test
    fun shouldCompleteStepperFlow() {
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
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())
        Espresso.onView(
            Matchers.allOf<android.view.View>(
                ViewMatchers.withId(R.id.button),
                ViewMatchers.isCompletelyDisplayed()
            )
        ).perform(ViewActions.doubleClick())

        //when
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickComplete())

        //then
        checkCurrentStepIs(2)
        checkTabState(0, StepTabStateMatcher.TabState.DONE)
        checkTabState(1, StepTabStateMatcher.TabState.DONE)
        checkTabState(2, StepTabStateMatcher.TabState.ACTIVE)
        SpoonScreenshotAction.perform(getScreenshotTag(4, "Completion test"))
    }

    @org.junit.Test
    fun shouldGoToTheNextStepWhenVerificationSucceedsAndNextTabClicked() {
        //given
        Espresso.onView(
            Matchers.allOf<android.view.View>(
                ViewMatchers.withId(R.id.button),
                ViewMatchers.isCompletelyDisplayed()
            )
        ).perform(ViewActions.doubleClick())

        //when
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickTabAtPosition(1))

        //then
        checkCurrentStepIs(1)
        checkTabState(0, StepTabStateMatcher.TabState.DONE)
        checkTabState(1, StepTabStateMatcher.TabState.ACTIVE)
        checkTabState(2, StepTabStateMatcher.TabState.INACTIVE)
        SpoonScreenshotAction.perform(getScreenshotTag(5, "Go back via tab test"))
    }

    @org.junit.Test
    fun shouldGoToThePreviousStepWhenPreviousTabClicked() {
        //given
        Espresso.onView(
            Matchers.allOf<android.view.View>(
                ViewMatchers.withId(R.id.button),
                ViewMatchers.isCompletelyDisplayed()
            )
        ).perform(ViewActions.doubleClick())
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())

        //when
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickTabAtPosition(0))

        //then
        checkCurrentStepIs(0)
        checkTabState(0, StepTabStateMatcher.TabState.ACTIVE)
        checkTabState(1, StepTabStateMatcher.TabState.INACTIVE)
        checkTabState(2, StepTabStateMatcher.TabState.INACTIVE)
        SpoonScreenshotAction.perform(getScreenshotTag(6, "Verification success via tab test"))
    }
}