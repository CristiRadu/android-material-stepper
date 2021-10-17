package com.stepstone.stepper.sample

import android.app.Activity
import androidx.annotation.NonNull
import java.lang.reflect.ParameterizedType
import java.util.*

import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule


/**
 * Base test class.
 *
 * @author Piotr Zawadzki
 */
abstract class AbstractActivityTest<STARTING_ACTIVITY : Activity?> {

    @get:Rule
    var intentsTestRule = ActivityScenarioRule(
        startingActivityClass
    )

    @NonNull
    protected fun getScreenshotTag(position: Int, @NonNull title: String): String {
        return String.format(Locale.ENGLISH, "%02d", position) + ". " + title
    }

    /**
     * Gets the class from the generic type.
     * [Source](http://stackoverflow.com/a/17767068/973379)
     *
     * @return the class from the generic type.
     */
    private val startingActivityClass: Class<STARTING_ACTIVITY>
        private get() = parameterizedType
            .actualTypeArguments[0] as Class<STARTING_ACTIVITY>
    private val parameterizedType: ParameterizedType
        private get() {
            var parameterizedType: ParameterizedType
            var clazz: Class<*>? = javaClass
            while (clazz != null) {
                try {
                    parameterizedType = getGenericSuperclass(clazz)
                    val actualTypeArguments = parameterizedType.actualTypeArguments
                    clazz = if (actualTypeArguments.size > 0
                        && Activity::class.java.isAssignableFrom(actualTypeArguments[0] as Class<*>)
                    ) {
                        return parameterizedType
                    } else {
                        clazz.superclass
                    }
                } catch (ex: ClassCastException) {
                    clazz = clazz.superclass
                }
            }
            throw IllegalStateException("Activity test must contain a type argument which is the tested Activity class")
        }

    private fun getGenericSuperclass(aClass: Class<*>): ParameterizedType {
        return aClass.genericSuperclass as ParameterizedType
    }
}