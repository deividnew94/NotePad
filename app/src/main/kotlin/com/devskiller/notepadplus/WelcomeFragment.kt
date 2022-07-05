package com.devskiller.notepadplus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class WelcomeFragment : Fragment() {

    companion object {

        fun newInstance(): WelcomeFragment = WelcomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // START REFACTOR OF THIS METHOD
        // END REFACTOR OF THIS METHOD
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
