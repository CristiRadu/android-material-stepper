package stepper.idling

import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import androidx.test.espresso.IdlingResource
import androidx.viewpager2.widget.ViewPager2
import androidx.test.espresso.IdlingResource.ResourceCallback

/**
 * View pager listener that serves as Espresso's [IdlingResource] and notifies the
 * registered callback when the view pager gets to STATE_IDLE state.
 *
 *
 * A copy of `android.support.test.espresso.contrib.ViewPagerActions.CustomViewPagerListener`.
 */
class CustomViewPagerListener : OnPageChangeCallback(), IdlingResource {
    private var mCurrState = ViewPager2.SCROLL_STATE_IDLE
    private var mCallback: ResourceCallback? = null
    @JvmField
    var mNeedsIdle = false
    override fun registerIdleTransitionCallback(resourceCallback: ResourceCallback) {
        mCallback = resourceCallback
    }

    override fun getName(): String {
        return "View pager listener"
    }

    override fun isIdleNow(): Boolean {
        return if (!mNeedsIdle) {
            true
        } else {
            mCurrState == ViewPager2.SCROLL_STATE_IDLE
        }
    }

    override fun onPageSelected(position: Int) {
        if (mCurrState == ViewPager2.SCROLL_STATE_IDLE) {
            if (mCallback != null) {
                mCallback!!.onTransitionToIdle()
            }
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
        mCurrState = state
        if (mCurrState == ViewPager2.SCROLL_STATE_IDLE) {
            if (mCallback != null) {
                mCallback!!.onTransitionToIdle()
            }
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
}