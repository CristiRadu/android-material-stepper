package stepper

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import com.stepstone.stepper.R
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.internal.widget.StepTab
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Checks the subtitle of a [StepTab] at a specified position.
 *
 * @author Piotr Zawadzki
 */
class StepperLayoutTabSubtitleMatcher private constructor(
    private val tabPosition: Int, private val subtitleMatcher: Matcher<View>
) : BoundedMatcher<View, StepperLayout>(
    StepperLayout::class.java
) {
    override fun describeTo(description: Description) {
        description
            .appendText(" at position: ")
            .appendValue(tabPosition)
            .appendText(" with message: ")
            .appendValue(subtitleMatcher)
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
        val subtitleTextView = stepTab.findViewById<View>(R.id.ms_stepSubtitle) as TextView
        return subtitleMatcher.matches(subtitleTextView)
    }

    companion object {
        private const val TAG = "StepperLayoutTabSubtMa"
        fun tabAtPositionHasSubtitle(
            @androidx.annotation.IntRange(from = 0) tabPosition: Int,
            subtitleMatcher: Matcher<View>
        ): Matcher<View> {
            return StepperLayoutTabSubtitleMatcher(tabPosition, subtitleMatcher)
        }
    }
}