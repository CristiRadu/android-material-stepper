//package com.stepstone.stepper.sample
//
//import android.support.annotation.StringRes
//import androidx.test.filters.LargeTest
//import com.stepstone.stepper.sample.test.action.SpoonScreenshotAction
//import org.junit.Test
//import java.util.*
//
///**
// * Performs sanity tests i.e. open each sample app Activity and makes a screenshot of the screen.
// *
// * @author Piotr Zawadzki
// */
//@LargeTest
//class SanityTest : AbstractActivityTest<MainActivity?>() {
//    @Test
//    fun shouldOpenDefaultDotsActivity() {
//        //when
//        clickRowWithText(R.string.default_dots)
//
//        //then
//        intended(hasComponent(DefaultDotsActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(1, R.string.default_dots))
//    }
//
//    @Test
//    fun shouldOpenStyledDotsActivity() {
//        //when
//        clickRowWithText(R.string.styled_dots)
//
//        //then
//        intended(hasComponent(StyledDotsActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(2, R.string.styled_dots))
//    }
//
//    @Test
//    fun shouldOpenThemedDotsActivity() {
//        //when
//        clickRowWithText(R.string.themed_dots)
//
//        //then
//        intended(hasComponent(ThemedDotsActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(3, R.string.themed_dots))
//    }
//
//    @Test
//    fun shouldOpenDefaultProgressBarActivity() {
//        //when
//        clickRowWithText(R.string.default_progress_bar)
//
//        //then
//        intended(hasComponent(DefaultProgressBarActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(4, R.string.default_progress_bar))
//    }
//
//    @Test
//    fun shouldOpenStyledProgressBarActivity() {
//        //when
//        clickRowWithText(R.string.styled_progress_bar)
//
//        //then
//        intended(hasComponent(StyledProgressBarActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(5, R.string.styled_progress_bar))
//    }
//
//    @Test
//    fun shouldOpenDefaultTabsActivity() {
//        //when
//        clickRowWithText(R.string.default_tabs)
//
//        //then
//        intended(hasComponent(DefaultTabsActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(6, R.string.default_tabs))
//    }
//
//    @Test
//    fun shouldOpenStyledTabsActivity() {
//        //when
//        clickRowWithText(R.string.styled_tabs)
//
//        //then
//        intended(hasComponent(StyledTabsActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(7, R.string.styled_tabs))
//    }
//
//    @Test
//    fun shouldOpenDefaultNoneActivity() {
//        //when
//        clickRowWithText(R.string.default_none)
//
//        //then
//        intended(hasComponent(DefaultNoneActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(8, R.string.default_none))
//    }
//
//    @Test
//    fun shouldOpenErrorOnTabsActivity() {
//        //when
//        clickRowWithText(R.string.error_tabs)
//
//        //then
//        intended(hasComponent(ShowErrorTabActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(9, R.string.error_tabs))
//    }
//
//    @Test
//    fun shouldOpenColoredErrorOnTabsActivity() {
//        //when
//        clickRowWithText(R.string.error_color_tabs)
//
//        //then
//        intended(hasComponent(ShowErrorCustomColorTabActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(10, R.string.error_color_tabs))
//    }
//
//    @Test
//    fun shouldOpenErrorOnTabsOnBackActivity() {
//        //when
//        clickRowWithText(R.string.error_back_tabs)
//
//        //then
//        intended(hasComponent(ShowErrorOnBackTabActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(11, R.string.error_back_tabs))
//    }
//
//    @Test
//    fun shouldOpenErrorOnTabsWithMessageActivity() {
//        //when
//        clickRowWithText(R.string.error_with_message_tabs)
//
//        //then
//        intended(hasComponent(ShowErrorWithMessageTabActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(12, R.string.error_with_message_tabs))
//    }
//
//    @Test
//    fun shouldOpenCombinationActivity() {
//        //when
//        clickRowWithText(R.string.combination)
//
//        //then
//        intended(hasComponent(CombinationActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(13, R.string.combination))
//    }
//
//    @Test
//    fun shouldOpenCustomPageTransformerActivity() {
//        //when
//        clickRowWithText(R.string.custom_page_transformer)
//
//        //then
//        intended(hasComponent(CustomPageTransformerActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(14, R.string.custom_page_transformer))
//    }
//
//    @Test
//    fun shouldOpenDelayedTransitionActivity() {
//        //when
//        clickRowWithText(R.string.delayed_transition)
//
//        //then
//        intended(hasComponent(DelayedTransitionStepperActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(15, R.string.delayed_transition))
//    }
//
//    @Test
//    fun shouldOpenStepperFeedbackActivity() {
//        //when
//        clickRowWithText(R.string.stepper_feedback)
//
//        //then
//        intended(hasComponent(StepperFeedbackActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(16, R.string.stepper_feedback))
//    }
//
//    @Test
//    fun shouldOpenCustomNavigationButtonsActivity() {
//        //when
//        clickRowWithText(R.string.custom_navigation_buttons)
//
//        //then
//        intended(hasComponent(CustomNavigationButtonsActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(17, R.string.custom_navigation_buttons))
//    }
//
//    @Test
//    fun shouldOpenReturnButtonActivity() {
//        //when
//        clickRowWithText(R.string.show_back_button)
//
//        //then
//        intended(hasComponent(ReturnButtonActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(18, R.string.show_back_button))
//    }
//
//    @Test
//    fun shouldOpenNoFragmentsActivity() {
//        //when
//        clickRowWithText(R.string.no_fragments)
//
//        //then
//        intended(hasComponent(NoFragmentsActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(19, R.string.no_fragments))
//    }
//
//    @Test
//    fun shouldOpenProceedProgrammaticallyActivity() {
//        //when
//        clickRowWithText(R.string.proceed_programmatically)
//
//        //then
//        intended(hasComponent(ProceedProgrammaticallyActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(20, R.string.proceed_programmatically))
//    }
//
//    @Test
//    fun shouldOpenPassDataBetweenStepsActivity() {
//        //when
//        clickRowWithText(R.string.passing_data_between_steps)
//
//        //then
//        intended(hasComponent(PassDataBetweenStepsActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(21, R.string.passing_data_between_steps))
//    }
//
//    @Test
//    fun shouldOpenDisabledTabNavigationActivity() {
//        //when
//        clickRowWithText(R.string.disabled_tab_navigation)
//
//        //then
//        intended(hasComponent(DisabledTabNavigationActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(22, R.string.disabled_tab_navigation))
//    }
//
//    @Test
//    fun shouldOpenHiddenBottomNavigationActivity() {
//        //when
//        clickRowWithText(R.string.hidden_bottom_navigation)
//
//        //then
//        intended(hasComponent(HiddenBottomNavigationActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(23, R.string.hidden_bottom_navigation))
//    }
//
//    @Test
//    fun shouldOpenCustomStepperLayoutThemeActivity() {
//        //when
//        clickRowWithText(R.string.custom_stepperlayout_theme)
//
//        //then
//        intended(hasComponent(CustomStepperLayoutThemeActivity::class.java.name))
//        SpoonScreenshotAction.perform(getScreenshotTag(24, R.string.custom_stepperlayout_theme))
//    }
//
//    @Test
//    fun shouldOpenSetButtonColorProgrammaticallyActivity() {
//        //when
//        clickRowWithText(R.string.set_button_color_programmatically)
//
//        //then
//        intended(hasComponent(SetButtonColorProgrammaticallyActivity::class.java.name))
//        SpoonScreenshotAction.perform(
//            getScreenshotTag(
//                25,
//                R.string.set_button_color_programmatically
//            )
//        )
//    }
//
//    @NonNull
//    private fun getScreenshotTag(position: Int, @StringRes titleResId: Int): String {
//        return String.format(
//            Locale.ENGLISH,
//            "%02d",
//            position
//        ) + ". " + intentsTestRule.getActivity().getString(titleResId)
//    }
//
//    private fun clickRowWithText(@StringRes stringResId: Int) {
//        Espresso.onView(ViewMatchers.withId(R.id.list))
//            .perform(
//                RecyclerViewActions.actionOnItem(
//                    ViewMatchers.withChild(
//                        ViewMatchers.withText(
//                            stringResId
//                        )
//                    ), ViewActions.click()
//                )
//            )
//    }
//}