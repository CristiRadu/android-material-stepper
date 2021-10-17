package com.stepstone.stepper.sample

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import com.stepstone.stepper.sample.test.action.SpoonScreenshotAction
import com.stepstone.stepper.sample.test.matcher.CommonMatchers
import org.hamcrest.Matchers
import org.junit.Test
import stepper.StepperNavigationActions.clickNext

/**
 * Performs tests on a themed dotted stepper i.e. the one with `ms_stepperType="dots"`.
 *
 * @author Piotr Zawadzki
 */
@LargeTest
class ThemedDotsActivityTest : AbstractActivityTest<ThemedDotsActivity?>() {
    @Test
    fun shouldStayOnTheFirstStepWhenVerificationFails() {
        clickNext()

        //when
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())

        //then
        CommonMatchers.checkCurrentStepIs(0)
        SpoonScreenshotAction.perform(getScreenshotTag(1, "Verification failure test"))
    }

    @Test
    fun shouldGoToTheNextStepWhenVerificationSucceeds() {
        //given
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.button),
                ViewMatchers.isCompletelyDisplayed()
            )
        ).perform(ViewActions.doubleClick())

        //when
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())

        //then
        CommonMatchers.checkCurrentStepIs(1)
        SpoonScreenshotAction.perform(getScreenshotTag(2, "Verification success test"))
    }

    @Test
    fun shouldShowCompleteButtonOnTheLastStep() {
        //given
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.button),
                ViewMatchers.isCompletelyDisplayed()
            )
        ).perform(ViewActions.doubleClick())
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.button),
                ViewMatchers.isCompletelyDisplayed()
            )
        ).perform(ViewActions.doubleClick())

        //when
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())

        //then
        CommonMatchers.checkCurrentStepIs(2)
        CommonMatchers.checkCompleteButtonShown()
        SpoonScreenshotAction.perform(getScreenshotTag(3, "Last step test"))
    }
}