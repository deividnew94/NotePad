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

        val noteType = intent.getStringExtra("noteType")
        var note_ID :UUID = UUID.randomUUID()

        if (noteType.equals("Edit")) {
            val noteIndice = intent.getStringExtra("noteID")

            var note = NoteLab.getNote(UUID.fromString(noteIndice))

            note_ID = note!!.id
            viewBinding.etTitle.setText(note!!.title)
            viewBinding.etDescription.setText(note!!.description)

        }

        viewBinding.bSave.setOnClickListener {
            if (viewBinding.etTitle.text.trim().isNotEmpty()) {

                if (noteType.equals("Edit")) {

                    NoteLab.updateNote(Note(id=note_ID,description = viewBinding.etDescription.text.toString(), title = viewBinding.etTitle.text.toString()))

                } else {

                    NoteLab.addNote(Note(description = viewBinding.etDescription.text.toString(), title = viewBinding.etTitle.text.toString()))

                }

                val intent = Intent(this@ChangeNoteActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else   viewBinding.etTitle.error=getString(R.string.field_not_be_empty_error)

        }

        // END YOUR CHANGE
    }
}
