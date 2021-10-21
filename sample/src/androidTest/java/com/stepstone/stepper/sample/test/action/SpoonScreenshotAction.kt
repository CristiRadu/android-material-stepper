package com.stepstone.stepper.sample.test.action

import android.R
import android.app.Activity
import android.content.ContextWrapper
import android.util.Log
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import com.stepstone.stepper.sample.test.spoon.Spoon
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import java.io.File

/**
 * Class to take screenshots using Spoon library from an Espresso test
 * Original code from Gist: https://gist.github.com/edenman/7fdd32a4d59ccc01185b
 */
class SpoonScreenshotAction
/**
 * Initialize with information required to take a screenshot
 *
 * @param tag       Name of the screenshot to include in the file name
 * @param testClass Name of the class taking the screenshot (required by Spoon library)
 */ private constructor(private val mTag: String, private val mTestClass: String) : ViewAction {
    override fun getConstraints(): Matcher<View> {
        return Matchers.any(View::class.java)
    }

    override fun getDescription(): String {
        return "Taking a screenshot using spoon."
    }

    override fun perform(uiController: UiController, view: View) {
        lastScreenshot = Spoon.screenshot(getActivity(view), mTag, mTestClass)
        Log.d(
            TAG,
            "Screenshot recorded at: " + if (lastScreenshot != null) lastScreenshot!!.path else "NOT RECORDED"
        )
    }

    companion object {
        private val TAG = SpoonScreenshotAction::class.java.simpleName

        /**
         * Get the last captured screenshot file
         *
         * @return Last screenshot file handler or null if there was no screenshot taken
         */
        var lastScreenshot: File? = null
            private set

        /**
         * Get the activity from the context of the view
         *
         * @param view View from which the activity will be inferred
         * @return Activity that contains the given view
         */
        private fun getActivity(view: View): Activity {
            var context = view.context
            while (context !is Activity) {
                context = if (context is ContextWrapper) {
                    context.baseContext
                } else {
                    throw IllegalStateException(
                        "Got a context of class " + context.javaClass + " and I don't know "
                                + "how to get the Activity from it"
                    )
                }
            }
            return context
        }

        /**
         * Espresso action to be take a screenshot of the current activity
         * This must be called directly from the test method
         *
         * @param tag Name of the screenshot to include in the file name
         */
        @JvmStatic
        fun perform(tag: String) {
            val trace = Thread.currentThread().stackTrace
            val testClass = trace[3].className
            Espresso.onView(ViewMatchers.withId(R.id.content))
                .perform(SpoonScreenshotAction(tag, testClass))
        }
    }
}