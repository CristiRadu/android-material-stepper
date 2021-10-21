package com.stepstone.stepper.sample

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import com.stepstone.stepper.sample.test.action.SpoonScreenshotAction
import com.stepstone.stepper.sample.test.matcher.CommonMatchers.checkCompleteButtonShown
import com.stepstone.stepper.sample.test.matcher.CommonMatchers.checkCurrentStepIs
import org.hamcrest.Matchers
import stepper.StepperNavigationActions.clickNext

/**
 * Performs tests on a dotted stepper i.e. the one with `ms_stepperType="dots"`.
 *
 * @author Piotr Zawadzki
 */
@LargeTest
class DefaultDotsActivityTest : AbstractActivityTest<DefaultDotsActivity?>() {
    @org.junit.Test
    fun shouldStayOnTheFirstStepWhenVerificationFails() {
        //when
        onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())

        //then
        checkCurrentStepIs(0)
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
}