package com.devskiller.notepadplus

import android.os.Build
import android.widget.Button
import android.widget.EditText
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.N])
class ChangeNoteActivityTest {

    private lateinit var mController: ActivityController<ChangeNoteActivity>
    private lateinit var mActivity: ChangeNoteActivity
    private lateinit var mNote: Note
    private lateinit var mSaveButton: Button
    private lateinit var mEditTitle: EditText
    private lateinit var mEditDesc: EditText

    @Before
    fun setUp() {
        NoteLab.clear()

        mNote = Note()
        NoteLab.addNote(mNote)

        val intent = ChangeNoteActivity.newIntent(RuntimeEnvironment.application, mNote.id)

        mController = Robolectric.buildActivity(ChangeNoteActivity::class.java, intent)
        mActivity = mController.create()
                .start()
                .resume()
                .visible()
                .get()
        mEditTitle = mActivity.findViewById(R.id.et_title)
        mSaveButton = mActivity.findViewById(R.id.b_save)
        mEditDesc = mActivity.findViewById(R.id.et_description)
    }

    @Test
    fun shouldShowErrorIfTitleIsEmpty() {
        mEditTitle.setText("")

        mSaveButton.performClick()

        assertThat(mActivity.resources.getString(R.string.field_not_be_empty_error)).isEqualTo(mEditTitle.error)
    }

    @Test
    fun shouldSaveFromEditTitleToNoteLab() {
        mEditTitle.setText("Title1234")

        mSaveButton.performClick()

        val mCurrentNote = NoteLab.getNote(mNote.id)!!

        assertThat(mEditTitle.text.toString()).isEqualTo(mCurrentNote.title)
    }

    @Test
    fun shouldSaveFromEditDescToNoteLab() {
        mEditDesc.setText("Desc 1234")

        mSaveButton.performClick()

        val currentNote: Note = NoteLab.getNote(mNote.id)!!

        assertThat(mEditDesc.text.toString()).isEqualTo(currentNote.description)
    }
}
