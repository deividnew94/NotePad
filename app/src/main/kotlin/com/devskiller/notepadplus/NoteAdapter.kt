package com.devskiller.notepadplus

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devskiller.notepadplus.databinding.ViewNoteListItemBinding

class NoteAdapter(private val mNotes: List<Note>) : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    inner class NoteHolder(
        private val mViewBinding: ViewNoteListItemBinding
    ) : RecyclerView.ViewHolder(mViewBinding.root), View.OnClickListener {

        private var mNote: Note? = null

        fun bind(note: Note) {
            mViewBinding.tvNoteTitle.text = note.title
            mViewBinding.root.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            // START YOUR CHANGE
            val intent = Intent(mViewBinding.root.context, ChangeNoteActivity::class.java)
            intent.putExtra("noteType", "Edit")
            intent.putExtra("noteID", mNotes[adapterPosition].id.toString())
            mViewBinding.root.context.startActivity(intent)
            (mViewBinding.root.context as MainActivity).finish()
            // END YOUR CHANGE
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteHolder = NoteHolder(ViewNoteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(
        holder: NoteHolder,
        position: Int
    ) {
        // START YOUR CHANGE
        holder.bind(mNotes[position])
        // END YOUR CHANGE
    }

    override fun getItemCount(): Int {
        // START REFACTOR OF THIS METHOD
        if (mNotes.isNotEmpty()) return  mNotes.size
        // END REFACTOR OF THIS METHOD
        return 0
    }
}
