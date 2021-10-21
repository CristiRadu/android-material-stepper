package com.stepstone.stepper.sample

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import com.stepstone.stepper.sample.test.action.SpoonScreenshotAction
import com.stepstone.stepper.sample.test.matcher.CommonMatchers.checkBackButtonColor
import com.stepstone.stepper.sample.test.matcher.CommonMatchers.checkCompleteButtonColor
import com.stepstone.stepper.sample.test.matcher.CommonMatchers.checkCompleteButtonShown
import com.stepstone.stepper.sample.test.matcher.CommonMatchers.checkCurrentStepIs
import com.stepstone.stepper.sample.test.matcher.CommonMatchers.checkNextButtonColor
import org.hamcrest.Matchers
import stepper.StepperNavigationActions.clickComplete
import stepper.StepperNavigationActions.clickNext

/**
 * Performs tests on a stepper on which we change the button colors in code.
 *
 * @author Piotr Zawadzki
 */
@LargeTest
class SetButtonColorProgrammaticallyActivityTest :
    AbstractActivityTest<SetButtonColorProgrammaticallyActivity?>() {
    @org.junit.Test
    fun shouldStayOnTheFirstStepWhenVerificationFails() {
        //when
        Espresso.onView(ViewMatchers.withId(R.id.stepperLayout)).perform(clickNext())

        //then
        SpoonScreenshotAction.perform(getScreenshotTag(1, "Verification failure test"))
        checkCurrentStepIs(0)
        checkNextButtonColor(R.color.verification_failed_color)
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
        SpoonScreenshotAction.perform(getScreenshotTag(2, "Verification success test"))
        checkCurrentStepIs(1)
        checkNextButtonColor(R.color.ms_black)
        checkBackButtonColor(R.color.ms_white)
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
        SpoonScreenshotAction.perform(getScreenshotTag(3, "Last step test"))
        checkCurrentStepIs(2)
        checkCompleteButtonShown()
        checkCompleteButtonColor(R.color.verification_failed_color)
        checkBackButtonColor(R.color.ms_black)
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
        SpoonScreenshotAction.perform(getScreenshotTag(4, "Completion test"))
        checkCurrentStepIs(2)
        checkCompleteButtonColor(R.color.ms_black)
        checkBackButtonColor(R.color.ms_black)
    }
}