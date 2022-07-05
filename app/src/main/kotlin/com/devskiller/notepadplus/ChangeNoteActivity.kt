package com.devskiller.notepadplus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devskiller.notepadplus.databinding.ActivityChangeNoteBinding
import java.util.UUID

class ChangeNoteActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_NOTE_ID = "com.devskiller.intent.note_id"

        fun newIntent(
            context: Context,
            uuid: UUID
        ): Intent = Intent(context, ChangeNoteActivity::class.java)
                .putExtra(EXTRA_NOTE_ID, uuid)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding = ActivityChangeNoteBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        // START YOUR CHANGE
        // END YOUR CHANGE
    }
}
