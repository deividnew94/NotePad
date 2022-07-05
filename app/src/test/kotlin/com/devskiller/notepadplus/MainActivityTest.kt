package com.devskiller.notepadplus

import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.widget.TextView
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowActivity
import org.robolectric.shadows.ShadowApplication

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.N])
class MainActivityTest {

    private lateinit var mController: ActivityController<MainActivity>
    private lateinit var mActivity: MainActivity

    @Before
    fun setUp() {
        NoteLab.clear()

        mController = Robolectric.buildActivity(MainActivity::class.java)
        mActivity = mController.create()
                .start()
                .resume()
                .visible()
                .get()
    }

    @Test
    fun shouldHaveWelcomeFragment() {
        val fragment = mActivity.supportFragmentManager.findFragmentById(R.id.fl_fragment_container)

        assertThat(fragment).isInstanceOf(WelcomeFragment::class.java)
    }

    @Test
    fun shouldHaveWelcomeMessageInWelcomeFragment() {
        val fragment = mActivity.supportFragmentManager.findFragmentById(R.id.fl_fragment_container)!!

        val welcomeText = fragment.view!!.findViewById<TextView>(R.id.tv_welcome)

        assertThat(welcomeText.text)
                .isEqualTo(mActivity.resources.getString(R.string.welcome_message))
    }

    @Test
    fun shouldStartChangeNoteActivityOnClickAddButton() {
        val shadowActivity: ShadowActivity = Shadows.shadowOf(mActivity)
        shadowActivity.clickMenuItem(R.id.create_note)

        val actual = ShadowApplication.getInstance().nextStartedActivity
        val expectedIntent = Intent(mActivity, ChangeNoteActivity::class.java)

        assertThat<ComponentName>(expectedIntent.component).isEqualTo(actual.component)
    }
}
