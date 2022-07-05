package com.devskiller.notepadplus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.devskiller.notepadplus.databinding.FragmentNoteListBinding

class NoteListFragment : Fragment() {

    companion object {

        fun newInstance(): NoteListFragment = NoteListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewBinding = FragmentNoteListBinding.inflate(inflater, container, false)

        viewBinding.root.layoutManager = LinearLayoutManager(activity)

        return viewBinding.root
    }

    override fun onResume() {
        super.onResume()

        changeList()
    }

    private fun changeList() {
        val notes: List<Note> = NoteLab.notes

        val viewBinding = FragmentNoteListBinding.bind(requireView())

        val adapter = viewBinding.root.adapter

        if (adapter == null) {
            viewBinding.root.adapter = NoteAdapter(notes)
        } else {
            adapter.notifyDataSetChanged()
        }
    }
}
