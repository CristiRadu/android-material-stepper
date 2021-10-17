package stepper

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.ViewAction
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.internal.widget.StepTab
import androidx.test.espresso.matcher.ViewMatchers
import androidx.viewpager2.widget.ViewPager2
import stepper.idling.CustomViewPagerListener
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import com.stepstone.stepper.R
import org.hamcrest.Matcher

/**
 * A set of actions which can potentially change the currently selected step in a [com.stepstone.stepper.StepperLayout].
 *
 * @author Piotr Zawadzki
 */
object StepperNavigationActions {
    /**
     * Clicks the Next button.
     */
    fun clickNext(): ViewAction {
        return object : AbstractStepperNavigationAction() {
            override fun getDescription(): String {
                return "Click on the Next button"
            }

            override fun performAction(stepperLayout: StepperLayout) {
                val nextButton = stepperLayout.findViewById<View>(R.id.ms_stepNextButton)
                nextButton.performClick()
            }
        }
    }

    /**
     * Clicks the Back button.
     */
    fun clickBack(): ViewAction {
        return object : AbstractStepperNavigationAction() {
            override fun getDescription(): String {
                return "Click on the Back button"
            }

            override fun performAction(stepperLayout: StepperLayout) {
                val backButton = stepperLayout.findViewById<View>(R.id.ms_stepPrevButton)
                backButton.performClick()
            }
        }
    }

    /**
     * Clicks the Complete button.
     */
    fun clickComplete(): ViewAction {
        return object : AbstractStepperNavigationAction() {
            override fun getDescription(): String {
                return "Click on the Complete button"
            }

            override fun performAction(stepperLayout: StepperLayout) {
                val completeButton = stepperLayout.findViewById<View>(R.id.ms_stepCompleteButton)
                completeButton.performClick()
            }
        }
    }

    /**
     * Clicks a tab at a specified position.
     */
    fun clickTabAtPosition(tabPosition: Int): ViewAction {
        return object : AbstractStepperNavigationAction() {
            override fun getDescription(): String {
                return "Click on tab at position: $tabPosition"
            }

            override fun performAction(stepperLayout: StepperLayout) {
                val tabsContainer =
                    stepperLayout.findViewById<View>(R.id.ms_stepTabsInnerContainer) as ViewGroup
                val childCount = tabsContainer.childCount
                require(childCount != 0) { "No tabs found!" }
                require(!(tabPosition < 0 || tabPosition >= childCount)) { "Invalid tab position: $tabPosition" }
                val stepTab = tabsContainer.getChildAt(tabPosition) as StepTab
                stepTab.performClick()
            }
        }
    }

    /**
     * Based on `ViewPagerActions.ViewPagerScrollAction`.
     */
    private abstract class AbstractStepperNavigationAction : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return ViewMatchers.isDisplayed()
        }

        override fun perform(uiController: UiController, view: View) {
            val stepperLayout = view as StepperLayout
            val viewPager: ViewPager2 = stepperLayout.findViewById(R.id.ms_stepPager)
            // Add a custom tracker listener
            val customListener = CustomViewPagerListener()
            viewPager.registerOnPageChangeCallback(customListener)

            // Note that we're running the following block in a try-finally construct. This
            // is needed since some of the actions are going to throw (expected) exceptions. If that
            // happens, we still need to clean up after ourselves to leave the system (Espresso) in a good
            // state.
            try {
                // Register our listener as idling resource so that Espresso waits until the
                // wrapped action results in the view pager getting to the STATE_IDLE state
                Espresso.registerIdlingResources(customListener)
                uiController.loopMainThreadUntilIdle()
                performAction(stepperLayout)
                uiController.loopMainThreadUntilIdle()
                customListener.mNeedsIdle = true
                uiController.loopMainThreadUntilIdle()
                customListener.mNeedsIdle = false
            } finally {
                // Unregister our idling resource
                Espresso.unregisterIdlingResources(customListener)
                // And remove our tracker listener from ViewPager
                viewPager.unregisterOnPageChangeCallback(customListener)
            }
        }

        protected abstract fun performAction(stepperLayout: StepperLayout)
    }
}